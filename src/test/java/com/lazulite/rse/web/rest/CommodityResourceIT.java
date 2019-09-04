package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.Commodity;
import com.lazulite.rse.repository.CommodityRepository;
import com.lazulite.rse.service.CommodityService;
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
import java.math.BigDecimal;
import java.util.List;

import static com.lazulite.rse.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lazulite.rse.domain.enumeration.DeliveryMethod;
import com.lazulite.rse.domain.enumeration.CommodityStatus;
/**
 * Integration tests for the {@link CommodityResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class CommodityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DAY_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DAY_RENT = new BigDecimal(2);
    private static final BigDecimal SMALLER_DAY_RENT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_WEEKLY_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEEKLY_RENT = new BigDecimal(2);
    private static final BigDecimal SMALLER_WEEKLY_RENT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_MONTHLY_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTHLY_RENT = new BigDecimal(2);
    private static final BigDecimal SMALLER_MONTHLY_RENT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_DEPOSIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEPOSIT = new BigDecimal(2);
    private static final BigDecimal SMALLER_DEPOSIT = new BigDecimal(1 - 1);

    private static final String DEFAULT_RENTAL_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_RENTAL_METHOD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MAX_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_RENT = new BigDecimal(2);
    private static final BigDecimal SMALLER_MAX_RENT = new BigDecimal(1 - 1);

    private static final Long DEFAULT_INVENTORY = 1L;
    private static final Long UPDATED_INVENTORY = 2L;
    private static final Long SMALLER_INVENTORY = 1L - 1L;

    private static final DeliveryMethod DEFAULT_DELIVERY_METHOD = DeliveryMethod.ExpressDelivery;
    private static final DeliveryMethod UPDATED_DELIVERY_METHOD = DeliveryMethod.PickUp;

    private static final CommodityStatus DEFAULT_STATUS = CommodityStatus.OnLine;
    private static final CommodityStatus UPDATED_STATUS = CommodityStatus.OffLine;

    private static final String DEFAULT_LEASE_MUST_READ = "AAAAAAAAAA";
    private static final String UPDATED_LEASE_MUST_READ = "BBBBBBBBBB";

    private static final String DEFAULT_DESCIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCIPTION = "BBBBBBBBBB";

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityService commodityService;

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

    private MockMvc restCommodityMockMvc;

    private Commodity commodity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommodityResource commodityResource = new CommodityResource(commodityService);
        this.restCommodityMockMvc = MockMvcBuilders.standaloneSetup(commodityResource)
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
    public static Commodity createEntity(EntityManager em) {
        Commodity commodity = new Commodity()
            .name(DEFAULT_NAME)
            .remark(DEFAULT_REMARK)
            .dayRent(DEFAULT_DAY_RENT)
            .weeklyRent(DEFAULT_WEEKLY_RENT)
            .monthlyRent(DEFAULT_MONTHLY_RENT)
            .deposit(DEFAULT_DEPOSIT)
            .rentalMethod(DEFAULT_RENTAL_METHOD)
            .maxRent(DEFAULT_MAX_RENT)
            .inventory(DEFAULT_INVENTORY)
            .deliveryMethod(DEFAULT_DELIVERY_METHOD)
            .status(DEFAULT_STATUS)
            .leaseMustRead(DEFAULT_LEASE_MUST_READ)
            .desciption(DEFAULT_DESCIPTION);
        return commodity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commodity createUpdatedEntity(EntityManager em) {
        Commodity commodity = new Commodity()
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dayRent(UPDATED_DAY_RENT)
            .weeklyRent(UPDATED_WEEKLY_RENT)
            .monthlyRent(UPDATED_MONTHLY_RENT)
            .deposit(UPDATED_DEPOSIT)
            .rentalMethod(UPDATED_RENTAL_METHOD)
            .maxRent(UPDATED_MAX_RENT)
            .inventory(UPDATED_INVENTORY)
            .deliveryMethod(UPDATED_DELIVERY_METHOD)
            .status(UPDATED_STATUS)
            .leaseMustRead(UPDATED_LEASE_MUST_READ)
            .desciption(UPDATED_DESCIPTION);
        return commodity;
    }

    @BeforeEach
    public void initTest() {
        commodity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommodity() throws Exception {
        int databaseSizeBeforeCreate = commodityRepository.findAll().size();

        // Create the Commodity
        restCommodityMockMvc.perform(post("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodity)))
            .andExpect(status().isCreated());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeCreate + 1);
        Commodity testCommodity = commodityList.get(commodityList.size() - 1);
        assertThat(testCommodity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCommodity.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCommodity.getDayRent()).isEqualTo(DEFAULT_DAY_RENT);
        assertThat(testCommodity.getWeeklyRent()).isEqualTo(DEFAULT_WEEKLY_RENT);
        assertThat(testCommodity.getMonthlyRent()).isEqualTo(DEFAULT_MONTHLY_RENT);
        assertThat(testCommodity.getDeposit()).isEqualTo(DEFAULT_DEPOSIT);
        assertThat(testCommodity.getRentalMethod()).isEqualTo(DEFAULT_RENTAL_METHOD);
        assertThat(testCommodity.getMaxRent()).isEqualTo(DEFAULT_MAX_RENT);
        assertThat(testCommodity.getInventory()).isEqualTo(DEFAULT_INVENTORY);
        assertThat(testCommodity.getDeliveryMethod()).isEqualTo(DEFAULT_DELIVERY_METHOD);
        assertThat(testCommodity.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCommodity.getLeaseMustRead()).isEqualTo(DEFAULT_LEASE_MUST_READ);
        assertThat(testCommodity.getDesciption()).isEqualTo(DEFAULT_DESCIPTION);
    }

    @Test
    @Transactional
    public void createCommodityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commodityRepository.findAll().size();

        // Create the Commodity with an existing ID
        commodity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommodityMockMvc.perform(post("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodity)))
            .andExpect(status().isBadRequest());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommodities() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        // Get all the commodityList
        restCommodityMockMvc.perform(get("/api/commodities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commodity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].dayRent").value(hasItem(DEFAULT_DAY_RENT.intValue())))
            .andExpect(jsonPath("$.[*].weeklyRent").value(hasItem(DEFAULT_WEEKLY_RENT.intValue())))
            .andExpect(jsonPath("$.[*].monthlyRent").value(hasItem(DEFAULT_MONTHLY_RENT.intValue())))
            .andExpect(jsonPath("$.[*].deposit").value(hasItem(DEFAULT_DEPOSIT.intValue())))
            .andExpect(jsonPath("$.[*].rentalMethod").value(hasItem(DEFAULT_RENTAL_METHOD.toString())))
            .andExpect(jsonPath("$.[*].maxRent").value(hasItem(DEFAULT_MAX_RENT.intValue())))
            .andExpect(jsonPath("$.[*].inventory").value(hasItem(DEFAULT_INVENTORY.intValue())))
            .andExpect(jsonPath("$.[*].deliveryMethod").value(hasItem(DEFAULT_DELIVERY_METHOD.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].leaseMustRead").value(hasItem(DEFAULT_LEASE_MUST_READ.toString())))
            .andExpect(jsonPath("$.[*].desciption").value(hasItem(DEFAULT_DESCIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getCommodity() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        // Get the commodity
        restCommodityMockMvc.perform(get("/api/commodities/{id}", commodity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commodity.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.dayRent").value(DEFAULT_DAY_RENT.intValue()))
            .andExpect(jsonPath("$.weeklyRent").value(DEFAULT_WEEKLY_RENT.intValue()))
            .andExpect(jsonPath("$.monthlyRent").value(DEFAULT_MONTHLY_RENT.intValue()))
            .andExpect(jsonPath("$.deposit").value(DEFAULT_DEPOSIT.intValue()))
            .andExpect(jsonPath("$.rentalMethod").value(DEFAULT_RENTAL_METHOD.toString()))
            .andExpect(jsonPath("$.maxRent").value(DEFAULT_MAX_RENT.intValue()))
            .andExpect(jsonPath("$.inventory").value(DEFAULT_INVENTORY.intValue()))
            .andExpect(jsonPath("$.deliveryMethod").value(DEFAULT_DELIVERY_METHOD.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.leaseMustRead").value(DEFAULT_LEASE_MUST_READ.toString()))
            .andExpect(jsonPath("$.desciption").value(DEFAULT_DESCIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommodity() throws Exception {
        // Get the commodity
        restCommodityMockMvc.perform(get("/api/commodities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommodity() throws Exception {
        // Initialize the database
        commodityService.save(commodity);

        int databaseSizeBeforeUpdate = commodityRepository.findAll().size();

        // Update the commodity
        Commodity updatedCommodity = commodityRepository.findById(commodity.getId()).get();
        // Disconnect from session so that the updates on updatedCommodity are not directly saved in db
        em.detach(updatedCommodity);
        updatedCommodity
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dayRent(UPDATED_DAY_RENT)
            .weeklyRent(UPDATED_WEEKLY_RENT)
            .monthlyRent(UPDATED_MONTHLY_RENT)
            .deposit(UPDATED_DEPOSIT)
            .rentalMethod(UPDATED_RENTAL_METHOD)
            .maxRent(UPDATED_MAX_RENT)
            .inventory(UPDATED_INVENTORY)
            .deliveryMethod(UPDATED_DELIVERY_METHOD)
            .status(UPDATED_STATUS)
            .leaseMustRead(UPDATED_LEASE_MUST_READ)
            .desciption(UPDATED_DESCIPTION);

        restCommodityMockMvc.perform(put("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommodity)))
            .andExpect(status().isOk());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeUpdate);
        Commodity testCommodity = commodityList.get(commodityList.size() - 1);
        assertThat(testCommodity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCommodity.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCommodity.getDayRent()).isEqualTo(UPDATED_DAY_RENT);
        assertThat(testCommodity.getWeeklyRent()).isEqualTo(UPDATED_WEEKLY_RENT);
        assertThat(testCommodity.getMonthlyRent()).isEqualTo(UPDATED_MONTHLY_RENT);
        assertThat(testCommodity.getDeposit()).isEqualTo(UPDATED_DEPOSIT);
        assertThat(testCommodity.getRentalMethod()).isEqualTo(UPDATED_RENTAL_METHOD);
        assertThat(testCommodity.getMaxRent()).isEqualTo(UPDATED_MAX_RENT);
        assertThat(testCommodity.getInventory()).isEqualTo(UPDATED_INVENTORY);
        assertThat(testCommodity.getDeliveryMethod()).isEqualTo(UPDATED_DELIVERY_METHOD);
        assertThat(testCommodity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCommodity.getLeaseMustRead()).isEqualTo(UPDATED_LEASE_MUST_READ);
        assertThat(testCommodity.getDesciption()).isEqualTo(UPDATED_DESCIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCommodity() throws Exception {
        int databaseSizeBeforeUpdate = commodityRepository.findAll().size();

        // Create the Commodity

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommodityMockMvc.perform(put("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodity)))
            .andExpect(status().isBadRequest());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommodity() throws Exception {
        // Initialize the database
        commodityService.save(commodity);

        int databaseSizeBeforeDelete = commodityRepository.findAll().size();

        // Delete the commodity
        restCommodityMockMvc.perform(delete("/api/commodities/{id}", commodity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Commodity.class);
        Commodity commodity1 = new Commodity();
        commodity1.setId(1L);
        Commodity commodity2 = new Commodity();
        commodity2.setId(commodity1.getId());
        assertThat(commodity1).isEqualTo(commodity2);
        commodity2.setId(2L);
        assertThat(commodity1).isNotEqualTo(commodity2);
        commodity1.setId(null);
        assertThat(commodity1).isNotEqualTo(commodity2);
    }
}
