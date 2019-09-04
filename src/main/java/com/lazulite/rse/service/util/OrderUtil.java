package com.lazulite.rse.service.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtil {

	/**
	 * 订单号
	 * @return
     */
	public static String getOrderNo() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
		return df.format(date)+getRandomNum(5);
	}


    /**
     * 订单号凭证号
     * @return
     */
    public static String getCertificate() {
        return getRandomNum(4)+" "+getRandomNum(4)+" "+getRandomNum(2);
    }



    /**
	 * 随机数
	 * @param length
	 * @return
     */
	public static String getRandomNum(int length) { //length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 随机字符串
	 * @param length
	 * @return
     */
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
//
//	public static void main(String[] args) {
//		System.out.println(OrderUtil.getOrderNo());
//	}



}
