package com.aubga.junit.framework;

public class TestCase implements Test {
	protected String fName;
	public TestCase(String name) {
		this.fName = name;
	}
	@Override
	public int countTestCases() {
		return 1;
	}

	@Override
	public void run(TestResult result) {

	}

}
