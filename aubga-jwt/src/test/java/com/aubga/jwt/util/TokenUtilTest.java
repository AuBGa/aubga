package com.aubga.jwt.util;

import org.junit.Assert;
import org.junit.Test;

import com.aubga.jwt.JWTApplicationTests;


/*上面代码重点是, 测试类加@RunWith注解, 还有加上 @SpringBootTest(classes = App.class) 注解, 这里的 App.class 是主程序java类. 主程序java程序必须是SpringBootApplication程序, 否则测试用例会报如下错误:
Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test java.lang.IllegalStateException.
@RunWith是JUnit的一个注解, 用来告诉JUnit不要使用内置的方式进行单元测试, 而应该使用指定的类做单元测试 对于Spring单元测试总是要使用 SpringRunner.class . 
@SpringBootTest 用来指定SpringBoot应用程序的入口类, 该注解默认会根据包名逐级往上找, 一直找到一个SpringBoot主程序class为止, 然后启动该类为单元测试准备Spring上下文环境.  Spring单元测试并不在每个测试方法前都移动一个全新的Spring上下文, 因为这样做太耗费时间, 而是会缓存上下文环境. 如果某个测试方法需要重新准备Spring上下文, 需要在该方法上加 @DirtiesContext 注解. 
@Test注解: JUnit在执行每个测试方法之前, 都会为测试类创建一个新的实例, 这样有助于隔离各个测试方法之前的相互影响.
*/
public class TokenUtilTest extends JWTApplicationTests{
	

	@Test
	public void sign() {
		String sign = TokenUtil.sign(username, password);
		Assert.assertNotNull(sign);
		System.out.println("sign->" + sign);
	}
	
	@Test
	public void verify() {
		boolean verify = TokenUtil.verify(correctSign);
		Assert.assertTrue(verify);
	}
	
	@Test
	public void getUserName() {
		String username = TokenUtil.getUserName(correctSign);
		Assert.assertEquals(username, "admin");
		
	}
	
}
