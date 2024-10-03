package com.scc.qa.testcases;

import org.testng.annotations.Test;

import com.scc.qa.base.GetParas;

public class Testing {
	
	
	@Test
	void checkParasValue() {
		System.out.println("Hello from Testing class");
		System.out.println("server name: "+GetParas.serverName);
	}
	

}
