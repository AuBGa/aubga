/*
 *  Copyright (c) 2013 James Buncle
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.aubga.pattern.inter;

import java.util.List;

/**
 * 表对象<br/>
 * tableName:表名称<br/>
 * columns:列集合<br/>
 * indexes:索引集合<br/>
 * ddlSql:表的DDL语句,这里拿空间换了时间<br/>
 * @author JinPengJia
 *
 */
public class Table {

    private  String tableName;
    private  List<Column> columns;
    private  List<Index> indexes;
    private  String ddlSql;

    public Table() {}
    public Table(
             String tableName,
             List<Column> columns,
             List<Index> indexes,
             String ddlSql) {
        this.tableName = tableName;
        this.columns = columns;
        this.indexes = indexes;
        this.ddlSql = ddlSql;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public Index getIndex(final String keyName) {
        for (final Index index : getIndexes()) {
            if (index.getKeyName().equals(keyName)) {
                return index;
            }
        }
        return null;
    }

    public List<Index> getIndexes() {
        return this.indexes;
    }

    public Column getColumn(final String columnName) {
        for (final Column column : this.getColumns()) {
            if (column.getName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public String getTableName() {
        return tableName;
    }

    public String getDdlSql() {
		return ddlSql;
	}

    public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public void setIndexes(List<Index> indexes) {
		this.indexes = indexes;
	}
	public void setDdlSql(String ddlSql) {
		this.ddlSql = ddlSql;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columns == null) ? 0 : columns.hashCode());
		result = prime * result + ((ddlSql == null) ? 0 : ddlSql.hashCode());
		result = prime * result + ((indexes == null) ? 0 : indexes.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (columns == null) {
			if (other.columns != null)
				return false;
		} else if (!columns.equals(other.columns))
			return false;
		if (ddlSql == null) {
			if (other.ddlSql != null)
				return false;
		} else if (!ddlSql.equals(other.ddlSql))
			return false;
		if (indexes == null) {
			if (other.indexes != null)
				return false;
		} else if (!indexes.equals(other.indexes))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
}
