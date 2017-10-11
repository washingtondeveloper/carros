package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException{
		String url ="jdbc:mysql://localhost:3306/livro";
		Connection conn = DriverManager.getConnection(url, "root", "root");
		return conn;
	}
	
	public static void main(String[] args) throws Exception{
		Connection conn = new BaseDAO().getConnection();
		System.out.println(conn);
	}
}
