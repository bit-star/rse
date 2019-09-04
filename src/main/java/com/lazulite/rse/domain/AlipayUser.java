package com.lazulite.rse.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AlipayUser.
 */
@Entity
@Table(name = "alipay_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlipayUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private String gender;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "auth_token_type")
    private String authTokenType;

    @Column(name = "expires_in")
    private String expiresIn;

    @Column(name = "alipay_user_id")
    private String alipayUserId;

    @Column(name = "re_expires_in")
    private String reExpiresIn;

    @Column(name = "refresh_token")
    private String refreshToken;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public AlipayUser userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public AlipayUser avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public AlipayUser nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public AlipayUser mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public AlipayUser gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public AlipayUser countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return province;
    }

    public AlipayUser province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public AlipayUser city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AlipayUser accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthTokenType() {
        return authTokenType;
    }

    public AlipayUser authTokenType(String authTokenType) {
        this.authTokenType = authTokenType;
        return this;
    }

    public void setAuthTokenType(String authTokenType) {
        this.authTokenType = authTokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public AlipayUser expiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public AlipayUser alipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
        return this;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    public String getReExpiresIn() {
        return reExpiresIn;
    }

    public AlipayUser reExpiresIn(String reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
        return this;
    }

    public void setReExpiresIn(String reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public AlipayUser refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlipayUser)) {
            return false;
        }
        return id != null && id.equals(((AlipayUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlipayUser{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", gender='" + getGender() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", accessToken='" + getAccessToken() + "'" +
            ", authTokenType='" + getAuthTokenType() + "'" +
            ", expiresIn='" + getExpiresIn() + "'" +
            ", alipayUserId='" + getAlipayUserId() + "'" +
            ", reExpiresIn='" + getReExpiresIn() + "'" +
            ", refreshToken='" + getRefreshToken() + "'" +
            "}";
    }
}
