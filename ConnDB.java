package Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/***
 * MYSQL连接类
 * @author HZ
 * @since 2017年7月4日 21:40:22
 */
public class ConnDB {
	private ResultSet rs = null;
	private Connection conn = null;
	private Statement stam = null;
	String driver = "com.mysql.jdbc.Driver";
	String url = null;
	String userName = null;
	String password = null;

	public ConnDB(String url,String userName,String password,String database) {
		this.url="jdbc:mysql://"+url+":3306/"+database+"?useUnicode=true&characterEncoding=utf8";

		this.userName=userName;
		this.password=password;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(this.url, this.userName, this.password);
			stam=conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * 根据stamtment执行sql
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql) {
		try {
			rs = stam.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 根据stamtment执行sql
	 * @param sql
	 * @return
	 */
	public void executeUpdate(String sql) {
		//System.out.println("update数据库");
		try {
			stam.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭connection
	 */
	public void close() {
		try {
			 if(null != stam)  
	            {  
				 stam.close();  
				 stam = null ;  
	            }  
			 if (null != conn) {
					conn.close();
					conn=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}