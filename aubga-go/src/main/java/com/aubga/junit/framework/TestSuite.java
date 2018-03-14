package com.aubga.junit.framework;

public class TestSuite implements Test {
	private String name;
	
	
	public TestSuite(String name) {
		setName(name);
	}
	public TestSuite(Class<?> theClass,String name) {
		this(theClass);
		setName(name);
	}
	public TestSuite(Class<?>...classes) {
		for(Class<?> theClass : classes) {
			//do ???
		}
	}
	public TestSuite(Class<?> theClass) {
		addTestsFromTestCase(theClass);
	}
	
	private void addTestsFromTestCase(Class<?> theClass) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int countTestCases() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void run(TestResult result) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
