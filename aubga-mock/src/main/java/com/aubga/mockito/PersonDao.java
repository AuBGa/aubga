package com.aubga.mockito;

public interface PersonDao {
	Person getPerson(int id);

	boolean update(Person person);
}