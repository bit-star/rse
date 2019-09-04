package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.Specification;
import com.lazulite.rse.repository.SpecificationRepository;
import com.lazulite.rse.service.SpecificationService;
import com.lazulite.rse.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.lazulite.rse.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SpecificationResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class SpecificationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSpecificationMockMvc;

    private Specification specification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpecificationResource specificationResource = new SpecificationResource(specificationService);
        this.restSpecificationMockMvc = MockMvcBuilders.standaloneSetup(specificationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specification createEntity(EntityManager em) {
        Specification specification = new Specification()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return specification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specification createUpdatedEntity(EntityManager em) {
        Specification specification = new Specification()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return specification;
    }

    @BeforeEach
    public void initTest() {
        specification = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecification() throws Exception {
        int databaseSizeBeforeCreate = specificationRepository.findAll().size();

        // Create the Specification
        restSpecificationMockMvc.perform(post("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specification)))
            .andExpect(status().isCreated());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeCreate + 1);
        Specification testSpecification = specificationList.get(specificationList.size() - 1);
        assertThat(testSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSpecification.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specificationRepository.findAll().size();

        // Create the Specification with an existing ID
        specification.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecificationMockMvc.perform(post("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specification)))
            .andExpect(status().isBadRequest());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpecifications() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        // Get all the specificationList
        restSpecificationMockMvc.perform(get("/api/specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specification.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getSpecification() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        // Get the specification
        restSpecificationMockMvc.perform(get("/api/specifications/{id}", specification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(specification.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSpecification() throws Exception {
        // Get the specification
        restSpecificationMockMvc.perform(get("/api/specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecification() throws Exception {
        // Initialize the database
        specificationService.save(specification);

        int databaseSizeBeforeUpdate = specificationRepository.findAll().size();

        // Update the specification
        Specification updatedSpecification = specificationRepository.findById(specification.getId()).get();
        // Disconnect from session so that the updates on updatedSpecification are not directly saved in db
        em.detach(updatedSpecification);
        updatedSpecification
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);

        restSpecificationMockMvc.perform(put("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpecification)))
            .andExpect(status().isOk());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeUpdate);
        Specification testSpecification = specificationList.get(specificationList.size() - 1);
        assertThat(testSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSpecification.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecification() throws Exception {
        int databaseSizeBeforeUpdate = specificationRepository.findAll().size();

        // Create the Specification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecificationMockMvc.perform(put("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specification)))
            .andExpect(status().isBadRequest());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecification() throws Exception {
        // Initialize the database
        specificationService.save(specification);

        int databaseSizeBeforeDelete = specificationRepository.findAll().size();

        // Delete the specification
        restSpecificationMockMvc.perform(delete("/api/specifications/{id}", specification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specification.class);
        Specification specification1 = new Specification();
        specification1.setId(1L);
        Specification specification2 = new Specification();
        specification2.setId(specification1.getId());
        assertThat(specification1).isEqualTo(specification2);
        specification2.setId(2L);
        assertThat(specification1).isNotEqualTo(specification2);
        specification1.setId(null);
        assertThat(specification1).isNotEqualTo(specification2);
    }
}
