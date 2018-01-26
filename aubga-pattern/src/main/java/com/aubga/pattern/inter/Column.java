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
 * 
 */
package com.aubga.pattern.inter;

/**
 * 
 * @author JinPengJai
 *
 */
public class Column {

    private  String columnName;
    private  String type;
    private  boolean nullable;

    private  String key;
    private  String defaultValue;
    private  String extra;
    private  String collation;
    private  String comment;

    public String getName() {
        return columnName;
    }

    public Column() {}
    public Column(
             String field,
             String type,
             boolean nullable,
             String key,
             String defaultValue,
             String extra,
             String collation,
             String comment) {

        this.columnName = field;
        this.type = type;
        this.nullable = nullable;
        this.key = key;
        this.defaultValue = defaultValue;
        this.extra = extra;
        this.collation = collation;
        this.comment = comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getType() {
        return type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getExtra() {
        return extra;
    }

    public String getCollation() {
        return collation;
    }

    public String getComment() {
        return comment;
    }

    public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collation == null) ? 0 : collation.hashCode());
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + (nullable ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Column other = (Column) obj;
		if (collation == null) {
			if (other.collation != null)
				return false;
		} else if (!collation.equals(other.collation))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (nullable != other.nullable)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "MySQLTableDescription{" + "field=" + columnName + ", type=" + type + ", nullable=" + nullable + ", key=" + key + ", defaultValue=" + defaultValue + ", extra=" + extra + '}';
    }

}
