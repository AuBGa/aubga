package com.aubga.mockito;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PersonServiceTest {

    private PersonDao     mockDao;
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        //模拟PersonDao对象
        mockDao = mock(PersonDao.class);
        when(mockDao.getPerson(1)).thenReturn(new Person(1, "Person1"));
        when(mockDao.update(isA(Person.class))).thenReturn(true);

        
        doReturn(new Person(1,"Person_aubga")).when(mockDao).getPerson(2);
        personService = new PersonService(mockDao);
    }

    @Test
    public void testUpdate() throws Exception {
        boolean result = personService.update(1, "new name");
        assertTrue("must true", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, times(1)).update(isA(Person.class));
    }

    @Test
    public void testUpdateNotFind() throws Exception {
        boolean result = personService.update(2, "new name");
        assertFalse("must true", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, never()).update(isA(Person.class));
    }
}