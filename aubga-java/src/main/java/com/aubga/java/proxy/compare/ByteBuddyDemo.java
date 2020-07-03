/*package com.aubga.java.proxy.compare;

public class ByteBuddyDemo {

	public static void main(String[] args) {

		Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value("Hello World!")).make()
				.load(Object.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();

		System.out.println(dynamicType.getSimpleName());
		// 输出：Object$ByteBuddy$ilIxkTl1
	}
}
*/