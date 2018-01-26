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
import java.util.Objects;

/**
 * 
 * @author JinPengJia
 *
 */
public class Index {

    private  String tableName;
    private  boolean nonUnique;
    private  String keyName;
    private  List<String> columnNames;
    
    public Index() {}
    
    public Index(
             String tableName,
             boolean nonUnique,
             String keyName,
             List<String> columnNames) {
        this.tableName = tableName;
        this.nonUnique = nonUnique;
        this.keyName = keyName;
        this.columnNames = columnNames;
    }

    private boolean isPrimaryKey() {
        return this.keyName.equals("PRIMARY");
    }

    private boolean isUniqueKey() {
        return !this.nonUnique;
    }

    private String getColumnString() {
        return "`" + ListUtils.implode("`, `", this.columnNames) + "`";
    }

    private String getSetPrimaryKeyStatement() {
        return "ALTER TABLE `" + tableName + "` ADD PRIMARY KEY("
                + getColumnString()
                + ");";
    }

    private String getAddUniqueStatement() {
        return "ALTER TABLE `" + tableName + "` ADD UNIQUE "
                + "`" + this.keyName + "` ("
                + getColumnString()
                + ");";
    }

    private String getAddIndex() {
        return "ALTER TABLE `" + tableName + "` ADD INDEX "
                + "`" + this.keyName + "` ("
                + getColumnString()
                + ");";
    }

    public List<String> getColumnNames() {
        return this.columnNames;
    }

    public String getKeyName() {
        return this.keyName;
    }


    public String getCreateStatement() {
        if (isPrimaryKey()) {
            //Primary key
            return getSetPrimaryKeyStatement();
        } else if (isUniqueKey()) {
            //Unique key
            return getAddUniqueStatement();
        } else {
            //Just a key/index
            return getAddIndex();
        }
    }

    public String getDeleteStatament() {
        if (isPrimaryKey()) {
            return "ALTER TABLE `" + tableName + "` DROP PRIMARY KEY;";
        } else {
            return "DROP INDEX `" + keyName + "` ON `" + tableName + "`;";
        }
    }

    public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isNonUnique() {
		return nonUnique;
	}

	public void setNonUnique(boolean nonUnique) {
		this.nonUnique = nonUnique;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	@Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.tableName);
        hash = 97 * hash + (this.nonUnique ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.keyName);
        hash = 97 * hash + Objects.hashCode(this.columnNames);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
         Index other = (Index) obj;
        if (this.nonUnique != other.nonUnique) {
            return false;
        }
        if (!Objects.equals(this.tableName, other.tableName)) {
            return false;
        }
        if (!Objects.equals(this.keyName, other.keyName)) {
            return false;
        }
        return Objects.equals(this.columnNames, other.columnNames);
    }

    @Override
    public String toString() {
        return "Index{" + "tableName=" + tableName + ", nonUnique=" + nonUnique + ", keyName=" + keyName + ", columnNames=" + columnNames + '}';
    }

}
