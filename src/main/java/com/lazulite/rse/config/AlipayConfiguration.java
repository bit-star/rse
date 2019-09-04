package com.lazulite.rse.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfiguration {

    private final Logger log = LoggerFactory.getLogger(AlipayConfiguration.class);

    @Autowired
    ApplicationProperties applicationProperties;

    @Bean
    public AlipayClient alipayClient(){
        // 请求方式 json
        String FORMAT = "json";
        // 编码格式，目前只支持UTF-8
        String CHARSET = "UTF-8";
        // 签名方式
        String SIGN_TYPE = "RSA2";
        // 网关
        String URL = applicationProperties.getAlipay().getGateway();
        // 商户APP_ID
        String APP_ID = applicationProperties.getAlipay().getAppconfig().getAppid();
        // 商户RSA 私钥
        String APP_PRIVATE_KEY = applicationProperties.getAlipay().getAppconfig().getPrivatekey();
        // 支付宝公钥
//        String ALIPAY_PUBLIC_KEY = applicationProperties.getAlipay().getAppconfig().getPublickey();
        String APP_PUBLIC_CERT_PATH = applicationProperties.getAlipay().getAppconfig().getAppPublicCertPath();
        String ALIPAY_PUBLIC_CERT_PATH = applicationProperties.getAlipay().getAppconfig().getAlipayPublicCertPath();
        String ALIPAY_ROOT_CERT_PATH = applicationProperties.getAlipay().getAppconfig().getAlipayRootCertPath();

        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(URL);
        certAlipayRequest.setAppId(APP_ID);
        certAlipayRequest.setPrivateKey(APP_PRIVATE_KEY);
        certAlipayRequest.setFormat(FORMAT);
        certAlipayRequest.setCharset(CHARSET);
        certAlipayRequest.setSignType(SIGN_TYPE);
        certAlipayRequest.setCertPath(APP_PUBLIC_CERT_PATH);
        certAlipayRequest.setAlipayPublicCertPath(ALIPAY_PUBLIC_CERT_PATH);
        certAlipayRequest.setRootCertPath(ALIPAY_ROOT_CERT_PATH);
        AlipayClient alipayClient = null;
        try {
            alipayClient = new DefaultAlipayClient(certAlipayRequest);
            log.info("创建支付宝网关访问客户端完成, 网关地址：{}，appId:{}", URL, APP_ID);
        } catch (AlipayApiException e) {
            log.error("创建支付宝客户端出现问题",e);
        }
        return alipayClient;
    }

}
