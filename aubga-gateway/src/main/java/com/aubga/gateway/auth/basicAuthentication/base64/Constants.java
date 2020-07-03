package com.aubga.gateway.auth.basicAuthentication.base64;


/**
 * 公用常量类。
 * 
 * @author wangwch
 * @since 1.0, 2011-02-11
 */
public abstract class Constants {

	/** AOP默认时间格式 **/
	public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

	/** AOP Date默认时区 **/
	public static final String DATE_TIMEZONE = "GMT+8";

	/** UTF-8字符集 **/
	public static final String CHARSET_UTF8 = "UTF-8";

	/** GBK字符集 **/
	public static final String CHARSET_GBK = "GBK";

	/** AOP JSON 应格式 */
	public static final String FORMAT_JSON = "json";
	/** AOP XML 应格式 */
	public static final String FORMAT_XML = "xml";

	/** MD5签名方式 */
	public static final String SIGN_METHOD_MD5 = "md5";
	/** HMAC签名方式 */
	public static final String SIGN_METHOD_HMAC = "hmac";
	/** HMAC签名方式 */
	public static final String SIGN_METHOD_DSA = "dsa";

	/** 当前使用的签名版本号 */
	public static final String SIGN_V = "1";

	/** SDK版本号 */
	public static final String SDK_VERSION = "aop-sdk-java-20110125";
	
}
