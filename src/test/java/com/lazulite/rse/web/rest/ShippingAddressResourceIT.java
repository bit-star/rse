package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.ShippingAddress;
import com.lazulite.rse.repository.ShippingAddressRepository;
import com.lazulite.rse.service.ShippingAddressService;
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
 * Integration tests for the {@link ShippingAddressResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class ShippingAddressResourceIT {

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_MOBLIE = "AAAAAAAAAA";
    private static final String UPDATED_MOBLIE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ShippingAddressService shippingAddressService;

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

    private MockMvc restShippingAddressMockMvc;

    private ShippingAddress shippingAddress;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingAddressResource shippingAddressResource = new ShippingAddressResource(shippingAddressService);
        this.restShippingAddressMockMvc = MockMvcBuilders.standaloneSetup(shippingAddressResource)
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
    public static ShippingAddress createEntity(EntityManager em) {
        ShippingAddress shippingAddress = new ShippingAddress()
            .receiver(DEFAULT_RECEIVER)
            .moblie(DEFAULT_MOBLIE)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .region(DEFAULT_REGION)
            .address(DEFAULT_ADDRESS);
        return shippingAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingAddress createUpdatedEntity(EntityManager em) {
        ShippingAddress shippingAddress = new ShippingAddress()
            .receiver(UPDATED_RECEIVER)
            .moblie(UPDATED_MOBLIE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .region(UPDATED_REGION)
            .address(UPDATED_ADDRESS);
        return shippingAddress;
    }

    @BeforeEach
    public void initTest() {
        shippingAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingAddress() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddress)))
            .andExpect(status().isCreated());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
        assertThat(testShippingAddress.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testShippingAddress.getMoblie()).isEqualTo(DEFAULT_MOBLIE);
        assertThat(testShippingAddress.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testShippingAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testShippingAddress.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testShippingAddress.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createShippingAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress with an existing ID
        shippingAddress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddress)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShippingAddresses() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get all the shippingAddressList
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER.toString())))
            .andExpect(jsonPath("$.[*].moblie").value(hasItem(DEFAULT_MOBLIE.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())));
    }
    
    @Test
    @Transactional
    public void getShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", shippingAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shippingAddress.getId().intValue()))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER.toString()))
            .andExpect(jsonPath("$.moblie").value(DEFAULT_MOBLIE.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingShippingAddress() throws Exception {
        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressService.save(shippingAddress);

        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Update the shippingAddress
        ShippingAddress updatedShippingAddress = shippingAddressRepository.findById(shippingAddress.getId()).get();
        // Disconnect from session so that the updates on updatedShippingAddress are not directly saved in db
        em.detach(updatedShippingAddress);
        updatedShippingAddress
            .receiver(UPDATED_RECEIVER)
            .moblie(UPDATED_MOBLIE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .region(UPDATED_REGION)
            .address(UPDATED_ADDRESS);

        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedShippingAddress)))
            .andExpect(status().isOk());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
        assertThat(testShippingAddress.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testShippingAddress.getMoblie()).isEqualTo(UPDATED_MOBLIE);
        assertThat(testShippingAddress.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testShippingAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testShippingAddress.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testShippingAddress.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingAddress() throws Exception {
        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddress)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressService.save(shippingAddress);

        int databaseSizeBeforeDelete = shippingAddressRepository.findAll().size();

        // Delete the shippingAddress
        restShippingAddressMockMvc.perform(delete("/api/shipping-addresses/{id}", shippingAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingAddress.class);
        ShippingAddress shippingAddress1 = new ShippingAddress();
        shippingAddress1.setId(1L);
        ShippingAddress shippingAddress2 = new ShippingAddress();
        shippingAddress2.setId(shippingAddress1.getId());
        assertThat(shippingAddress1).isEqualTo(shippingAddress2);
        shippingAddress2.setId(2L);
        assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
        shippingAddress1.setId(null);
        assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
    }
}
