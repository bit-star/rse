package com.lazulite.rse.web.rest;

import com.lazulite.rse.RseApp;
import com.lazulite.rse.domain.AlipayUser;
import com.lazulite.rse.repository.AlipayUserRepository;
import com.lazulite.rse.service.AlipayUserService;
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
 * Integration tests for the {@link AlipayUserResource} REST controller.
 */
@SpringBootTest(classes = RseApp.class)
public class AlipayUserResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESS_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTH_TOKEN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_AUTH_TOKEN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPIRES_IN = "AAAAAAAAAA";
    private static final String UPDATED_EXPIRES_IN = "BBBBBBBBBB";

    private static final String DEFAULT_ALIPAY_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ALIPAY_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RE_EXPIRES_IN = "AAAAAAAAAA";
    private static final String UPDATED_RE_EXPIRES_IN = "BBBBBBBBBB";

    private static final String DEFAULT_REFRESH_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_REFRESH_TOKEN = "BBBBBBBBBB";

    @Autowired
    private AlipayUserRepository alipayUserRepository;

    @Autowired
    private AlipayUserService alipayUserService;

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

    private MockMvc restAlipayUserMockMvc;

    private AlipayUser alipayUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlipayUserResource alipayUserResource = new AlipayUserResource(alipayUserService);
        this.restAlipayUserMockMvc = MockMvcBuilders.standaloneSetup(alipayUserResource)
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
    public static AlipayUser createEntity(EntityManager em) {
        AlipayUser alipayUser = new AlipayUser()
            .userId(DEFAULT_USER_ID)
            .avatar(DEFAULT_AVATAR)
            .nickName(DEFAULT_NICK_NAME)
            .mobile(DEFAULT_MOBILE)
            .gender(DEFAULT_GENDER)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .accessToken(DEFAULT_ACCESS_TOKEN)
            .authTokenType(DEFAULT_AUTH_TOKEN_TYPE)
            .expiresIn(DEFAULT_EXPIRES_IN)
            .alipayUserId(DEFAULT_ALIPAY_USER_ID)
            .reExpiresIn(DEFAULT_RE_EXPIRES_IN)
            .refreshToken(DEFAULT_REFRESH_TOKEN);
        return alipayUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlipayUser createUpdatedEntity(EntityManager em) {
        AlipayUser alipayUser = new AlipayUser()
            .userId(UPDATED_USER_ID)
            .avatar(UPDATED_AVATAR)
            .nickName(UPDATED_NICK_NAME)
            .mobile(UPDATED_MOBILE)
            .gender(UPDATED_GENDER)
            .countryCode(UPDATED_COUNTRY_CODE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .accessToken(UPDATED_ACCESS_TOKEN)
            .authTokenType(UPDATED_AUTH_TOKEN_TYPE)
            .expiresIn(UPDATED_EXPIRES_IN)
            .alipayUserId(UPDATED_ALIPAY_USER_ID)
            .reExpiresIn(UPDATED_RE_EXPIRES_IN)
            .refreshToken(UPDATED_REFRESH_TOKEN);
        return alipayUser;
    }

    @BeforeEach
    public void initTest() {
        alipayUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlipayUser() throws Exception {
        int databaseSizeBeforeCreate = alipayUserRepository.findAll().size();

        // Create the AlipayUser
        restAlipayUserMockMvc.perform(post("/api/alipay-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayUser)))
            .andExpect(status().isCreated());

        // Validate the AlipayUser in the database
        List<AlipayUser> alipayUserList = alipayUserRepository.findAll();
        assertThat(alipayUserList).hasSize(databaseSizeBeforeCreate + 1);
        AlipayUser testAlipayUser = alipayUserList.get(alipayUserList.size() - 1);
        assertThat(testAlipayUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAlipayUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testAlipayUser.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testAlipayUser.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testAlipayUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAlipayUser.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testAlipayUser.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testAlipayUser.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAlipayUser.getAccessToken()).isEqualTo(DEFAULT_ACCESS_TOKEN);
        assertThat(testAlipayUser.getAuthTokenType()).isEqualTo(DEFAULT_AUTH_TOKEN_TYPE);
        assertThat(testAlipayUser.getExpiresIn()).isEqualTo(DEFAULT_EXPIRES_IN);
        assertThat(testAlipayUser.getAlipayUserId()).isEqualTo(DEFAULT_ALIPAY_USER_ID);
        assertThat(testAlipayUser.getReExpiresIn()).isEqualTo(DEFAULT_RE_EXPIRES_IN);
        assertThat(testAlipayUser.getRefreshToken()).isEqualTo(DEFAULT_REFRESH_TOKEN);
    }

    @Test
    @Transactional
    public void createAlipayUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alipayUserRepository.findAll().size();

        // Create the AlipayUser with an existing ID
        alipayUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlipayUserMockMvc.perform(post("/api/alipay-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayUser)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayUser in the database
        List<AlipayUser> alipayUserList = alipayUserRepository.findAll();
        assertThat(alipayUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAlipayUsers() throws Exception {
        // Initialize the database
        alipayUserRepository.saveAndFlush(alipayUser);

        // Get all the alipayUserList
        restAlipayUserMockMvc.perform(get("/api/alipay-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alipayUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].accessToken").value(hasItem(DEFAULT_ACCESS_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].authTokenType").value(hasItem(DEFAULT_AUTH_TOKEN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].expiresIn").value(hasItem(DEFAULT_EXPIRES_IN.toString())))
            .andExpect(jsonPath("$.[*].alipayUserId").value(hasItem(DEFAULT_ALIPAY_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].reExpiresIn").value(hasItem(DEFAULT_RE_EXPIRES_IN.toString())))
            .andExpect(jsonPath("$.[*].refreshToken").value(hasItem(DEFAULT_REFRESH_TOKEN.toString())));
    }
    
    @Test
    @Transactional
    public void getAlipayUser() throws Exception {
        // Initialize the database
        alipayUserRepository.saveAndFlush(alipayUser);

        // Get the alipayUser
        restAlipayUserMockMvc.perform(get("/api/alipay-users/{id}", alipayUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alipayUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.accessToken").value(DEFAULT_ACCESS_TOKEN.toString()))
            .andExpect(jsonPath("$.authTokenType").value(DEFAULT_AUTH_TOKEN_TYPE.toString()))
            .andExpect(jsonPath("$.expiresIn").value(DEFAULT_EXPIRES_IN.toString()))
            .andExpect(jsonPath("$.alipayUserId").value(DEFAULT_ALIPAY_USER_ID.toString()))
            .andExpect(jsonPath("$.reExpiresIn").value(DEFAULT_RE_EXPIRES_IN.toString()))
            .andExpect(jsonPath("$.refreshToken").value(DEFAULT_REFRESH_TOKEN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlipayUser() throws Exception {
        // Get the alipayUser
        restAlipayUserMockMvc.perform(get("/api/alipay-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlipayUser() throws Exception {
        // Initialize the database
        alipayUserService.save(alipayUser);

        int databaseSizeBeforeUpdate = alipayUserRepository.findAll().size();

        // Update the alipayUser
        AlipayUser updatedAlipayUser = alipayUserRepository.findById(alipayUser.getId()).get();
        // Disconnect from session so that the updates on updatedAlipayUser are not directly saved in db
        em.detach(updatedAlipayUser);
        updatedAlipayUser
            .userId(UPDATED_USER_ID)
            .avatar(UPDATED_AVATAR)
            .nickName(UPDATED_NICK_NAME)
            .mobile(UPDATED_MOBILE)
            .gender(UPDATED_GENDER)
            .countryCode(UPDATED_COUNTRY_CODE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .accessToken(UPDATED_ACCESS_TOKEN)
            .authTokenType(UPDATED_AUTH_TOKEN_TYPE)
            .expiresIn(UPDATED_EXPIRES_IN)
            .alipayUserId(UPDATED_ALIPAY_USER_ID)
            .reExpiresIn(UPDATED_RE_EXPIRES_IN)
            .refreshToken(UPDATED_REFRESH_TOKEN);

        restAlipayUserMockMvc.perform(put("/api/alipay-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlipayUser)))
            .andExpect(status().isOk());

        // Validate the AlipayUser in the database
        List<AlipayUser> alipayUserList = alipayUserRepository.findAll();
        assertThat(alipayUserList).hasSize(databaseSizeBeforeUpdate);
        AlipayUser testAlipayUser = alipayUserList.get(alipayUserList.size() - 1);
        assertThat(testAlipayUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAlipayUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testAlipayUser.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testAlipayUser.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testAlipayUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAlipayUser.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testAlipayUser.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testAlipayUser.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAlipayUser.getAccessToken()).isEqualTo(UPDATED_ACCESS_TOKEN);
        assertThat(testAlipayUser.getAuthTokenType()).isEqualTo(UPDATED_AUTH_TOKEN_TYPE);
        assertThat(testAlipayUser.getExpiresIn()).isEqualTo(UPDATED_EXPIRES_IN);
        assertThat(testAlipayUser.getAlipayUserId()).isEqualTo(UPDATED_ALIPAY_USER_ID);
        assertThat(testAlipayUser.getReExpiresIn()).isEqualTo(UPDATED_RE_EXPIRES_IN);
        assertThat(testAlipayUser.getRefreshToken()).isEqualTo(UPDATED_REFRESH_TOKEN);
    }

    @Test
    @Transactional
    public void updateNonExistingAlipayUser() throws Exception {
        int databaseSizeBeforeUpdate = alipayUserRepository.findAll().size();

        // Create the AlipayUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlipayUserMockMvc.perform(put("/api/alipay-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alipayUser)))
            .andExpect(status().isBadRequest());

        // Validate the AlipayUser in the database
        List<AlipayUser> alipayUserList = alipayUserRepository.findAll();
        assertThat(alipayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlipayUser() throws Exception {
        // Initialize the database
        alipayUserService.save(alipayUser);

        int databaseSizeBeforeDelete = alipayUserRepository.findAll().size();

        // Delete the alipayUser
        restAlipayUserMockMvc.perform(delete("/api/alipay-users/{id}", alipayUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlipayUser> alipayUserList = alipayUserRepository.findAll();
        assertThat(alipayUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlipayUser.class);
        AlipayUser alipayUser1 = new AlipayUser();
        alipayUser1.setId(1L);
        AlipayUser alipayUser2 = new AlipayUser();
        alipayUser2.setId(alipayUser1.getId());
        assertThat(alipayUser1).isEqualTo(alipayUser2);
        alipayUser2.setId(2L);
        assertThat(alipayUser1).isNotEqualTo(alipayUser2);
        alipayUser1.setId(null);
        assertThat(alipayUser1).isNotEqualTo(alipayUser2);
    }
}
