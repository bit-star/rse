package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.AlipayFundAuthInfo;
import com.lazulite.rse.repository.AlipayFundAuthInfoRepository;
import com.lazulite.rse.service.AlipayFundAuthInfoService;
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
 * Integration tests for the {@link AlipayFundAuthInfoResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class AlipayFundAuthInfoResourceIT {

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

    private static final String DEFAULT_EXTRA_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_GMT_CREATE_STR = "AAAAAAAAAA";
    private static final String UPDATED_GMT_CREATE_STR = "BBBBBBBBBB";

    private static final String DEFAULT_GMT_TRANS_STR = "AAAAAAAAAA";
    private static final String UPDATED_GMT_TRANS_STR = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_REQUEST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_LOGON_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_LOGON_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRE_AUTH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRE_AUTH_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_REST_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REST_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_REST_CREDIT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REST_CREDIT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_REST_FUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REST_FUND_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_FREEZE_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_FREEZE_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_FREEZE_CREDIT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_FREEZE_CREDIT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_FREEZE_FUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_FREEZE_FUND_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_PAY_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_PAY_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_PAY_CREDIT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_PAY_CREDIT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_PAY_FUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_PAY_FUND_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TRANS_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_CURRENCY = "BBBBBBBBBB";

    @Autowired
    private AlipayFundAuthInfoRepository alipayFundAuthInfoRepository;

    @Autowired
    private AlipayFundAuthInfoService alipayFundAuthInfoService;

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

    private MockMvc restAlipayFundAuthInfoMockMvc;

    private AlipayFundAuthInfo alipayFundAuthInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlipayFundAuthInfoResource alipayFundAuthInfoResource = new AlipayFundAuthInfoResource(alipayFundAuthInfoService);
        this.restAlipayFundAuthInfoMockMvc = MockMvcBuilders.standaloneSetup(alipayFundAuthInfoResource)
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
    public static AlipayFundAuthInfo createEntity(EntityManager em) {
        AlipayFundAuthInfo alipayFundAuthInfo = new AlipayFundAuthInfo()
            .code(DEFAULT_CODE)
            .msg(DEFAULT_MSG)
            .subCode(DEFAULT_SUB_CODE)
            .subMsg(DEFAULT_SUB_MSG)
            .body(DEFAULT_BODY)
            .paramsStr(DEFAULT_PARAMS_STR)
            .amount(DEFAULT_AMOUNT)
            .authNo(DEFAULT_AUTH_NO)
            .creditAmount(DEFAULT_CREDIT_AMOUNT)
            .extraParam(DEFAULT_EXTRA_PARAM)
            .fundAmount(DEFAULT_FUND_AMOUNT)
            .gmtCreateStr(DEFAULT_GMT_CREATE_STR)
            .gmtTransStr(DEFAULT_GMT_TRANS_STR)
            .operationId(DEFAULT_OPERATION_ID)
            .operationType(DEFAULT_OPERATION_TYPE)
            .orderTitle(DEFAULT_ORDER_TITLE)
            .outOrderNo(DEFAULT_OUT_ORDER_NO)
            .outRequestNo(DEFAULT_OUT_REQUEST_NO)
            .payerLogonId(DEFAULT_PAYER_LOGON_ID)
            .payerUserId(DEFAULT_PAYER_USER_ID)
            .preAuthType(DEFAULT_PRE_AUTH_TYPE)
            .remark(DEFAULT_REMARK)
            .restAmount(DEFAULT_REST_AMOUNT)
            .restCreditAmount(DEFAULT_REST_CREDIT_AMOUNT)
            .restFundAmount(DEFAULT_REST_FUND_AMOUNT)
            .status(DEFAULT_STATUS)
            .totalFreezeAmount(DEFAULT_TOTAL_FREEZE_AMOUNT)
            .totalFreezeCreditAmount(DEFAULT_TOTAL_FREEZE_CREDIT_AMOUNT)
            .totalFreezeFundAmount(DEFAULT_TOTAL_FREEZE_FUND_AMOUNT)
            .totalPayAmount(DEFAULT_TOTAL_PAY_AMOUNT)
            .totalPayCreditAmount(DEFAULT_TOTAL_PAY_CREDIT_AMOUNT)
            .totalPayFundAmount(DEFAULT_TOTAL_PAY_FUND_AMOUNT)
            .transCurrency(DEFAULT_TRANS_CURRENCY);
        return alipayFundAuthInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlipayFundAuthInfo createUpdatedEntity(EntityManager em) {
        AlipayFundAuthInfo alipayFundAuthInfo = new AlipayFundAuthInfo()
            .code(UPDATED_CODE)
            .msg(UPDATED_MSG)
            .subCode(UPDATED_SUB_CODE)
            .subMsg(UPDATED_SUB_MSG)
            .body(UPDATED_BODY)
            .paramsStr(UPDATED_PARAMS_STR)
            .amount(UPDATED_AMOUNT)
            .authNo(UPDATED_AUTH_NO)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .extraParam(UPDATED_EXTRA_PARAM)
            .fundAmount(UPDATED_FUND_AMOUNT)
            .gmtCreateStr(UPDATED_GMT_CREATE_STR)
            .gmtTransStr(UPDATED_GMT_TRANS_STR)
            .operationId(UPDATED_OPERATION_ID)
            .operationType(UPDATED_OPERATION_TYPE)
            .orderTitle(UPDATED_ORDER_TITLE)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payerLogonId(UPDATED_PAYER_LOGON_ID)
            .payerUserId(UPDATED_PAYER_USER_ID)
            .preAuthType(UPDATED_PRE_AUTH_TYPE)
            .remark(UPDATED_REMARK)
            .restAmount(UPDATED_REST_AMOUNT)
            .restCreditAmount(UPDATED_REST_CREDIT_AMOUNT)
            .restFundAmount(UPDATED_REST_FUND_AMOUNT)
            .status(UPDATED_STATUS)
            .totalFreezeAmount(UPDATED_TOTAL_FREEZE_AMOUNT)
            .totalFreezeCreditAmount(UPDATED_TOTAL_FREEZE_CREDIT_AMOUNT)
            .totalFreezeFundAmount(UPDATED_TOTAL_FREEZE_FUND_AMOUNT)
            .totalPayAmount(UPDATED_TOTAL_PAY_AMOUNT)
            .totalPayCreditAmount(UPDATED_TOTAL_PAY_CREDIT_AMOUNT)
            .totalPayFundAmount(UPDATED_TOTAL_PAY_FUND_AMOUNT)
            .transCurrency(UPDATED_TRANS_CURRENCY);
        return alipayFundAuthInfo;
    }

    @BeforeEach
    public void initTest() {
        alipayFundAuthInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlipayFundAuthInfo() throws Exception {
        int databaseSizeBeforeCreate = alipayFundAuthInfoRepository.findAll().size();

        // Create the AlipayFundAuthInfo
        restAlipayFundAuthInfoMockMvc.perform(post("/api/alipay-fund-auth-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFundAuthInfo)))
            .andExpect(status().isCreated());

        // Validate the AlipayFundAuthInfo in the database
        List<AlipayFundAuthInfo> alipayFundAuthInfoList = alipayFundAuthInfoRepository.findAll();
        assertThat(alipayFundAuthInfoList).hasSize(databaseSizeBeforeCreate + 1);
        AlipayFundAuthInfo testAlipayFundAuthInfo = alipayFundAuthInfoList.get(alipayFundAuthInfoList.size() - 1);
        assertThat(testAlipayFundAuthInfo.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAlipayFundAuthInfo.getMsg()).isEqualTo(DEFAULT_MSG);
        assertThat(testAlipayFundAuthInfo.getSubCode()).isEqualTo(DEFAULT_SUB_CODE);
        assertThat(testAlipayFundAuthInfo.getSubMsg()).isEqualTo(DEFAULT_SUB_MSG);
        assertThat(testAlipayFundAuthInfo.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testAlipayFundAuthInfo.getParamsStr()).isEqualTo(DEFAULT_PARAMS_STR);
        assertThat(testAlipayFundAuthInfo.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getAuthNo()).isEqualTo(DEFAULT_AUTH_NO);
        assertThat(testAlipayFundAuthInfo.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getExtraParam()).isEqualTo(DEFAULT_EXTRA_PARAM);
        assertThat(testAlipayFundAuthInfo.getFundAmount()).isEqualTo(DEFAULT_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getGmtCreateStr()).isEqualTo(DEFAULT_GMT_CREATE_STR);
        assertThat(testAlipayFundAuthInfo.getGmtTransStr()).isEqualTo(DEFAULT_GMT_TRANS_STR);
        assertThat(testAlipayFundAuthInfo.getOperationId()).isEqualTo(DEFAULT_OPERATION_ID);
        assertThat(testAlipayFundAuthInfo.getOperationType()).isEqualTo(DEFAULT_OPERATION_TYPE);
        assertThat(testAlipayFundAuthInfo.getOrderTitle()).isEqualTo(DEFAULT_ORDER_TITLE);
        assertThat(testAlipayFundAuthInfo.getOutOrderNo()).isEqualTo(DEFAULT_OUT_ORDER_NO);
        assertThat(testAlipayFundAuthInfo.getOutRequestNo()).isEqualTo(DEFAULT_OUT_REQUEST_NO);
        assertThat(testAlipayFundAuthInfo.getPayerLogonId()).isEqualTo(DEFAULT_PAYER_LOGON_ID);
        assertThat(testAlipayFundAuthInfo.getPayerUserId()).isEqualTo(DEFAULT_PAYER_USER_ID);
        assertThat(testAlipayFundAuthInfo.getPreAuthType()).isEqualTo(DEFAULT_PRE_AUTH_TYPE);
        assertThat(testAlipayFundAuthInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testAlipayFundAuthInfo.getRestAmount()).isEqualTo(DEFAULT_REST_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getRestCreditAmount()).isEqualTo(DEFAULT_REST_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getRestFundAmount()).isEqualTo(DEFAULT_REST_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeAmount()).isEqualTo(DEFAULT_TOTAL_FREEZE_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeCreditAmount()).isEqualTo(DEFAULT_TOTAL_FREEZE_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeFundAmount()).isEqualTo(DEFAULT_TOTAL_FREEZE_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayAmount()).isEqualTo(DEFAULT_TOTAL_PAY_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayCreditAmount()).isEqualTo(DEFAULT_TOTAL_PAY_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayFundAmount()).isEqualTo(DEFAULT_TOTAL_PAY_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTransCurrency()).isEqualTo(DEFAULT_TRANS_CURRENCY);
    }

    @Test
    @Transactional
    public void createAlipayFundAuthInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alipayFundAuthInfoRepository.findAll().size();

        // Create the AlipayFundAuthInfo with an existing ID
        alipayFundAuthInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlipayFundAuthInfoMockMvc.perform(post("/api/alipay-fund-auth-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFundAuthInfo)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFundAuthInfo in the database
        List<AlipayFundAuthInfo> alipayFundAuthInfoList = alipayFundAuthInfoRepository.findAll();
        assertThat(alipayFundAuthInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAlipayFundAuthInfos() throws Exception {
        // Initialize the database
        alipayFundAuthInfoRepository.saveAndFlush(alipayFundAuthInfo);

        // Get all the alipayFundAuthInfoList
        restAlipayFundAuthInfoMockMvc.perform(get("/api/alipay-fund-auth-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alipayFundAuthInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].msg").value(hasItem(DEFAULT_MSG.toString())))
            .andExpect(jsonPath("$.[*].subCode").value(hasItem(DEFAULT_SUB_CODE.toString())))
            .andExpect(jsonPath("$.[*].subMsg").value(hasItem(DEFAULT_SUB_MSG.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())))
            .andExpect(jsonPath("$.[*].paramsStr").value(hasItem(DEFAULT_PARAMS_STR.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].authNo").value(hasItem(DEFAULT_AUTH_NO.toString())))
            .andExpect(jsonPath("$.[*].creditAmount").value(hasItem(DEFAULT_CREDIT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].extraParam").value(hasItem(DEFAULT_EXTRA_PARAM.toString())))
            .andExpect(jsonPath("$.[*].fundAmount").value(hasItem(DEFAULT_FUND_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].gmtCreateStr").value(hasItem(DEFAULT_GMT_CREATE_STR.toString())))
            .andExpect(jsonPath("$.[*].gmtTransStr").value(hasItem(DEFAULT_GMT_TRANS_STR.toString())))
            .andExpect(jsonPath("$.[*].operationId").value(hasItem(DEFAULT_OPERATION_ID.toString())))
            .andExpect(jsonPath("$.[*].operationType").value(hasItem(DEFAULT_OPERATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].orderTitle").value(hasItem(DEFAULT_ORDER_TITLE.toString())))
            .andExpect(jsonPath("$.[*].outOrderNo").value(hasItem(DEFAULT_OUT_ORDER_NO.toString())))
            .andExpect(jsonPath("$.[*].outRequestNo").value(hasItem(DEFAULT_OUT_REQUEST_NO.toString())))
            .andExpect(jsonPath("$.[*].payerLogonId").value(hasItem(DEFAULT_PAYER_LOGON_ID.toString())))
            .andExpect(jsonPath("$.[*].payerUserId").value(hasItem(DEFAULT_PAYER_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].preAuthType").value(hasItem(DEFAULT_PRE_AUTH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].restAmount").value(hasItem(DEFAULT_REST_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].restCreditAmount").value(hasItem(DEFAULT_REST_CREDIT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].restFundAmount").value(hasItem(DEFAULT_REST_FUND_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].totalFreezeAmount").value(hasItem(DEFAULT_TOTAL_FREEZE_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].totalFreezeCreditAmount").value(hasItem(DEFAULT_TOTAL_FREEZE_CREDIT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].totalFreezeFundAmount").value(hasItem(DEFAULT_TOTAL_FREEZE_FUND_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].totalPayAmount").value(hasItem(DEFAULT_TOTAL_PAY_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].totalPayCreditAmount").value(hasItem(DEFAULT_TOTAL_PAY_CREDIT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].totalPayFundAmount").value(hasItem(DEFAULT_TOTAL_PAY_FUND_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].transCurrency").value(hasItem(DEFAULT_TRANS_CURRENCY.toString())));
    }
    
    @Test
    @Transactional
    public void getAlipayFundAuthInfo() throws Exception {
        // Initialize the database
        alipayFundAuthInfoRepository.saveAndFlush(alipayFundAuthInfo);

        // Get the alipayFundAuthInfo
        restAlipayFundAuthInfoMockMvc.perform(get("/api/alipay-fund-auth-infos/{id}", alipayFundAuthInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alipayFundAuthInfo.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.msg").value(DEFAULT_MSG.toString()))
            .andExpect(jsonPath("$.subCode").value(DEFAULT_SUB_CODE.toString()))
            .andExpect(jsonPath("$.subMsg").value(DEFAULT_SUB_MSG.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()))
            .andExpect(jsonPath("$.paramsStr").value(DEFAULT_PARAMS_STR.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.authNo").value(DEFAULT_AUTH_NO.toString()))
            .andExpect(jsonPath("$.creditAmount").value(DEFAULT_CREDIT_AMOUNT.toString()))
            .andExpect(jsonPath("$.extraParam").value(DEFAULT_EXTRA_PARAM.toString()))
            .andExpect(jsonPath("$.fundAmount").value(DEFAULT_FUND_AMOUNT.toString()))
            .andExpect(jsonPath("$.gmtCreateStr").value(DEFAULT_GMT_CREATE_STR.toString()))
            .andExpect(jsonPath("$.gmtTransStr").value(DEFAULT_GMT_TRANS_STR.toString()))
            .andExpect(jsonPath("$.operationId").value(DEFAULT_OPERATION_ID.toString()))
            .andExpect(jsonPath("$.operationType").value(DEFAULT_OPERATION_TYPE.toString()))
            .andExpect(jsonPath("$.orderTitle").value(DEFAULT_ORDER_TITLE.toString()))
            .andExpect(jsonPath("$.outOrderNo").value(DEFAULT_OUT_ORDER_NO.toString()))
            .andExpect(jsonPath("$.outRequestNo").value(DEFAULT_OUT_REQUEST_NO.toString()))
            .andExpect(jsonPath("$.payerLogonId").value(DEFAULT_PAYER_LOGON_ID.toString()))
            .andExpect(jsonPath("$.payerUserId").value(DEFAULT_PAYER_USER_ID.toString()))
            .andExpect(jsonPath("$.preAuthType").value(DEFAULT_PRE_AUTH_TYPE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.restAmount").value(DEFAULT_REST_AMOUNT.toString()))
            .andExpect(jsonPath("$.restCreditAmount").value(DEFAULT_REST_CREDIT_AMOUNT.toString()))
            .andExpect(jsonPath("$.restFundAmount").value(DEFAULT_REST_FUND_AMOUNT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.totalFreezeAmount").value(DEFAULT_TOTAL_FREEZE_AMOUNT.toString()))
            .andExpect(jsonPath("$.totalFreezeCreditAmount").value(DEFAULT_TOTAL_FREEZE_CREDIT_AMOUNT.toString()))
            .andExpect(jsonPath("$.totalFreezeFundAmount").value(DEFAULT_TOTAL_FREEZE_FUND_AMOUNT.toString()))
            .andExpect(jsonPath("$.totalPayAmount").value(DEFAULT_TOTAL_PAY_AMOUNT.toString()))
            .andExpect(jsonPath("$.totalPayCreditAmount").value(DEFAULT_TOTAL_PAY_CREDIT_AMOUNT.toString()))
            .andExpect(jsonPath("$.totalPayFundAmount").value(DEFAULT_TOTAL_PAY_FUND_AMOUNT.toString()))
            .andExpect(jsonPath("$.transCurrency").value(DEFAULT_TRANS_CURRENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlipayFundAuthInfo() throws Exception {
        // Get the alipayFundAuthInfo
        restAlipayFundAuthInfoMockMvc.perform(get("/api/alipay-fund-auth-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlipayFundAuthInfo() throws Exception {
        // Initialize the database
        alipayFundAuthInfoService.save(alipayFundAuthInfo);

        int databaseSizeBeforeUpdate = alipayFundAuthInfoRepository.findAll().size();

        // Update the alipayFundAuthInfo
        AlipayFundAuthInfo updatedAlipayFundAuthInfo = alipayFundAuthInfoRepository.findById(alipayFundAuthInfo.getId()).get();
        // Disconnect from session so that the updates on updatedAlipayFundAuthInfo are not directly saved in db
        em.detach(updatedAlipayFundAuthInfo);
        updatedAlipayFundAuthInfo
            .code(UPDATED_CODE)
            .msg(UPDATED_MSG)
            .subCode(UPDATED_SUB_CODE)
            .subMsg(UPDATED_SUB_MSG)
            .body(UPDATED_BODY)
            .paramsStr(UPDATED_PARAMS_STR)
            .amount(UPDATED_AMOUNT)
            .authNo(UPDATED_AUTH_NO)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .extraParam(UPDATED_EXTRA_PARAM)
            .fundAmount(UPDATED_FUND_AMOUNT)
            .gmtCreateStr(UPDATED_GMT_CREATE_STR)
            .gmtTransStr(UPDATED_GMT_TRANS_STR)
            .operationId(UPDATED_OPERATION_ID)
            .operationType(UPDATED_OPERATION_TYPE)
            .orderTitle(UPDATED_ORDER_TITLE)
            .outOrderNo(UPDATED_OUT_ORDER_NO)
            .outRequestNo(UPDATED_OUT_REQUEST_NO)
            .payerLogonId(UPDATED_PAYER_LOGON_ID)
            .payerUserId(UPDATED_PAYER_USER_ID)
            .preAuthType(UPDATED_PRE_AUTH_TYPE)
            .remark(UPDATED_REMARK)
            .restAmount(UPDATED_REST_AMOUNT)
            .restCreditAmount(UPDATED_REST_CREDIT_AMOUNT)
            .restFundAmount(UPDATED_REST_FUND_AMOUNT)
            .status(UPDATED_STATUS)
            .totalFreezeAmount(UPDATED_TOTAL_FREEZE_AMOUNT)
            .totalFreezeCreditAmount(UPDATED_TOTAL_FREEZE_CREDIT_AMOUNT)
            .totalFreezeFundAmount(UPDATED_TOTAL_FREEZE_FUND_AMOUNT)
            .totalPayAmount(UPDATED_TOTAL_PAY_AMOUNT)
            .totalPayCreditAmount(UPDATED_TOTAL_PAY_CREDIT_AMOUNT)
            .totalPayFundAmount(UPDATED_TOTAL_PAY_FUND_AMOUNT)
            .transCurrency(UPDATED_TRANS_CURRENCY);

        restAlipayFundAuthInfoMockMvc.perform(put("/api/alipay-fund-auth-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlipayFundAuthInfo)))
            .andExpect(status().isOk());

        // Validate the AlipayFundAuthInfo in the database
        List<AlipayFundAuthInfo> alipayFundAuthInfoList = alipayFundAuthInfoRepository.findAll();
        assertThat(alipayFundAuthInfoList).hasSize(databaseSizeBeforeUpdate);
        AlipayFundAuthInfo testAlipayFundAuthInfo = alipayFundAuthInfoList.get(alipayFundAuthInfoList.size() - 1);
        assertThat(testAlipayFundAuthInfo.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAlipayFundAuthInfo.getMsg()).isEqualTo(UPDATED_MSG);
        assertThat(testAlipayFundAuthInfo.getSubCode()).isEqualTo(UPDATED_SUB_CODE);
        assertThat(testAlipayFundAuthInfo.getSubMsg()).isEqualTo(UPDATED_SUB_MSG);
        assertThat(testAlipayFundAuthInfo.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testAlipayFundAuthInfo.getParamsStr()).isEqualTo(UPDATED_PARAMS_STR);
        assertThat(testAlipayFundAuthInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getAuthNo()).isEqualTo(UPDATED_AUTH_NO);
        assertThat(testAlipayFundAuthInfo.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getExtraParam()).isEqualTo(UPDATED_EXTRA_PARAM);
        assertThat(testAlipayFundAuthInfo.getFundAmount()).isEqualTo(UPDATED_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getGmtCreateStr()).isEqualTo(UPDATED_GMT_CREATE_STR);
        assertThat(testAlipayFundAuthInfo.getGmtTransStr()).isEqualTo(UPDATED_GMT_TRANS_STR);
        assertThat(testAlipayFundAuthInfo.getOperationId()).isEqualTo(UPDATED_OPERATION_ID);
        assertThat(testAlipayFundAuthInfo.getOperationType()).isEqualTo(UPDATED_OPERATION_TYPE);
        assertThat(testAlipayFundAuthInfo.getOrderTitle()).isEqualTo(UPDATED_ORDER_TITLE);
        assertThat(testAlipayFundAuthInfo.getOutOrderNo()).isEqualTo(UPDATED_OUT_ORDER_NO);
        assertThat(testAlipayFundAuthInfo.getOutRequestNo()).isEqualTo(UPDATED_OUT_REQUEST_NO);
        assertThat(testAlipayFundAuthInfo.getPayerLogonId()).isEqualTo(UPDATED_PAYER_LOGON_ID);
        assertThat(testAlipayFundAuthInfo.getPayerUserId()).isEqualTo(UPDATED_PAYER_USER_ID);
        assertThat(testAlipayFundAuthInfo.getPreAuthType()).isEqualTo(UPDATED_PRE_AUTH_TYPE);
        assertThat(testAlipayFundAuthInfo.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testAlipayFundAuthInfo.getRestAmount()).isEqualTo(UPDATED_REST_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getRestCreditAmount()).isEqualTo(UPDATED_REST_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getRestFundAmount()).isEqualTo(UPDATED_REST_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeAmount()).isEqualTo(UPDATED_TOTAL_FREEZE_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeCreditAmount()).isEqualTo(UPDATED_TOTAL_FREEZE_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalFreezeFundAmount()).isEqualTo(UPDATED_TOTAL_FREEZE_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayAmount()).isEqualTo(UPDATED_TOTAL_PAY_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayCreditAmount()).isEqualTo(UPDATED_TOTAL_PAY_CREDIT_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTotalPayFundAmount()).isEqualTo(UPDATED_TOTAL_PAY_FUND_AMOUNT);
        assertThat(testAlipayFundAuthInfo.getTransCurrency()).isEqualTo(UPDATED_TRANS_CURRENCY);
    }

    @Test
    @Transactional
    public void updateNonExistingAlipayFundAuthInfo() throws Exception {
        int databaseSizeBeforeUpdate = alipayFundAuthInfoRepository.findAll().size();

        // Create the AlipayFundAuthInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlipayFundAuthInfoMockMvc.perform(put("/api/alipay-fund-auth-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayFundAuthInfo)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayFundAuthInfo in the database
        List<AlipayFundAuthInfo> alipayFundAuthInfoList = alipayFundAuthInfoRepository.findAll();
        assertThat(alipayFundAuthInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlipayFundAuthInfo() throws Exception {
        // Initialize the database
        alipayFundAuthInfoService.save(alipayFundAuthInfo);

        int databaseSizeBeforeDelete = alipayFundAuthInfoRepository.findAll().size();

        // Delete the alipayFundAuthInfo
        restAlipayFundAuthInfoMockMvc.perform(delete("/api/alipay-fund-auth-infos/{id}", alipayFundAuthInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlipayFundAuthInfo> alipayFundAuthInfoList = alipayFundAuthInfoRepository.findAll();
        assertThat(alipayFundAuthInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlipayFundAuthInfo.class);
        AlipayFundAuthInfo alipayFundAuthInfo1 = new AlipayFundAuthInfo();
        alipayFundAuthInfo1.setId(1L);
        AlipayFundAuthInfo alipayFundAuthInfo2 = new AlipayFundAuthInfo();
        alipayFundAuthInfo2.setId(alipayFundAuthInfo1.getId());
        assertThat(alipayFundAuthInfo1).isEqualTo(alipayFundAuthInfo2);
        alipayFundAuthInfo2.setId(2L);
        assertThat(alipayFundAuthInfo1).isNotEqualTo(alipayFundAuthInfo2);
        alipayFundAuthInfo1.setId(null);
        assertThat(alipayFundAuthInfo1).isNotEqualTo(alipayFundAuthInfo2);
    }
}
