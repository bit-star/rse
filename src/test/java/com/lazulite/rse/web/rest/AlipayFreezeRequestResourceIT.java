package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.AlipayFreezeRequest;
import com.lazulite.rse.repository.AlipayFreezeRequestRepository;
import com.lazulite.rse.service.AlipayFreezeRequestService;
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
 * Integration tests for the {@link AlipayFreezeRequestResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class AlipayFreezeRequestResourceIT {

    private static final String DEFAULT_ORDER_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_REQUEST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_LOGON_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_LOGON_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_NOTIFY_URL = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFY_URL = "BBBBBBBBBB";

    @Autowired
    private AlipayFreezeRequestRepository alipayFreezeRequestRepository;

    @Autowired
    private AlipayFreezeRequestService alipayFreezeRequestService;

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

    private MockMvc restAlipayFreezeRequestMockMvc;

    private AlipayFreezeRequest alipayFreezeRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlipayFreezeRequestResource alipayFreezeRequestResource = new AlipayFreezeRequestResource(alipayFreezeRequestService);
        this.restAlipayFreezeRequestMockMvc = MockMvcBuilders.standaloneSetup(alipayFreezeRequestResource)
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
    public static AlipayFreezeRequest createEntity(EntityManager em) {
        AlipayFreezeRequest alipayFreezeRequest = new AlipayFreezeRequest()
            .orderTitle(DEFAULT_ORDER_TITLE)
            .outOrderNo(DEFAULT_OUT_ORDER_NO)
            .outRequestNo(DEFAULT_OUT_REQUEST_NO)
            .payeeUserId(DEFAULT_PAYEE_USER_ID)
            .payeeLogonId(DEFAULT_PAYEE_LOGON_ID)
            .productCode(DEFAULT_PRODUCT_CODE)
            .amount(DEFAULT_AMOUNT)
            .extraParam(DEFAULT_EXTRA_PARAM)
            .notifyUrl(DEFAULT_NOTIFY_URL);
        return alipayFreezeRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlipayFreezeRequest createUpdatedEntity(EntityManager em) {
        AlipayFreezeRequest alipayFreezeRequest = new AlipayFreezeRequest()
            .orderTitle(UPDATED_ORDER_TITLE)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payeeUserId(UPDATED_PAYEE_USER_ID)
            .payeeLogonId(UPDATED_PAYEE_LOGON_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .amount(UPDATED_AMOUNT)
            .extraParam(UPDATED_EXTRA_PARAM)
            .notifyUrl(UPDATED_NOTIFY_URL);
        return alipayFreezeRequest;
    }

    @BeforeEach
    public void initTest() {
        alipayFreezeRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlipayFreezeRequest() throws Exception {
        int databaseSizeBeforeCreate = alipayFreezeRequestRepository.findAll().size();

        // Create the AlipayFreezeRequest
        restAlipayFreezeRequestMockMvc.perform(post("/api/alipay-freeze-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeRequest)))
            .andExpect(status().isCreated());

        // Validate the AlipayFreezeRequest in the database
        List<AlipayFreezeRequest> alipayFreezeRequestList = alipayFreezeRequestRepository.findAll();
        assertThat(alipayFreezeRequestList).hasSize(databaseSizeBeforeCreate + 1);
        AlipayFreezeRequest testAlipayFreezeRequest = alipayFreezeRequestList.get(alipayFreezeRequestList.size() - 1);
        assertThat(testAlipayFreezeRequest.getOrderTitle()).isEqualTo(DEFAULT_ORDER_TITLE);
        assertThat(testAlipayFreezeRequest.getOutOrderNo()).isEqualTo(DEFAULT_OUT_ORDER_NO);
        assertThat(testAlipayFreezeRequest.getOutRequestNo()).isEqualTo(DEFAULT_OUT_REQUEST_NO);
        assertThat(testAlipayFreezeRequest.getPayeeUserId()).isEqualTo(DEFAULT_PAYEE_USER_ID);
        assertThat(testAlipayFreezeRequest.getPayeeLogonId()).isEqualTo(DEFAULT_PAYEE_LOGON_ID);
        assertThat(testAlipayFreezeRequest.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testAlipayFreezeRequest.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAlipayFreezeRequest.getExtraParam()).isEqualTo(DEFAULT_EXTRA_PARAM);
        assertThat(testAlipayFreezeRequest.getNotifyUrl()).isEqualTo(DEFAULT_NOTIFY_URL);
    }

    @Test
    @Transactional
    public void createAlipayFreezeRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alipayFreezeRequestRepository.findAll().size();

        // Create the AlipayFreezeRequest with an existing ID
        alipayFreezeRequest.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlipayFreezeRequestMockMvc.perform(post("/api/alipay-freeze-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeRequest)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFreezeRequest in the database
        List<AlipayFreezeRequest> alipayFreezeRequestList = alipayFreezeRequestRepository.findAll();
        assertThat(alipayFreezeRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAlipayFreezeRequests() throws Exception {
        // Initialize the database
        alipayFreezeRequestRepository.saveAndFlush(alipayFreezeRequest);

        // Get all the alipayFreezeRequestList
        restAlipayFreezeRequestMockMvc.perform(get("/api/alipay-freeze-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alipayFreezeRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderTitle").value(hasItem(DEFAULT_ORDER_TITLE.toString())))
            .andExpect(jsonPath("$.[*].outOrderNo").value(hasItem(DEFAULT_OUT_ORDER_NO.toString())))
            .andExpect(jsonPath("$.[*].outRequestNo").value(hasItem(DEFAULT_OUT_REQUEST_NO.toString())))
            .andExpect(jsonPath("$.[*].payeeUserId").value(hasItem(DEFAULT_PAYEE_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].payeeLogonId").value(hasItem(DEFAULT_PAYEE_LOGON_ID.toString())))
            .andExpect(jsonPath("$.[*].productCode").value(hasItem(DEFAULT_PRODUCT_CODE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].extraParam").value(hasItem(DEFAULT_EXTRA_PARAM.toString())))
            .andExpect(jsonPath("$.[*].notifyUrl").value(hasItem(DEFAULT_NOTIFY_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getAlipayFreezeRequest() throws Exception {
        // Initialize the database
        alipayFreezeRequestRepository.saveAndFlush(alipayFreezeRequest);

        // Get the alipayFreezeRequest
        restAlipayFreezeRequestMockMvc.perform(get("/api/alipay-freeze-requests/{id}", alipayFreezeRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alipayFreezeRequest.getId().intValue()))
            .andExpect(jsonPath("$.orderTitle").value(DEFAULT_ORDER_TITLE.toString()))
            .andExpect(jsonPath("$.outOrderNo").value(DEFAULT_OUT_ORDER_NO.toString()))
            .andExpect(jsonPath("$.outRequestNo").value(DEFAULT_OUT_REQUEST_NO.toString()))
            .andExpect(jsonPath("$.payeeUserId").value(DEFAULT_PAYEE_USER_ID.toString()))
            .andExpect(jsonPath("$.payeeLogonId").value(DEFAULT_PAYEE_LOGON_ID.toString()))
            .andExpect(jsonPath("$.productCode").value(DEFAULT_PRODUCT_CODE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.extraParam").value(DEFAULT_EXTRA_PARAM.toString()))
            .andExpect(jsonPath("$.notifyUrl").value(DEFAULT_NOTIFY_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlipayFreezeRequest() throws Exception {
        // Get the alipayFreezeRequest
        restAlipayFreezeRequestMockMvc.perform(get("/api/alipay-freeze-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlipayFreezeRequest() throws Exception {
        // Initialize the database
        alipayFreezeRequestService.save(alipayFreezeRequest);

        int databaseSizeBeforeUpdate = alipayFreezeRequestRepository.findAll().size();

        // Update the alipayFreezeRequest
        AlipayFreezeRequest updatedAlipayFreezeRequest = alipayFreezeRequestRepository.findById(alipayFreezeRequest.getId()).get();
        // Disconnect from session so that the updates on updatedAlipayFreezeRequest are not directly saved in db
        em.detach(updatedAlipayFreezeRequest);
        updatedAlipayFreezeRequest
            .orderTitle(UPDATED_ORDER_TITLE)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payeeUserId(UPDATED_PAYEE_USER_ID)
            .payeeLogonId(UPDATED_PAYEE_LOGON_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .amount(UPDATED_AMOUNT)
            .extraParam(UPDATED_EXTRA_PARAM)
            .notifyUrl(UPDATED_NOTIFY_URL);

        restAlipayFreezeRequestMockMvc.perform(put("/api/alipay-freeze-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlipayFreezeRequest)))
            .andExpect(status().isOk());

        // Validate the AlipayFreezeRequest in the database
        List<AlipayFreezeRequest> alipayFreezeRequestList = alipayFreezeRequestRepository.findAll();
        assertThat(alipayFreezeRequestList).hasSize(databaseSizeBeforeUpdate);
        AlipayFreezeRequest testAlipayFreezeRequest = alipayFreezeRequestList.get(alipayFreezeRequestList.size() - 1);
        assertThat(testAlipayFreezeRequest.getOrderTitle()).isEqualTo(UPDATED_ORDER_TITLE);
        assertThat(testAlipayFreezeRequest.getOutOrderNo()).isEqualTo(UPDATED_OUT_ORDER_NO);
        assertThat(testAlipayFreezeRequest.getOutRequestNo()).isEqualTo(UPDATED_OUT_REQUEST_NO);
        assertThat(testAlipayFreezeRequest.getPayeeUserId()).isEqualTo(UPDATED_PAYEE_USER_ID);
        assertThat(testAlipayFreezeRequest.getPayeeLogonId()).isEqualTo(UPDATED_PAYEE_LOGON_ID);
        assertThat(testAlipayFreezeRequest.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testAlipayFreezeRequest.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAlipayFreezeRequest.getExtraParam()).isEqualTo(UPDATED_EXTRA_PARAM);
        assertThat(testAlipayFreezeRequest.getNotifyUrl()).isEqualTo(UPDATED_NOTIFY_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingAlipayFreezeRequest() throws Exception {
        int databaseSizeBeforeUpdate = alipayFreezeRequestRepository.findAll().size();

        // Create the AlipayFreezeRequest

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlipayFreezeRequestMockMvc.perform(put("/api/alipay-freeze-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeRequest)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFreezeRequest in the database
        List<AlipayFreezeRequest> alipayFreezeRequestList = alipayFreezeRequestRepository.findAll();
        assertThat(alipayFreezeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlipayFreezeRequest() throws Exception {
        // Initialize the database
        alipayFreezeRequestService.save(alipayFreezeRequest);

        int databaseSizeBeforeDelete = alipayFreezeRequestRepository.findAll().size();

        // Delete the alipayFreezeRequest
        restAlipayFreezeRequestMockMvc.perform(delete("/api/alipay-freeze-requests/{id}", alipayFreezeRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlipayFreezeRequest> alipayFreezeRequestList = alipayFreezeRequestRepository.findAll();
        assertThat(alipayFreezeRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlipayFreezeRequest.class);
        AlipayFreezeRequest alipayFreezeRequest1 = new AlipayFreezeRequest();
        alipayFreezeRequest1.setId(1L);
        AlipayFreezeRequest alipayFreezeRequest2 = new AlipayFreezeRequest();
        alipayFreezeRequest2.setId(alipayFreezeRequest1.getId());
        assertThat(alipayFreezeRequest1).isEqualTo(alipayFreezeRequest2);
        alipayFreezeRequest2.setId(2L);
        assertThat(alipayFreezeRequest1).isNotEqualTo(alipayFreezeRequest2);
        alipayFreezeRequest1.setId(null);
        assertThat(alipayFreezeRequest1).isNotEqualTo(alipayFreezeRequest2);
    }
}
