package com.aubga.junit.framework;

public class TestFailure {
	protected Test fFailedTest;
	protected Throwable fThrownException;
	
	public TestFailure() {
		
	}
	
	public TestFailure(Test fFailedTest, Throwable fThrownException) {
		super();
		this.fFailedTest = fFailedTest;
		this.fThrownException = fThrownException;
	}
	public Test getfFailedTest() {
		return fFailedTest;
	}
	public void setfFailedTest(Test fFailedTest) {
		this.fFailedTest = fFailedTest;
	}
	public Throwable getfThrownException() {
		return fThrownException;
	}
	public void setfThrownException(Throwable fThrownException) {
		this.fThrownException = fThrownException;
	}
	
	
}	
