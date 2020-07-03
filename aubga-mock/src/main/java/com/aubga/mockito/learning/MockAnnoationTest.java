package com.aubga.mockito.learning;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockAnnoationTest {
	@Mock
	private List mockList;
	
	@Test
	public void shorthand() {
		mockList.add(1);
		verify(mockList).add(1);
	}
}
