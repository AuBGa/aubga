package com.aubga.pattern.inter;

import java.util.List;

public interface ExtraStatementStrategy<T> extends StatementStrategy<T> {
	
	public List<String> update(T from, T to);
	
}
