package com.scc.qa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Utility {
	public static String serverName;
	public static String loginApproach;
	public static String typeLogin;
	
	@Parameters({ "serverName", "loginApproach", "typeOfLogin" })
	@BeforeTest
	protected void logSetup(String srvrNm, String lgnAprch, String lgnTyp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.setProperty("sys:user.home", dateFormat.format(new Date()));
		System.setProperty("logLocation", "./logs/");
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("before test");
		serverName = srvrNm;
		loginApproach = lgnAprch;
		typeLogin = lgnTyp;
		System.out.println("serverName: " + serverName);
		System.out.println("loginApproach: " + loginApproach);
		System.out.println("typeLogin: " + typeLogin);
	}

//	@Parameters({ "serverName", "loginApproach", "typeOfLogin" })
//	public static void getData(String srvrNm, String lgnAprch, String lgnTyp) {
//		System.out.println("before test");
//		serverName = srvrNm;
//		loginApproach = lgnAprch;
//		typeLogin = lgnTyp;
//		System.out.println("serverName: " + serverName);
//		System.out.println("loginApproach: " + loginApproach);
//		System.out.println("typeLogin: " + typeLogin);
//
//	}
}
