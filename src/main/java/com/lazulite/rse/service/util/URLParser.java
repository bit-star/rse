package com.lazulite.rse.service.util;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    protected byte type;
    protected static final byte TYPE_URL = 1;
    protected static final byte TYPE_QUERY_STRING = 2;
    protected String url;
    protected String baseUrl;
    protected String queryString;
    protected String label;
    protected String charset = "utf-8";

    protected boolean compiled = false;
    public Map<String, String> parsedParams;
    protected URLDecoder urld = new URLDecoder();

    public static URLParser fromURL(String url) {
        URLParser parser = new URLParser();

        parser.type = 1;
        parser.url = url;

        String[] split = url.split("\\?", 2);
        parser.baseUrl = split[0];
        parser.queryString = (split.length > 1 ? split[1] : "");

        String[] split2 = url.split("#", 2);
        parser.label = (split2.length > 1 ? split2[1] : null);

        return parser;
    }

    public static URLParser fromQueryString(String queryString) {
        URLParser parser = new URLParser();

        parser.type = 2;
        parser.queryString = queryString;

        return parser;
    }

    public URLParser useCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public URLParser compile() throws UnsupportedEncodingException {
        if (this.compiled) {
            return this;
        }
        String paramString = this.queryString.split("#")[0];
        String[] params = paramString.split("&");

        this.parsedParams = new HashMap<String, String>(params.length);
        for (String p : params) {
            String[] kv = p.split("=");
            if (kv.length == 2) {
                this.parsedParams.put(kv[0], URLDecoder.decode(kv[1], this.charset));
            }
        }
        this.compiled = true;

        return this;
    }

    public String getParameter(String name) {
        if (this.compiled) {
            return (String) this.parsedParams.get(name);
        }
        String paramString = this.queryString.split("#")[0];
        Matcher match = Pattern.compile("(^|&)" + name + "=([^&]*)").matcher(paramString);
        match.lookingAt();

        return match.group(2);
    }

    public URLParser setParameter(String name, String value) throws UnsupportedEncodingException {
        if (!this.compiled) {
            compile();
        }
        this.parsedParams.put(name, value);

        return this;
    }



    public static void main(String[] args) {
        try {


            System.err.println(
                fromURL("https://www.google.com/search?q=test&hl=zh_cn&oq=test&gs_l=heirloom-serp.3...38011332.38012012.0.38012235.4.4.0.0.0.0.0.0..0.0.msedr...0...1ac.1.34.heirloom-serp..4.0.0.1q6YK2r8vHI")
                    .compile().getParameter("gs_l"));



            String body = "alipay_root_cert_sn=687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6&alipay_sdk=alipay-sdk-java-4.5.0.ALL&app_cert_sn=a867fa3452b141b071360785fa2f0e18&app_id=2019082066289920&biz_content=%7B%22amount%22%3A%220.01%22%2C%22extra_param%22%3A%22%7B%5C%22category%5C%22%3A%5C%22RENT_PHONE%5C%22%2C%5C%22outStoreCode%5C%22%3A%5C%22test001%5C%22%2C%5C%22outStoreAlias%5C%22%3A%5C%22%E9%99%88%E4%BF%8A%E7%94%AB%E7%9A%84%E6%B5%8B%E8%AF%95%E6%95%B0%E7%A0%81%5C%22%7D%22%2C%22order_title%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9D%E9%A2%84%E6%8E%88%E6%9D%83%22%2C%22out_order_no%22%3A%22demo_1755942426165764%22%2C%22out_request_no%22%3A%221567350396%22%2C%22payee_user_id%22%3A%222088631087255902%22%2C%22product_code%22%3A%22PRE_AUTH_ONLINE%22%7D&charset=UTF-8&format=json&method=alipay.fund.auth.order.app.freeze&notify_url=https%3A%2F%2Foapiuat.fosun.com%2Frs%2Fapi%2Falipay%2Fnotify&sign=G79ABCIXHPC1B8YFCPC3YSxHQDVx%2B4AhC7GkIP10Y8O%2FHOvazW6tt9jqNsfCG8h2clP1keFRHBrDVTZrwkDh%2BU5tO3k4nX8WhEoAHNY4cX8Ah7inyEjMGcV5%2ByW22kez%2FfvLS9U7tjIZn0fG4FNuwk0oocpkZUO6icoO6J%2B%2FAh2j1p%2B20nYR94AgjvwcyG7f4MyXl%2Frw4fjS22o6Cr9nOq%2BAuu3NaSf2s3ecIh1jaJDFNExAGMN1FEcJ7Zbwfqvh02osPgVRuSXpivF1xijYleScGkHT3Kfyn3pSxKOexAlW5nLKxvMigXlIvD3WxH5xJgzpIOOzyWYe42IiqWEUbQ%3D%3D&sign_type=RSA2&timestamp=2019-09-01+23%3A06%3A36&version=1.0";
            String[] paramsStr = body.split("&");
            HashMap<String,String> paramsMaps = new HashMap<>();
            for (String param : paramsStr) {
                String[] keyValue = param.split("=");
                paramsMaps.put(keyValue[0], keyValue[1]);
            }
            String biz_content = paramsMaps.get("biz_content");
            biz_content = URLDecoder.decode(biz_content, "UTF-8");
            Object parse = JSON.parse(biz_content);
            System.out.println(JSON.toJSONString(parse));

            System.out.println(
                fromURL("https://www.google.com/search?q=test&hl=zh_cn&oq=test&gs_l=heirloom-serp.3...38011332.38012012.0.38012235.4.4.0.0.0.0.0.0..0.0.msedr...0...1ac.1.34.heirloom-serp..4.0.0.1q6YK2r8vHI")
                    .compile().getParameter("q"));


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
