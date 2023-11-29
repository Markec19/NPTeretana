/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import controller.Controller;
import domain.Clanarina;
import domain.Grad;
import domain.IndividualniTrening;
import domain.Nalog;
import domain.Ocena;
import domain.Oprema;
import domain.Teretana;
import domain.Trener;
import domain.VrstaOpreme;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DbConnectionFactory uspostavlja konekciju sa bazom podataka
 * 
 * @author Luka
 */
public class DbConnectionFactory {

    /**
     * Instanca klase Connection
     */
    private Connection connection;
    
    private String sufiks = "";
    
    /**
     * Instanca DbConnectionFactory
     */
    private static DbConnectionFactory instance;
    
    /**
     * Vraca instancu DbConnectionFactory
     * 
     * @return instanca DbConnectionFactory
     */
    public static DbConnectionFactory getInstance(){
        if(instance == null)
            instance = new DbConnectionFactory();
        return instance;
    }
    
    public void setSufiks(String s) {
    	sufiks = s;
    }
    
    /**
     * Uspostavlja konekciju sa bazom podataka
     * 
     * @return connection
     * @throws Exception 
     */
    public Connection getConnection() throws Exception{
        if(connection == null || connection.isClosed()){
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url" + sufiks);
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is successful");            
            connection.setAutoCommit(false);
        }
        return connection;
    }    
    
//    public Connection getConn() {
//    	return connection;
//    }
    
    public void closeConnection() throws Exception {
    	try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception("Nije moguce zatvoriti konekciju");
		}
    }
    
    
    
   
}
