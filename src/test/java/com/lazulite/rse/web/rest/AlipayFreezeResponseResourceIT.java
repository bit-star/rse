package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.AlipayFreezeResponse;
import com.lazulite.rse.repository.AlipayFreezeResponseRepository;
import com.lazulite.rse.service.AlipayFreezeResponseService;
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
 * Integration tests for the {@link AlipayFreezeResponseResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class AlipayFreezeResponseResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MSG = "AAAAAAAAAA";
    private static final String UPDATED_MSG = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_MSG = "AAAAAAAAAA";
    private static final String UPDATED_SUB_MSG = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final String DEFAULT_PARAMS_STR = "AAAAAAAAAA";
    private static final String UPDATED_PARAMS_STR = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_AUTH_NO = "AAAAAAAAAA";
    private static final String UPDATED_AUTH_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_GMT_TRANS = "AAAAAAAAAA";
    private static final String UPDATED_GMT_TRANS = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_REQUEST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRE_AUTH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRE_AUTH_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private AlipayFreezeResponseRepository alipayFreezeResponseRepository;

    @Autowired
    private AlipayFreezeResponseService alipayFreezeResponseService;

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

    private MockMvc restAlipayFreezeResponseMockMvc;

    private AlipayFreezeResponse alipayFreezeResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlipayFreezeResponseResource alipayFreezeResponseResource = new AlipayFreezeResponseResource(alipayFreezeResponseService);
        this.restAlipayFreezeResponseMockMvc = MockMvcBuilders.standaloneSetup(alipayFreezeResponseResource)
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
    public static AlipayFreezeResponse createEntity(EntityManager em) {
        AlipayFreezeResponse alipayFreezeResponse = new AlipayFreezeResponse()
            .code(DEFAULT_CODE)
            .msg(DEFAULT_MSG)
            .subCode(DEFAULT_SUB_CODE)
            .subMsg(DEFAULT_SUB_MSG)
            .body(DEFAULT_BODY)
            .paramsStr(DEFAULT_PARAMS_STR)
            .amount(DEFAULT_AMOUNT)
            .authNo(DEFAULT_AUTH_NO)
            .creditAmount(DEFAULT_CREDIT_AMOUNT)
            .fundAmount(DEFAULT_FUND_AMOUNT)
            .gmtTrans(DEFAULT_GMT_TRANS)
            .operationId(DEFAULT_OPERATION_ID)
            .outOrderNo(DEFAULT_OUT_ORDER_NO)
            .outRequestNo(DEFAULT_OUT_REQUEST_NO)
            .payerUserId(DEFAULT_PAYER_USER_ID)
            .preAuthType(DEFAULT_PRE_AUTH_TYPE)
            .status(DEFAULT_STATUS);
        return alipayFreezeResponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlipayFreezeResponse createUpdatedEntity(EntityManager em) {
        AlipayFreezeResponse alipayFreezeResponse = new AlipayFreezeResponse()
            .code(UPDATED_CODE)
            .msg(UPDATED_MSG)
            .subCode(UPDATED_SUB_CODE)
            .subMsg(UPDATED_SUB_MSG)
            .body(UPDATED_BODY)
            .paramsStr(UPDATED_PARAMS_STR)
            .amount(UPDATED_AMOUNT)
            .authNo(UPDATED_AUTH_NO)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .fundAmount(UPDATED_FUND_AMOUNT)
            .gmtTrans(UPDATED_GMT_TRANS)
            .operationId(UPDATED_OPERATION_ID)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payerUserId(UPDATED_PAYER_USER_ID)
            .preAuthType(UPDATED_PRE_AUTH_TYPE)
            .status(UPDATED_STATUS);
        return alipayFreezeResponse;
    }

    @BeforeEach
    public void initTest() {
        alipayFreezeResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlipayFreezeResponse() throws Exception {
        int databaseSizeBeforeCreate = alipayFreezeResponseRepository.findAll().size();

        // Create the AlipayFreezeResponse
        restAlipayFreezeResponseMockMvc.perform(post("/api/alipay-freeze-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeResponse)))
            .andExpect(status().isCreated());

        // Validate the AlipayFreezeResponse in the database
        List<AlipayFreezeResponse> alipayFreezeResponseList = alipayFreezeResponseRepository.findAll();
        assertThat(alipayFreezeResponseList).hasSize(databaseSizeBeforeCreate + 1);
        AlipayFreezeResponse testAlipayFreezeResponse = alipayFreezeResponseList.get(alipayFreezeResponseList.size() - 1);
        assertThat(testAlipayFreezeResponse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAlipayFreezeResponse.getMsg()).isEqualTo(DEFAULT_MSG);
        assertThat(testAlipayFreezeResponse.getSubCode()).isEqualTo(DEFAULT_SUB_CODE);
        assertThat(testAlipayFreezeResponse.getSubMsg()).isEqualTo(DEFAULT_SUB_MSG);
        assertThat(testAlipayFreezeResponse.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testAlipayFreezeResponse.getParamsStr()).isEqualTo(DEFAULT_PARAMS_STR);
        assertThat(testAlipayFreezeResponse.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAlipayFreezeResponse.getAuthNo()).isEqualTo(DEFAULT_AUTH_NO);
        assertThat(testAlipayFreezeResponse.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testAlipayFreezeResponse.getFundAmount()).isEqualTo(DEFAULT_FUND_AMOUNT);
        assertThat(testAlipayFreezeResponse.getGmtTrans()).isEqualTo(DEFAULT_GMT_TRANS);
        assertThat(testAlipayFreezeResponse.getOperationId()).isEqualTo(DEFAULT_OPERATION_ID);
        assertThat(testAlipayFreezeResponse.getOutOrderNo()).isEqualTo(DEFAULT_OUT_ORDER_NO);
        assertThat(testAlipayFreezeResponse.getOutRequestNo()).isEqualTo(DEFAULT_OUT_REQUEST_NO);
        assertThat(testAlipayFreezeResponse.getPayerUserId()).isEqualTo(DEFAULT_PAYER_USER_ID);
        assertThat(testAlipayFreezeResponse.getPreAuthType()).isEqualTo(DEFAULT_PRE_AUTH_TYPE);
        assertThat(testAlipayFreezeResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAlipayFreezeResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alipayFreezeResponseRepository.findAll().size();

        // Create the AlipayFreezeResponse with an existing ID
        alipayFreezeResponse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlipayFreezeResponseMockMvc.perform(post("/api/alipay-freeze-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeResponse)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFreezeResponse in the database
        List<AlipayFreezeResponse> alipayFreezeResponseList = alipayFreezeResponseRepository.findAll();
        assertThat(alipayFreezeResponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAlipayFreezeResponses() throws Exception {
        // Initialize the database
        alipayFreezeResponseRepository.saveAndFlush(alipayFreezeResponse);

        // Get all the alipayFreezeResponseList
        restAlipayFreezeResponseMockMvc.perform(get("/api/alipay-freeze-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alipayFreezeResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].msg").value(hasItem(DEFAULT_MSG.toString())))
            .andExpect(jsonPath("$.[*].subCode").value(hasItem(DEFAULT_SUB_CODE.toString())))
            .andExpect(jsonPath("$.[*].subMsg").value(hasItem(DEFAULT_SUB_MSG.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())))
            .andExpect(jsonPath("$.[*].paramsStr").value(hasItem(DEFAULT_PARAMS_STR.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].authNo").value(hasItem(DEFAULT_AUTH_NO.toString())))
            .andExpect(jsonPath("$.[*].creditAmount").value(hasItem(DEFAULT_CREDIT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].fundAmount").value(hasItem(DEFAULT_FUND_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].gmtTrans").value(hasItem(DEFAULT_GMT_TRANS.toString())))
            .andExpect(jsonPath("$.[*].operationId").value(hasItem(DEFAULT_OPERATION_ID.toString())))
            .andExpect(jsonPath("$.[*].outOrderNo").value(hasItem(DEFAULT_OUT_ORDER_NO.toString())))
            .andExpect(jsonPath("$.[*].outRequestNo").value(hasItem(DEFAULT_OUT_REQUEST_NO.toString())))
            .andExpect(jsonPath("$.[*].payerUserId").value(hasItem(DEFAULT_PAYER_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].preAuthType").value(hasItem(DEFAULT_PRE_AUTH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAlipayFreezeResponse() throws Exception {
        // Initialize the database
        alipayFreezeResponseRepository.saveAndFlush(alipayFreezeResponse);

        // Get the alipayFreezeResponse
        restAlipayFreezeResponseMockMvc.perform(get("/api/alipay-freeze-responses/{id}", alipayFreezeResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alipayFreezeResponse.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.msg").value(DEFAULT_MSG.toString()))
            .andExpect(jsonPath("$.subCode").value(DEFAULT_SUB_CODE.toString()))
            .andExpect(jsonPath("$.subMsg").value(DEFAULT_SUB_MSG.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()))
            .andExpect(jsonPath("$.paramsStr").value(DEFAULT_PARAMS_STR.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.authNo").value(DEFAULT_AUTH_NO.toString()))
            .andExpect(jsonPath("$.creditAmount").value(DEFAULT_CREDIT_AMOUNT.toString()))
            .andExpect(jsonPath("$.fundAmount").value(DEFAULT_FUND_AMOUNT.toString()))
            .andExpect(jsonPath("$.gmtTrans").value(DEFAULT_GMT_TRANS.toString()))
            .andExpect(jsonPath("$.operationId").value(DEFAULT_OPERATION_ID.toString()))
            .andExpect(jsonPath("$.outOrderNo").value(DEFAULT_OUT_ORDER_NO.toString()))
            .andExpect(jsonPath("$.outRequestNo").value(DEFAULT_OUT_REQUEST_NO.toString()))
            .andExpect(jsonPath("$.payerUserId").value(DEFAULT_PAYER_USER_ID.toString()))
            .andExpect(jsonPath("$.preAuthType").value(DEFAULT_PRE_AUTH_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlipayFreezeResponse() throws Exception {
        // Get the alipayFreezeResponse
        restAlipayFreezeResponseMockMvc.perform(get("/api/alipay-freeze-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlipayFreezeResponse() throws Exception {
        // Initialize the database
        alipayFreezeResponseService.save(alipayFreezeResponse);

        int databaseSizeBeforeUpdate = alipayFreezeResponseRepository.findAll().size();

        // Update the alipayFreezeResponse
        AlipayFreezeResponse updatedAlipayFreezeResponse = alipayFreezeResponseRepository.findById(alipayFreezeResponse.getId()).get();
        // Disconnect from session so that the updates on updatedAlipayFreezeResponse are not directly saved in db
        em.detach(updatedAlipayFreezeResponse);
        updatedAlipayFreezeResponse
            .code(UPDATED_CODE)
            .msg(UPDATED_MSG)
            .subCode(UPDATED_SUB_CODE)
            .subMsg(UPDATED_SUB_MSG)
            .body(UPDATED_BODY)
            .paramsStr(UPDATED_PARAMS_STR)
            .amount(UPDATED_AMOUNT)
            .authNo(UPDATED_AUTH_NO)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .fundAmount(UPDATED_FUND_AMOUNT)
            .gmtTrans(UPDATED_GMT_TRANS)
            .operationId(UPDATED_OPERATION_ID)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payerUserId(UPDATED_PAYER_USER_ID)
            .preAuthType(UPDATED_PRE_AUTH_TYPE)
            .status(UPDATED_STATUS);

        restAlipayFreezeResponseMockMvc.perform(put("/api/alipay-freeze-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlipayFreezeResponse)))
            .andExpect(status().isOk());

        // Validate the AlipayFreezeResponse in the database
        List<AlipayFreezeResponse> alipayFreezeResponseList = alipayFreezeResponseRepository.findAll();
        assertThat(alipayFreezeResponseList).hasSize(databaseSizeBeforeUpdate);
        AlipayFreezeResponse testAlipayFreezeResponse = alipayFreezeResponseList.get(alipayFreezeResponseList.size() - 1);
        assertThat(testAlipayFreezeResponse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAlipayFreezeResponse.getMsg()).isEqualTo(UPDATED_MSG);
        assertThat(testAlipayFreezeResponse.getSubCode()).isEqualTo(UPDATED_SUB_CODE);
        assertThat(testAlipayFreezeResponse.getSubMsg()).isEqualTo(UPDATED_SUB_MSG);
        assertThat(testAlipayFreezeResponse.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testAlipayFreezeResponse.getParamsStr()).isEqualTo(UPDATED_PARAMS_STR);
        assertThat(testAlipayFreezeResponse.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAlipayFreezeResponse.getAuthNo()).isEqualTo(UPDATED_AUTH_NO);
        assertThat(testAlipayFreezeResponse.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testAlipayFreezeResponse.getFundAmount()).isEqualTo(UPDATED_FUND_AMOUNT);
        assertThat(testAlipayFreezeResponse.getGmtTrans()).isEqualTo(UPDATED_GMT_TRANS);
        assertThat(testAlipayFreezeResponse.getOperationId()).isEqualTo(UPDATED_OPERATION_ID);
        assertThat(testAlipayFreezeResponse.getOutOrderNo()).isEqualTo(UPDATED_OUT_ORDER_NO);
        assertThat(testAlipayFreezeResponse.getOutRequestNo()).isEqualTo(UPDATED_OUT_REQUEST_NO);
        assertThat(testAlipayFreezeResponse.getPayerUserId()).isEqualTo(UPDATED_PAYER_USER_ID);
        assertThat(testAlipayFreezeResponse.getPreAuthType()).isEqualTo(UPDATED_PRE_AUTH_TYPE);
        assertThat(testAlipayFreezeResponse.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAlipayFreezeResponse() throws Exception {
        int databaseSizeBeforeUpdate = alipayFreezeResponseRepository.findAll().size();

        // Create the AlipayFreezeResponse

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlipayFreezeResponseMockMvc.perform(put("/api/alipay-freeze-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFreezeResponse)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFreezeResponse in the database
        List<AlipayFreezeResponse> alipayFreezeResponseList = alipayFreezeResponseRepository.findAll();
        assertThat(alipayFreezeResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlipayFreezeResponse() throws Exception {
        // Initialize the database
        alipayFreezeResponseService.save(alipayFreezeResponse);

        int databaseSizeBeforeDelete = alipayFreezeResponseRepository.findAll().size();

        // Delete the alipayFreezeResponse
        restAlipayFreezeResponseMockMvc.perform(delete("/api/alipay-freeze-responses/{id}", alipayFreezeResponse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlipayFreezeResponse> alipayFreezeResponseList = alipayFreezeResponseRepository.findAll();
        assertThat(alipayFreezeResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlipayFreezeResponse.class);
        AlipayFreezeResponse alipayFreezeResponse1 = new AlipayFreezeResponse();
        alipayFreezeResponse1.setId(1L);
        AlipayFreezeResponse alipayFreezeResponse2 = new AlipayFreezeResponse();
        alipayFreezeResponse2.setId(alipayFreezeResponse1.getId());
        assertThat(alipayFreezeResponse1).isEqualTo(alipayFreezeResponse2);
        alipayFreezeResponse2.setId(2L);
        assertThat(alipayFreezeResponse1).isNotEqualTo(alipayFreezeResponse2);
        alipayFreezeResponse1.setId(null);
        assertThat(alipayFreezeResponse1).isNotEqualTo(alipayFreezeResponse2);
    }
}
