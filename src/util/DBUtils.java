package util;

import java.sql.*;

public class DBUtils {
	private static String driver="com.mysql.jdbc.Driver";
	//数据库的地址和名字
	private static String url="jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static String user="root";
	private static String password="123456";

	private static Connection connection = null;
	private static PreparedStatement pstatement = null;

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			connection= DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 获得执行sql对象的方法
	 * @return
	 * @throws Exception
	 */
	public static PreparedStatement getStatement(String sql) {
		try {
			pstatement = getConnection().prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstatement;
	}



	/**
	 * 释放资源,
	 */
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

	/**
	 * 测试数据库连接
	 * @param args
	 */
	public static void main(String[] args) {
		//   System.out.println(getStatement());
	}
}

