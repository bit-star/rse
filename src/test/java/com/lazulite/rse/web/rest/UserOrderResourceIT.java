package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.UserOrder;
import com.lazulite.rse.repository.UserOrderRepository;
import com.lazulite.rse.service.UserOrderService;
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

import com.lazulite.rse.domain.enumeration.OrderStatus;
/**
 * Integration tests for the {@link UserOrderResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class UserOrderResourceIT {

    private static final String DEFAULT_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NO = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_STATUS = OrderStatus.PendingPayment;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.PendingConfirming;

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGISTICS_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_LOGISTICS_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SHIPMENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FREIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_FREIGHT = new BigDecimal(2);
    private static final BigDecimal SMALLER_FREIGHT = new BigDecimal(1 - 1);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESSING_OPINIONS = "AAAAAAAAAA";
    private static final String UPDATED_PROCESSING_OPINIONS = "BBBBBBBBBB";

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserOrderService userOrderService;

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

    private MockMvc restUserOrderMockMvc;

    private UserOrder userOrder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserOrderResource userOrderResource = new UserOrderResource(userOrderService);
        this.restUserOrderMockMvc = MockMvcBuilders.standaloneSetup(userOrderResource)
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
    public static UserOrder createEntity(EntityManager em) {
        UserOrder userOrder = new UserOrder()
            .orderNo(DEFAULT_ORDER_NO)
            .requestNo(DEFAULT_REQUEST_NO)
            .status(DEFAULT_STATUS)
            .receiver(DEFAULT_RECEIVER)
            .mobile(DEFAULT_MOBILE)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .region(DEFAULT_REGION)
            .address(DEFAULT_ADDRESS)
            .storeName(DEFAULT_STORE_NAME)
            .logisticsCompany(DEFAULT_LOGISTICS_COMPANY)
            .shipmentNumber(DEFAULT_SHIPMENT_NUMBER)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .freight(DEFAULT_FREIGHT)
            .description(DEFAULT_DESCRIPTION)
            .processingOpinions(DEFAULT_PROCESSING_OPINIONS);
        return userOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserOrder createUpdatedEntity(EntityManager em) {
        UserOrder userOrder = new UserOrder()
            .orderNo(UPDATED_ORDER_NO)
            .requestNo(UPDATED_REQUEST_NO)
            .status(UPDATED_STATUS)
            .receiver(UPDATED_RECEIVER)
            .mobile(UPDATED_MOBILE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .region(UPDATED_REGION)
            .address(UPDATED_ADDRESS)
            .storeName(UPDATED_STORE_NAME)
            .logisticsCompany(UPDATED_LOGISTICS_COMPANY)
            .shipmentNumber(UPDATED_SHIPMENT_NUMBER)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .freight(UPDATED_FREIGHT)
            .description(UPDATED_DESCRIPTION)
            .processingOpinions(UPDATED_PROCESSING_OPINIONS);
        return userOrder;
    }

    @BeforeEach
    public void initTest() {
        userOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserOrder() throws Exception {
        int databaseSizeBeforeCreate = userOrderRepository.findAll().size();

        // Create the UserOrder
        restUserOrderMockMvc.perform(post("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userOrder)))
            .andExpect(status().isCreated());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeCreate + 1);
        UserOrder testUserOrder = userOrderList.get(userOrderList.size() - 1);
        assertThat(testUserOrder.getOrderNo()).isEqualTo(DEFAULT_ORDER_NO);
        assertThat(testUserOrder.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testUserOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserOrder.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testUserOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testUserOrder.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testUserOrder.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserOrder.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testUserOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserOrder.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testUserOrder.getLogisticsCompany()).isEqualTo(DEFAULT_LOGISTICS_COMPANY);
        assertThat(testUserOrder.getShipmentNumber()).isEqualTo(DEFAULT_SHIPMENT_NUMBER);
        assertThat(testUserOrder.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testUserOrder.getFreight()).isEqualTo(DEFAULT_FREIGHT);
        assertThat(testUserOrder.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUserOrder.getProcessingOpinions()).isEqualTo(DEFAULT_PROCESSING_OPINIONS);
    }

    @Test
    @Transactional
    public void createUserOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userOrderRepository.findAll().size();

        // Create the UserOrder with an existing ID
        userOrder.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserOrderMockMvc.perform(post("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userOrder)))
            .andExpect(status().isBadRequest());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserOrders() throws Exception {
        // Initialize the database
        userOrderRepository.saveAndFlush(userOrder);

        // Get all the userOrderList
        restUserOrderMockMvc.perform(get("/api/user-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNo").value(hasItem(DEFAULT_ORDER_NO.toString())))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME.toString())))
            .andExpect(jsonPath("$.[*].logisticsCompany").value(hasItem(DEFAULT_LOGISTICS_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].shipmentNumber").value(hasItem(DEFAULT_SHIPMENT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].freight").value(hasItem(DEFAULT_FREIGHT.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].processingOpinions").value(hasItem(DEFAULT_PROCESSING_OPINIONS.toString())));
    }
    
    @Test
    @Transactional
    public void getUserOrder() throws Exception {
        // Initialize the database
        userOrderRepository.saveAndFlush(userOrder);

        // Get the userOrder
        restUserOrderMockMvc.perform(get("/api/user-orders/{id}", userOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userOrder.getId().intValue()))
            .andExpect(jsonPath("$.orderNo").value(DEFAULT_ORDER_NO.toString()))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME.toString()))
            .andExpect(jsonPath("$.logisticsCompany").value(DEFAULT_LOGISTICS_COMPANY.toString()))
            .andExpect(jsonPath("$.shipmentNumber").value(DEFAULT_SHIPMENT_NUMBER.toString()))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD.toString()))
            .andExpect(jsonPath("$.freight").value(DEFAULT_FREIGHT.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.processingOpinions").value(DEFAULT_PROCESSING_OPINIONS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserOrder() throws Exception {
        // Get the userOrder
        restUserOrderMockMvc.perform(get("/api/user-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserOrder() throws Exception {
        // Initialize the database
        userOrderService.save(userOrder);

        int databaseSizeBeforeUpdate = userOrderRepository.findAll().size();

        // Update the userOrder
        UserOrder updatedUserOrder = userOrderRepository.findById(userOrder.getId()).get();
        // Disconnect from session so that the updates on updatedUserOrder are not directly saved in db
        em.detach(updatedUserOrder);
        updatedUserOrder
            .orderNo(UPDATED_ORDER_NO)
            .requestNo(UPDATED_REQUEST_NO)
            .status(UPDATED_STATUS)
            .receiver(UPDATED_RECEIVER)
            .mobile(UPDATED_MOBILE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .region(UPDATED_REGION)
            .address(UPDATED_ADDRESS)
            .storeName(UPDATED_STORE_NAME)
            .logisticsCompany(UPDATED_LOGISTICS_COMPANY)
            .shipmentNumber(UPDATED_SHIPMENT_NUMBER)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .freight(UPDATED_FREIGHT)
            .description(UPDATED_DESCRIPTION)
            .processingOpinions(UPDATED_PROCESSING_OPINIONS);

        restUserOrderMockMvc.perform(put("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserOrder)))
            .andExpect(status().isOk());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeUpdate);
        UserOrder testUserOrder = userOrderList.get(userOrderList.size() - 1);
        assertThat(testUserOrder.getOrderNo()).isEqualTo(UPDATED_ORDER_NO);
        assertThat(testUserOrder.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testUserOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserOrder.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testUserOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testUserOrder.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testUserOrder.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserOrder.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testUserOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserOrder.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testUserOrder.getLogisticsCompany()).isEqualTo(UPDATED_LOGISTICS_COMPANY);
        assertThat(testUserOrder.getShipmentNumber()).isEqualTo(UPDATED_SHIPMENT_NUMBER);
        assertThat(testUserOrder.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testUserOrder.getFreight()).isEqualTo(UPDATED_FREIGHT);
        assertThat(testUserOrder.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUserOrder.getProcessingOpinions()).isEqualTo(UPDATED_PROCESSING_OPINIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingUserOrder() throws Exception {
        int databaseSizeBeforeUpdate = userOrderRepository.findAll().size();

        // Create the UserOrder

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserOrderMockMvc.perform(put("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userOrder)))
            .andExpect(status().isBadRequest());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserOrder() throws Exception {
        // Initialize the database
        userOrderService.save(userOrder);

        int databaseSizeBeforeDelete = userOrderRepository.findAll().size();

        // Delete the userOrder
        restUserOrderMockMvc.perform(delete("/api/user-orders/{id}", userOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserOrder.class);
        UserOrder userOrder1 = new UserOrder();
        userOrder1.setId(1L);
        UserOrder userOrder2 = new UserOrder();
        userOrder2.setId(userOrder1.getId());
        assertThat(userOrder1).isEqualTo(userOrder2);
        userOrder2.setId(2L);
        assertThat(userOrder1).isNotEqualTo(userOrder2);
        userOrder1.setId(null);
        assertThat(userOrder1).isNotEqualTo(userOrder2);
    }
}
