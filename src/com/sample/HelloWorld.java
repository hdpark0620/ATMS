package com.sample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

import com.sample.common.Controller;

public class HelloWorld extends Controller {
    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final JLabel labelHelloWorld;
    
    public HelloWorld() {
        
        this.labelHelloWorld = new JLabel();
        
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        setSize(500, 500);
        
        setVisible(true);
        
        // DB connection
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {
        	 
            System.out.println("Java JDBC PostgreSQL Example");
 
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading customer records...");
            System.out.printf("%-30.30s  %-30.30s%n", "id", "name");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("id"), resultSet.getString("name"));
                
                labelHelloWorld.setText("id: " + resultSet.getString("id") + " name:" + resultSet.getString("name"));
                
                add(labelHelloWorld, BorderLayout.NORTH);
            }
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}