package util;

import java.sql.*;


public class DBUtils {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://myblog?useUnicode=true&characterEncoding=UTF-8";
	private static String user="root";
	private static String password="123456";
	
	private static Connection connection;
	private static Statement statement;
	/**
	 * 连接数据库
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws Exception{
		Class.forName(driver);
		connection=(Connection) DriverManager.getConnection(url,user,password);
	
		return connection;
	}


	/**
	 * 执行查询语句
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet doQuery(String sql){
		try {
			connection = getConnection();
			statement = (Statement) connection.createStatement();
			return statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 执行增删改
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int doUpdate(String sql)	throws Exception{
		connection=getConnection();
		statement=(Statement) connection.createStatement();
		
		return statement.executeUpdate(sql);
	}
	/**
	 * 释放资源,
	 */
	public static void dispose(){
		try {
			if(statement!=null)
				statement.close();
			if(connection!=null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public static void Close(Statement st, ResultSet rs, Connection conn) {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(conn != null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
