package com.aubga.junit.textui;

import java.io.PrintStream;

import com.aubga.junit.framework.Test;
import com.aubga.junit.framework.TestCase;
import com.aubga.junit.framework.TestResult;
import com.aubga.junit.runner.BaseTestRunner;

public class TestRunner extends BaseTestRunner {
	
	private ResultPrint fPrinter;
/*	public static final int SUCCESS_EXIT = 0;
	public static final int FAILURE_EXIT = 1;
	public static final int EXCEPTION_EXIT = 2;*/
	
	public TestRunner() {
		this(System.out);
	}
	public TestRunner(PrintStream writer) {
		this(new ResultPrint(writer));
	}
	
	public TestRunner(ResultPrint fPrinter) {
		this.fPrinter = fPrinter;
	}
	@Override
	protected void testFailed(int statusError, Test test, Throwable e) {
	}

	@Override
	public void testStarted(String string) {
	}

	@Override
	public void testEnded(String string) {
	}
	
	static public void run(Class<? extends TestCase> testClass) {
		
	}
	
	static public TestResult run(Test test) {
		TestRunner runner = new TestRunner();
		return runner.doRun(test);
	}
	
	public TestResult doRun(Test test) {
		return doRun(test,false);
	}
	
	public TestResult doRun(Test suite,boolean wait) {
		TestResult  result = createTestResult();
		result.addListener(fPrinter);
		long startTime = System.currentTimeMillis();
		suite.run(result);
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;
		
		fPrinter.print(result,runTime);
		return result;
	
	}
	private TestResult createTestResult() {
		return new TestResult();
	}
	
}
