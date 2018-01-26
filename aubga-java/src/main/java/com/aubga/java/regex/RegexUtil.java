package com.aubga.java.regex;

import java.util.regex.Matcher;

public class RegexUtil {
	
	/**
	 * 将字符串中的完整域名替换为target字符
	 * @param source
	 * @param target
	 * @param regex
	 * @return
	 */
	public static String replaceDomain(String source,Matcher m,String target) {
	//	String curDate = source;
		//boolean isExisted = false;
		while(m.find()) {
		//	isExisted = true;
			String domain = m.group();
			if(domain.endsWith(".cn")|domain.endsWith(".com")|domain.endsWith(".org")) {
				source = source.replace(domain, target);
			}
		}
		/*if(isExisted) {
			System.out.println(curDate+"--"+"changto-->"+source);
		}*/
		return source;
	}
}
