package com.aubga.java.regex;

import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Test;


public class RegexUtilTest {
	public static String DOMAIN_REGEX="[^//]*?\\.(com|cn|net|org|biz|info|cc|tv|gov|info|co)";//域名的正则表达式
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Pattern domainPattern = Pattern.compile(DOMAIN_REGEX,Pattern.CASE_INSENSITIVE);
		String curData = "htt://www.baidu.com/dd/ddd/xxx.png;htt://www.baidu.com/dd/ddd/xxx.png,htt://www.baidu.com/dd/ddd/xxx.png"	;
		
		curData = RegexUtil.replaceDomain(curData,domainPattern.matcher(curData), "127.0.0.1");
		
		System.out.println(curData);
	}

}
