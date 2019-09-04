package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.ItemLeaseCycle;
import com.lazulite.rse.repository.ItemLeaseCycleRepository;
import com.lazulite.rse.service.ItemLeaseCycleService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lazulite.rse.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ItemLeaseCycleResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class ItemLeaseCycleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_START_TIME = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_END_TIME = Instant.ofEpochMilli(-1L);

    private static final Long DEFAULT_INVENTORY = 1L;
    private static final Long UPDATED_INVENTORY = 2L;
    private static final Long SMALLER_INVENTORY = 1L - 1L;

    private static final Long DEFAULT_NUMBER_OF_PERIODS = 1L;
    private static final Long UPDATED_NUMBER_OF_PERIODS = 2L;
    private static final Long SMALLER_NUMBER_OF_PERIODS = 1L - 1L;

    private static final BigDecimal DEFAULT_DEPOSIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEPOSIT = new BigDecimal(2);
    private static final BigDecimal SMALLER_DEPOSIT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_CREDIT_EXEMPTION = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_EXEMPTION = new BigDecimal(2);
    private static final BigDecimal SMALLER_CREDIT_EXEMPTION = new BigDecimal(1 - 1);

    private static final Long DEFAULT_RENT_RECEIVED_NUMBER_OF_PERIODS = 1L;
    private static final Long UPDATED_RENT_RECEIVED_NUMBER_OF_PERIODS = 2L;
    private static final Long SMALLER_RENT_RECEIVED_NUMBER_OF_PERIODS = 1L - 1L;

    private static final BigDecimal DEFAULT_DEBITED_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEBITED_AMOUNT = new BigDecimal(2);
    private static final BigDecimal SMALLER_DEBITED_AMOUNT = new BigDecimal(1 - 1);

    private static final Instant DEFAULT_NEXT_BILL_DAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NEXT_BILL_DAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_NEXT_BILL_DAY = Instant.ofEpochMilli(-1L);

    @Autowired
    private ItemLeaseCycleRepository itemLeaseCycleRepository;

    @Autowired
    private ItemLeaseCycleService itemLeaseCycleService;

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

    private MockMvc restItemLeaseCycleMockMvc;

    private ItemLeaseCycle itemLeaseCycle;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemLeaseCycleResource itemLeaseCycleResource = new ItemLeaseCycleResource(itemLeaseCycleService);
        this.restItemLeaseCycleMockMvc = MockMvcBuilders.standaloneSetup(itemLeaseCycleResource)
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
    public static ItemLeaseCycle createEntity(EntityManager em) {
        ItemLeaseCycle itemLeaseCycle = new ItemLeaseCycle()
            .name(DEFAULT_NAME)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .inventory(DEFAULT_INVENTORY)
            .numberOfPeriods(DEFAULT_NUMBER_OF_PERIODS)
            .deposit(DEFAULT_DEPOSIT)
            .creditExemption(DEFAULT_CREDIT_EXEMPTION)
            .rentReceivedNumberOfPeriods(DEFAULT_RENT_RECEIVED_NUMBER_OF_PERIODS)
            .debitedAmount(DEFAULT_DEBITED_AMOUNT)
            .nextBillDay(DEFAULT_NEXT_BILL_DAY);
        return itemLeaseCycle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemLeaseCycle createUpdatedEntity(EntityManager em) {
        ItemLeaseCycle itemLeaseCycle = new ItemLeaseCycle()
            .name(UPDATED_NAME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .inventory(UPDATED_INVENTORY)
            .numberOfPeriods(UPDATED_NUMBER_OF_PERIODS)
            .deposit(UPDATED_DEPOSIT)
            .creditExemption(UPDATED_CREDIT_EXEMPTION)
            .rentReceivedNumberOfPeriods(UPDATED_RENT_RECEIVED_NUMBER_OF_PERIODS)
            .debitedAmount(UPDATED_DEBITED_AMOUNT)
            .nextBillDay(UPDATED_NEXT_BILL_DAY);
        return itemLeaseCycle;
    }

    @BeforeEach
    public void initTest() {
        itemLeaseCycle = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemLeaseCycle() throws Exception {
        int databaseSizeBeforeCreate = itemLeaseCycleRepository.findAll().size();

        // Create the ItemLeaseCycle
        restItemLeaseCycleMockMvc.perform(post("/api/item-lease-cycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemLeaseCycle)))
            .andExpect(status().isCreated());

        // Validate the ItemLeaseCycle in the database
        List<ItemLeaseCycle> itemLeaseCycleList = itemLeaseCycleRepository.findAll();
        assertThat(itemLeaseCycleList).hasSize(databaseSizeBeforeCreate + 1);
        ItemLeaseCycle testItemLeaseCycle = itemLeaseCycleList.get(itemLeaseCycleList.size() - 1);
        assertThat(testItemLeaseCycle.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemLeaseCycle.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testItemLeaseCycle.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testItemLeaseCycle.getInventory()).isEqualTo(DEFAULT_INVENTORY);
        assertThat(testItemLeaseCycle.getNumberOfPeriods()).isEqualTo(DEFAULT_NUMBER_OF_PERIODS);
        assertThat(testItemLeaseCycle.getDeposit()).isEqualTo(DEFAULT_DEPOSIT);
        assertThat(testItemLeaseCycle.getCreditExemption()).isEqualTo(DEFAULT_CREDIT_EXEMPTION);
        assertThat(testItemLeaseCycle.getRentReceivedNumberOfPeriods()).isEqualTo(DEFAULT_RENT_RECEIVED_NUMBER_OF_PERIODS);
        assertThat(testItemLeaseCycle.getDebitedAmount()).isEqualTo(DEFAULT_DEBITED_AMOUNT);
        assertThat(testItemLeaseCycle.getNextBillDay()).isEqualTo(DEFAULT_NEXT_BILL_DAY);
    }

    @Test
    @Transactional
    public void createItemLeaseCycleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemLeaseCycleRepository.findAll().size();

        // Create the ItemLeaseCycle with an existing ID
        itemLeaseCycle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemLeaseCycleMockMvc.perform(post("/api/item-lease-cycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemLeaseCycle)))
            .andExpect(status().isBadRequest());

        // Validate the ItemLeaseCycle in the database
        List<ItemLeaseCycle> itemLeaseCycleList = itemLeaseCycleRepository.findAll();
        assertThat(itemLeaseCycleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllItemLeaseCycles() throws Exception {
        // Initialize the database
        itemLeaseCycleRepository.saveAndFlush(itemLeaseCycle);

        // Get all the itemLeaseCycleList
        restItemLeaseCycleMockMvc.perform(get("/api/item-lease-cycles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemLeaseCycle.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].inventory").value(hasItem(DEFAULT_INVENTORY.intValue())))
            .andExpect(jsonPath("$.[*].numberOfPeriods").value(hasItem(DEFAULT_NUMBER_OF_PERIODS.intValue())))
            .andExpect(jsonPath("$.[*].deposit").value(hasItem(DEFAULT_DEPOSIT.intValue())))
            .andExpect(jsonPath("$.[*].creditExemption").value(hasItem(DEFAULT_CREDIT_EXEMPTION.intValue())))
            .andExpect(jsonPath("$.[*].rentReceivedNumberOfPeriods").value(hasItem(DEFAULT_RENT_RECEIVED_NUMBER_OF_PERIODS.intValue())))
            .andExpect(jsonPath("$.[*].debitedAmount").value(hasItem(DEFAULT_DEBITED_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].nextBillDay").value(hasItem(DEFAULT_NEXT_BILL_DAY.toString())));
    }
    
    @Test
    @Transactional
    public void getItemLeaseCycle() throws Exception {
        // Initialize the database
        itemLeaseCycleRepository.saveAndFlush(itemLeaseCycle);

        // Get the itemLeaseCycle
        restItemLeaseCycleMockMvc.perform(get("/api/item-lease-cycles/{id}", itemLeaseCycle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemLeaseCycle.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.inventory").value(DEFAULT_INVENTORY.intValue()))
            .andExpect(jsonPath("$.numberOfPeriods").value(DEFAULT_NUMBER_OF_PERIODS.intValue()))
            .andExpect(jsonPath("$.deposit").value(DEFAULT_DEPOSIT.intValue()))
            .andExpect(jsonPath("$.creditExemption").value(DEFAULT_CREDIT_EXEMPTION.intValue()))
            .andExpect(jsonPath("$.rentReceivedNumberOfPeriods").value(DEFAULT_RENT_RECEIVED_NUMBER_OF_PERIODS.intValue()))
            .andExpect(jsonPath("$.debitedAmount").value(DEFAULT_DEBITED_AMOUNT.intValue()))
            .andExpect(jsonPath("$.nextBillDay").value(DEFAULT_NEXT_BILL_DAY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemLeaseCycle() throws Exception {
        // Get the itemLeaseCycle
        restItemLeaseCycleMockMvc.perform(get("/api/item-lease-cycles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemLeaseCycle() throws Exception {
        // Initialize the database
        itemLeaseCycleService.save(itemLeaseCycle);

        int databaseSizeBeforeUpdate = itemLeaseCycleRepository.findAll().size();

        // Update the itemLeaseCycle
        ItemLeaseCycle updatedItemLeaseCycle = itemLeaseCycleRepository.findById(itemLeaseCycle.getId()).get();
        // Disconnect from session so that the updates on updatedItemLeaseCycle are not directly saved in db
        em.detach(updatedItemLeaseCycle);
        updatedItemLeaseCycle
            .name(UPDATED_NAME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .inventory(UPDATED_INVENTORY)
            .numberOfPeriods(UPDATED_NUMBER_OF_PERIODS)
            .deposit(UPDATED_DEPOSIT)
            .creditExemption(UPDATED_CREDIT_EXEMPTION)
            .rentReceivedNumberOfPeriods(UPDATED_RENT_RECEIVED_NUMBER_OF_PERIODS)
            .debitedAmount(UPDATED_DEBITED_AMOUNT)
            .nextBillDay(UPDATED_NEXT_BILL_DAY);

        restItemLeaseCycleMockMvc.perform(put("/api/item-lease-cycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemLeaseCycle)))
            .andExpect(status().isOk());

        // Validate the ItemLeaseCycle in the database
        List<ItemLeaseCycle> itemLeaseCycleList = itemLeaseCycleRepository.findAll();
        assertThat(itemLeaseCycleList).hasSize(databaseSizeBeforeUpdate);
        ItemLeaseCycle testItemLeaseCycle = itemLeaseCycleList.get(itemLeaseCycleList.size() - 1);
        assertThat(testItemLeaseCycle.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemLeaseCycle.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testItemLeaseCycle.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testItemLeaseCycle.getInventory()).isEqualTo(UPDATED_INVENTORY);
        assertThat(testItemLeaseCycle.getNumberOfPeriods()).isEqualTo(UPDATED_NUMBER_OF_PERIODS);
        assertThat(testItemLeaseCycle.getDeposit()).isEqualTo(UPDATED_DEPOSIT);
        assertThat(testItemLeaseCycle.getCreditExemption()).isEqualTo(UPDATED_CREDIT_EXEMPTION);
        assertThat(testItemLeaseCycle.getRentReceivedNumberOfPeriods()).isEqualTo(UPDATED_RENT_RECEIVED_NUMBER_OF_PERIODS);
        assertThat(testItemLeaseCycle.getDebitedAmount()).isEqualTo(UPDATED_DEBITED_AMOUNT);
        assertThat(testItemLeaseCycle.getNextBillDay()).isEqualTo(UPDATED_NEXT_BILL_DAY);
    }

    @Test
    @Transactional
    public void updateNonExistingItemLeaseCycle() throws Exception {
        int databaseSizeBeforeUpdate = itemLeaseCycleRepository.findAll().size();

        // Create the ItemLeaseCycle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemLeaseCycleMockMvc.perform(put("/api/item-lease-cycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemLeaseCycle)))
            .andExpect(status().isBadRequest());

        // Validate the ItemLeaseCycle in the database
        List<ItemLeaseCycle> itemLeaseCycleList = itemLeaseCycleRepository.findAll();
        assertThat(itemLeaseCycleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemLeaseCycle() throws Exception {
        // Initialize the database
        itemLeaseCycleService.save(itemLeaseCycle);

        int databaseSizeBeforeDelete = itemLeaseCycleRepository.findAll().size();

        // Delete the itemLeaseCycle
        restItemLeaseCycleMockMvc.perform(delete("/api/item-lease-cycles/{id}", itemLeaseCycle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemLeaseCycle> itemLeaseCycleList = itemLeaseCycleRepository.findAll();
        assertThat(itemLeaseCycleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemLeaseCycle.class);
        ItemLeaseCycle itemLeaseCycle1 = new ItemLeaseCycle();
        itemLeaseCycle1.setId(1L);
        ItemLeaseCycle itemLeaseCycle2 = new ItemLeaseCycle();
        itemLeaseCycle2.setId(itemLeaseCycle1.getId());
        assertThat(itemLeaseCycle1).isEqualTo(itemLeaseCycle2);
        itemLeaseCycle2.setId(2L);
        assertThat(itemLeaseCycle1).isNotEqualTo(itemLeaseCycle2);
        itemLeaseCycle1.setId(null);
        assertThat(itemLeaseCycle1).isNotEqualTo(itemLeaseCycle2);
    }
}
