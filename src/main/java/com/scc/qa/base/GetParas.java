package com.scc.qa.base;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class GetParas {
	public static String serverName;
	public static String loginApproach;
	public static String typeLogin;

	@Parameters({ "serverName", "loginApproach", "typeOfLogin" })
	public static void getData(String srvrNm,String lgnAprch,String lgnTyp) {
		System.out.println("before test");
		serverName=srvrNm;
		loginApproach=lgnAprch;
		typeLogin=lgnTyp;
		System.out.println("serverName: "+serverName);
		System.out.println("loginApproach: "+loginApproach);
		System.out.println("typeLogin: "+typeLogin);

	}
}
