//DAO--- data access object-- it is used to ftech or communitcate with the database;
// it is a design pattern which are used to separate all the database related operation in single separate class.\
package dao;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import model.User;

public class UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/demouserjdbc";
	private String jdbcUsername = "root";
	private String jdbcPassword = "System@8815";   //this is correct password 
	//NOTE :our database name is demouserjdbc and table name is jdbcuser;
	
	private static final String INSERT_USERS_SQL = "INSERT_INTO jdbcuser"+ "(name, email, country) VALUES " +"(?,?,?);";
	
	private static final String SELECT_USER_BY_ID = "select id, name, email, country from jdbcuser where id =?;";
	private static final String SELECT_ALL_USER = "select * from jdbcuser;";
	private static final String DELETE_USERS_SQL = "delete from jdbcuser where id = ?;";
	private static final String UPDATE_USERS_SQL = "update jdbcuser set name = ? , email= ?, country = ? where id = ?;";
	
	
	// getconnection method for database connections
	protected Connection getConnection() {
		 Connection connection = null;
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection(jdbcURL, jdbcUsername,jdbcPassword);
			 
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }catch(ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		return connection;
		
	}
	
	
	//create or insert user
	
	public void insertUser(User user) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3,user.getCountry());
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//update user
	public boolean updateUser(User user) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)){
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getCountry());
			preparedStatement.setInt(4,user.getId());
			
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	
	
	//select user by id
	public User selectUser(int id) {
		User user = null;
		// Establishing a connection
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
			
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country= rs.getString("country");
				user = new User(id, name, email, country );
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	// select all the user
	public List<User> selectAllUser(){
		List<User> users = new ArrayList<>();
		
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)){
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
			      int id = rs.getInt("id");
 				String name = rs.getString("name");
				String email = rs.getString("email");
				String country= rs.getString("country");
				users.add(new User(id, name, email, country ) );	
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}
	
		
			return users;
		}
	
	
	//delete user
	
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)){
			
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rowDeleted;
		
	}
	
	
	
	
	
	

}
