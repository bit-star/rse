package com.lazulite.rse.config;

import io.github.jhipster.config.JHipsterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Rs.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public ApplicationProperties() {
    }

    private  Alipay alipay = new Alipay();

    public Alipay getAlipay() {
        return alipay;
    }


    public static class Alipay {


        private  String  gateway = "";

        private  Appconfig appconfig =  new Appconfig();

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public Appconfig getAppconfig() {
            return appconfig;
        }

        public Alipay() {
        }

        public static class Appconfig {
            private String  privatekey = "";
            private String  publickey = "";
            private String  appid = "";
            private String  appPublicCertPath = "";
            private String  alipayPublicCertPath = "";
            private String  alipayRootCertPath = "";
            public Appconfig() {
            }

            public String getPrivatekey() {
                return privatekey;
            }

            public void setPrivatekey(String privatekey) {
                this.privatekey = privatekey;
            }

            public String getPublickey() {
                return publickey;
            }

            public void setPublickey(String publickey) {
                this.publickey = publickey;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getAppPublicCertPath() {
                return appPublicCertPath;
            }

            public void setAppPublicCertPath(String appPublicCertPath) {
                this.appPublicCertPath = appPublicCertPath;
            }

            public String getAlipayPublicCertPath() {
                return alipayPublicCertPath;
            }

            public void setAlipayPublicCertPath(String alipayPublicCertPath) {
                this.alipayPublicCertPath = alipayPublicCertPath;
            }

            public String getAlipayRootCertPath() {
                return alipayRootCertPath;
            }

            public void setAlipayRootCertPath(String alipayRootCertPath) {
                this.alipayRootCertPath = alipayRootCertPath;
            }
        }

    }
}
