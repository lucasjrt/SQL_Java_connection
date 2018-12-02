package br.ufu.facom.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {
	
	public static Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Trabalho?currentSchema=praca_alimentacao";
			c = DriverManager.getConnection(url, "postgres", "postgres");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
	
	public static void insertShopping(String nome, String endereco, String telefone) {
		Connection c = getConnection();
		if(c != null) {
			Statement st;
			String s = "insert into shopping values ( '" + nome + "', '" + endereco + "', '" + telefone + "')";
			System.out.println(s);
			try {
				st = c.createStatement();
				st.executeUpdate(s);
				c.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void removeShopping(String id) {
		Connection c = getConnection();
		if(c != null) {
			Statement st;
			String s = "delete from shopping where nome = '" + id + "'";
			System.out.println(s);
			try {
				st = c.createStatement();
				st.executeUpdate(s);
				c.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(String e: getShoppings()) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<String> getShoppings() {
		ArrayList<String> shoppings = new ArrayList<String>();
		Connection c = getConnection();
		if(c != null) {
			Statement s;
			try {
				s = c.createStatement();
				ResultSet rs = s.executeQuery("select nome from shopping");
				while(rs.next()) {
					String temp = rs.getString(1);
					shoppings.add(temp);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return shoppings;
	}
	
}
