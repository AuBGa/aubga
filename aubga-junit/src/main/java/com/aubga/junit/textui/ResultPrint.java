package com.aubga.junit.textui;

import java.io.PrintStream;

import com.aubga.junit.framework.AssertionFailedError;
import com.aubga.junit.framework.Test;
import com.aubga.junit.framework.TestListener;
import com.aubga.junit.framework.TestResult;

public class ResultPrint implements TestListener {
	PrintStream fWriter;
	
	public ResultPrint(PrintStream writer) {
		this.fWriter = writer;
	}
	
	@Override
	public void addError(Test test, Throwable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFailure(Test test, AssertionFailedError e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startTest(Test test) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endTest(Test test) {
		// TODO Auto-generated method stub

	}

	public void print(TestResult result, long runTime) {
		// TODO Auto-generated method stub
		
	}

}
