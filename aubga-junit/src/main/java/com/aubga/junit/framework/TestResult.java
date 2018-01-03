package com.aubga.junit.framework;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
	protected List<TestFailure> fFailures;
	protected List<TestFailure> fErrors;
	protected List<TestListener> fListeners;
	
	public TestResult() {
		fFailures = new ArrayList<TestFailure>();
		fErrors = new ArrayList<TestFailure>();
		fListeners = new ArrayList<TestListener>();
		
	}
	
	//提供追加error、failure的功能；同时作为观察者模式，肯定需要添加关于listener的操作;
	
	public synchronized void addError(Test test,Throwable e) {
		fErrors.add(new TestFailure(test,e));
		for(TestListener each : cloneListeners()) {
			each.addError(test, e);
		}
	}

	public synchronized void addFailure(Test test,AssertionFailedError e) {
		fFailures.add(new TestFailure(test,e));
	}
	
	public synchronized void addListener(TestListener listener) {
		this.fListeners.add(listener);
	}
	public synchronized void removeListener(TestListener listener) {
		fListeners.remove(listener);
	}
	
	private synchronized List<TestListener> cloneListeners() {
		List<TestListener> result = new ArrayList<TestListener>();
		result.addAll(fListeners);
		return result;
	}
	
}
