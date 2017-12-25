package com.aubga.mysql;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * jdbc连接数据库
 * @author APPle
 *
 */
public class ConnectionTest {
    //连接数据库的URL
    private  static String url = "jdbc:mysql://10.103.27.10:3306/test1_asset";
    // jdbc协议:数据库子协议:主机:端口/连接的数据库   //

    private  static String user = "root";//用户名
    private  static String password = "123123";//密码


    	public static void main(String[] args) throws ClassNotFoundException, SQLException {
            //通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //1.注册驱动程序(可以注册多个驱动程序)
            //DriverManager.registerDriver(driver);
            //2.连接到具体的数据库
            Connection conn = DriverManager.getConnection(url, user, password);
            
            DatabaseMetaData md =  conn.getMetaData();
            
            ResultSet rs = md.getTables(null, null, null, new String[]{"TABLE","VIEW"});
            while(rs.next()) {
            	String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)   
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知       
                String tableName = rs.getString("TABLE_NAME");  //表名    
                String tableType = rs.getString("TABLE_TYPE");  //表类型,典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。  
                String remarks = rs.getString("REMARKS");       //表备注    
                System.out.println(tableCat + " - " + tableSchemaName + " - " +tableName + " - " + tableType + " - " + remarks); 
            }
            
            ResultSet crs =  md.getColumns(null, null, "ast_user_info", null);
            while(crs.next()) {
            	System.out.println(crs.toString());
            }
            rs = md.getIndexInfo(null, null, "ast_asset_check_record", false, true);    
            while (rs.next()){    
                String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)   
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知       
                String tableName = rs.getString("TABLE_NAME");  //表名    
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 索引值是否可以不唯一,TYPE为 tableIndexStatistic时索引值为 false;  
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//索引类别（可能为空）,TYPE为 tableIndexStatistic 时索引类别为 null;   
                String indexName = rs.getString("INDEX_NAME");//索引的名称 ;TYPE为 tableIndexStatistic 时索引名称为 null;  
                /** 
                 * 索引类型：  
                 *  tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息  
                 *  tableIndexClustered - 此为集群索引  
                 *  tableIndexHashed - 此为散列索引  
                 *  tableIndexOther - 此为某种其他样式的索引  
                 */  
                short type = rs.getShort("TYPE");//索引类型;  
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//在索引列顺序号;TYPE为 tableIndexStatistic 时该序列号为零;  
                String columnName = rs.getString("COLUMN_NAME");//列名;TYPE为 tableIndexStatistic时列名称为 null;  
                String ascOrDesc = rs.getString("ASC_OR_DESC");//列排序顺序:升序还是降序[A:升序; B:降序];如果排序序列不受支持,可能为 null;TYPE为 tableIndexStatistic时排序序列为 null;  
                int cardinality = rs.getInt("CARDINALITY");   //基数;TYPE为 tableIndexStatistic 时,它是表中的行数;否则,它是索引中唯一值的数量。     
                int pages = rs.getInt("PAGES"); //TYPE为 tableIndexStatisic时,它是用于表的页数,否则它是用于当前索引的页数。  
                String filterCondition = rs.getString("FILTER_CONDITION"); //过滤器条件,如果有的话(可能为 null)。  
                  
                System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName + " - " + nonUnique + " - "   
                       + indexQualifier + " - " + indexName + " - " + type + " - " + ordinalPosition + " - " + columnName   
                       + " - " + ascOrDesc + " - " + cardinality + " - " + pages + " - " + filterCondition);       
            } 
            
            Map<String,Class<?>> map =  conn.getTypeMap();
            String schema = conn.getSchema();
            System.out.println(map);
            System.out.println(schema);
		}
}