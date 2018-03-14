package com.aubga.junit.framework;

public interface TestListener {
	public void addError(Test test,Throwable e);
	public void addFailure(Test test ,AssertionFailedError e);
	public void startTest(Test test);
	public void endTest(Test test);
}	
