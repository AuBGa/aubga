package com.aubga.junit.runner;

import com.aubga.junit.framework.AssertionFailedError;
import com.aubga.junit.framework.Test;
import com.aubga.junit.framework.TestListener;

public abstract class BaseTestRunner implements TestListener {

	@Override
	public void addError(Test test, Throwable e) {
		testFailed(TestRunListener.STATUS_ERROR,test,e);
	}

	protected abstract void testFailed(int statusError, Test test, Throwable e) ;

	@Override
	public void addFailure(Test test, AssertionFailedError e) {
		testFailed(TestRunListener.STATUS_FAILURE, test, e);
	}

	@Override
	public void startTest(Test test) {
		this.testStarted(test.toString());
	}

	public abstract void testStarted(String string);
	public abstract void testEnded(String string);

	@Override
	public void endTest(Test test) {
		this.testEnded(test.toString());
	}

}
