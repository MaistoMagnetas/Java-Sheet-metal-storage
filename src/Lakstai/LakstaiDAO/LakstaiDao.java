package Lakstai.LakstaiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Lakstai.Vartotojai.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class LakstaiDao {
	public void addLakstas(Lakstai lakst)
	{
		String sql = "INSERT INTO `metalolakstai`"
				+ "(`id`,`user`,`medziaga`, `lakstoStoris`, `lakstoMatmenys`, `likutis`,"
				+ " `reikiaPapildyti`)"
				+ " VALUES (?,?,?, ?, ?, ?, ?)";
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement add = myConn.prepareStatement(sql);
			add.setInt(1, lakst.getId());
			add.setString(2, lakst.getUser());
			add.setString(3,lakst.getMedziaga());
			add.setString(4,lakst.getLakstoStoris());
			add.setString(5,lakst.getLakstoMatmenys());
			add.setInt(6,lakst.getLikutis());
			add.setString(7,lakst.getReikiaPapildyti());
		
			add.execute();
			add.close();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
		}
	public void showLakstai(ObservableList<Lakstai> data, User user ) {
		String query = "SELECT * FROM metalolakstai";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement add = myConn.prepareStatement(query);
		ResultSet rs = add.executeQuery();
		while(rs.next()) {
			data.add(new Lakstai(
					rs.getInt("id"),
					rs.getString("user"),
					rs.getString("medziaga"),
					rs.getString("lakstoStoris"),
					rs.getString("lakstoMatmenys"),
					rs.getInt("likutis"),
					rs.getString("reikiaPapildyti")			
					));
		}
		
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	
	public void updateLakstai(Lakstai lakst)
	{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement upd = myConn.prepareStatement("UPDATE metalolakstai SET medziaga = ?, lakstoStoris = ?,lakstoMatmenys = ?,likutis = ?,reikiaPapildyti = ? WHERE id = ?");
			upd.setString(1,lakst.getMedziaga());
			upd.setString(2,lakst.getLakstoStoris());
			upd.setString(3,lakst.getLakstoMatmenys());
			upd.setInt(4,lakst.getLikutis());
			upd.setString(5,lakst.getReikiaPapildyti());
			upd.setInt(6,lakst.getId());
			upd.executeUpdate();
			upd.close();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
		}
		
	
	//+++
	public void deleteLakstas(int id)
	{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement del = myConn.prepareStatement("delete FROM metalolakstai WHERE id = ?");
			del.setInt(1, id);
			del.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
	}
	

	
	public ObservableList<Lakstai> searchLakstaiByMedziaga(String medziaga){
		String sql = "";
		if (medziaga.isEmpty()) {
			sql = "Select * FROM metaloLakstai";
		} else {
			sql = "Select * FROM metaloLakstai WHERE medziaga LIKE '%" + medziaga + "%'";	
		}
		
		ObservableList<Lakstai>data = FXCollections.observableArrayList();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement medz = myConn.prepareStatement(sql);
			
			ResultSet rs = medz.executeQuery();	
				while(rs.next()){
					data.add(new Lakstai(
							rs.getInt("id"),
							rs.getString("user"),
							rs.getString("medziaga"),
							rs.getString("lakstoStoris"),
							rs.getString("lakstoMatmenys"),
							rs.getInt("likutis"),
							rs.getString("reikiaPapildyti")				
					));	       		         		         			
				}	
		}catch(Exception exc){
			exc.printStackTrace();	
		}
			return data;
	}
	
}