package com.aubga.mockito.learning;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
public class MockitoOfficalTest {
	@Mock
	List mockAnnoationList;
	
	public MockitoOfficalTest() {
		MockitoAnnotations.initMocks(this);
	}
	@Ignore
	@Test
	public void testBehaviour() {
		List mockedList = mock(List.class);
		
		mockedList.add("1");
		mockedList.clear();
		
		//校验add、clear方法是否被调用
		verify(mockedList).add("1");
		verify(mockedList).clear();
	}
	@Ignore
	@Test
	public void testStubbing() {
		//You can mock concrete classes, not just interfaces
		 LinkedList mockedList = mock(LinkedList.class);
		 //stubbing
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());

		 //following prints "first"
		 System.out.println(mockedList.get(0));

		 //following throws runtime exception
		// System.out.println(mockedList.get(1));

		 //following prints "null" because get(999) was not stubbed
		 System.out.println(mockedList.get(999));

		 //Although it is possible to verify a stubbed invocation, usually it's just redundant
		 //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
		 //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
		 verify(mockedList).get(0);
		
	}
	@Ignore
	@Test
	public void testArgumentMatchers() {
		 LinkedList mockedList = mock(LinkedList.class);
		//stubbing using built-in anyInt() argument matcher
		 when(mockedList.get(anyInt())).thenReturn("element");

		 //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
	//	 when(mockedList.contains(argThat(isValid()))).thenReturn("element");

		 //following prints "element"
		 System.out.println(mockedList.get(999));

		 //you can also verify using an argument matcher
		 verify(mockedList).get(anyInt());

		 //argument matchers can also be written as Java 8 Lambdas
		// verify(mockedList).add(argThat(someString -> someString.length() > 5));

		 
	}

	private ArgumentMatcher<Object> isValid() {
		return null;
	}
	@Ignore
	@Test
	public void testExactOfNumberOfInvocation() {
		 LinkedList mockedList = mock(LinkedList.class);
		 mockedList.add("once");

		 mockedList.add("twice");
		 mockedList.add("twice");

		 mockedList.add("three times");
		 mockedList.add("three times");
		 mockedList.add("three times");

		 //following two verifications work exactly the same - times(1) is used by default
		 verify(mockedList).add("once");
		 verify(mockedList, times(1)).add("once");

		 //exact number of invocations verification
		 verify(mockedList, times(2)).add("twice");
		 verify(mockedList, times(3)).add("three times");

		 //verification using never(). never() is an alias to times(0)
		 verify(mockedList, never()).add("never happened");

		 //verification using atLeast()/atMost()
		 verify(mockedList, atLeastOnce()).add("three times");
		 verify(mockedList, atLeast(2)).add("three times");
		 verify(mockedList, atMost(5)).add("three times");

	}
	
	@Test
	public void testRedundantInvocations() {
		 LinkedList mockedList = mock(LinkedList.class);
		 LinkedList mockedList2 = mock(LinkedList.class);
		 //using mocks
		 mockedList.add("one");
		 mockedList.add("two");
		 verify(mockedList).add("one");
		 verify(mockedList).add("two");
		 
		 //verify(mockedList).add("two");
		 //following verification will fail
		 verifyNoMoreInteractions(mockedList);
		 verifyZeroInteractions(mockedList,mockedList2);
	}
	
	@Test
	public void testTimeout() {
		LinkedList mockList = mock(LinkedList.class,Mockito.RETURNS_SMART_NULLS);
		mockList.add("test");
		mockList.add("test");
		//verify(mockList,timeout(1)).add("test");
		verify(mockList,timeout(1000).times(2)).add("test");
		reset(mockList);
		mockList.add("two");
		verify(mockList,timeout(1000).atLeast(1)).add("two");
		
	}
	@Test
	public void testOneLine() {
		LinkedList mockList = when(mock(LinkedList.class,Mockito.RETURNS_SMART_NULLS).getFirst()).thenReturn("aubga").getMock();
		System.out.println(mockList.getFirst());
		Assert.assertEquals("aubga", mockList.getFirst());
	}
	
	@Test
	public void testSerialiable() {
		LinkedList mockList = mock(LinkedList.class,withSettings().serializable());
		mockList.add("au");
		
	}
	@Test
	public void testCapture() {
		LinkedList mockList = mock(LinkedList.class);
		mockList.add("test");
		ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
		verify(mockList).add(ac.capture());
		System.out.println(ac.getValue());
		assertEquals("test", ac.getValue());
	}
	
	@Test
	public void testAnnoation() {
		mockAnnoationList.add("1");
	}
	
	@Test
    public void answer_with_callback(){
		LinkedList mockList = mock(LinkedList.class);
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:"+args[0];
            }
        });
        assertEquals("hello world:0",mockList.get(0));
        assertEquals("hello world:999",mockList.get(999));
        
        
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock arg0) throws Throwable {
				Object[] args = arg0.getArguments();
				return "hello aubga:"+args[0];
			}
        	
        });
    }
	
	@Test
	public void testMockDetails() {
		
			List  mockedList = mock(LinkedList.class,Mockito.RETURNS_DEEP_STUBS);
			
			when(mockedList.get(anyInt())).thenReturn("aubga");
		//To identify whether a particular object is a mock or a spy:
		 assertTrue(Mockito.mockingDetails(mockedList).isMock());
		 assertFalse(Mockito.mockingDetails(mockedList).isSpy());

		   //Getting details like type to mock or default answer:
		   MockingDetails details = mockingDetails(mockedList);
		
		   System.out.println(details.getMockCreationSettings().getTypeToMock());
		   System.out.println(details.getMockCreationSettings().getDefaultAnswer());

		   //Getting invocations and stubbings of the mock:
		   MockingDetails details2 = mockingDetails(mockedList);
		   System.out.println(details2.getInvocations());
		   System.out.println(details2.getStubbings());

		   //Printing all interactions (including stubbing, unused stubs)
		   System.out.println(mockingDetails(mockedList).printInvocations());
	}
}
