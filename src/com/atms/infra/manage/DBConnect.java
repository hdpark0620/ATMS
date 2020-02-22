package com.atms.infra.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBConnect {
	
	private Connection conn;
    private static DBConnect db;
    private PreparedStatement statement;
    
    private DBConnect() {
        String url= "jdbc:postgresql://localhost:5432/";
        String dbName = "postgres";
        String userName = "postgres";
        String password = "postgres";
        try {
            this.conn = (Connection)DriverManager.getConnection(url + dbName, userName, password);
            conn.setAutoCommit(false);
            System.out.println("Connected to PostgreSQL database!");
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    
    /**
     *
     * @return Database connection object
     * @throws SQLException 
     */
    public static synchronized DBConnect getDbCon() throws SQLException {
    	if ( db == null ) {
            db = new DBConnect();
        } else if (db.conn.isClosed()) {
        		db.conn.beginRequest();
        }
        return db;
    }
    
    public static synchronized void relased() throws SQLException {
    	if ( db.conn != null && !db.conn.isClosed()) {
            db.conn.close();
            db = null;
        }
    }
    
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet select(StringBuilder query, HashMap<String, String> paramMap) throws SQLException {
        System.out.println("Reading customer records...");
        
        PreparedStatement pstmt = null;
		try {
			pstmt = db.conn.prepareStatement(bindParameter(query, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        int row = 0;
        if (!paramMap.isEmpty()) {
            for (String key : paramMap.keySet()) {
                pstmt.setString(++row, paramMap.get(key));
    		}
        }
        ResultSet result = pstmt.executeQuery();
        db.conn.endRequest();
        return result;
    }
    
    /**
     * 
     * @param query
     * @param paramMap
     * @return
     */
    private String bindParameter(StringBuilder query, HashMap<String, String> paramMap) {
    	if (!paramMap.isEmpty()) {
            for (String key : paramMap.keySet()) {
            	String indexPrefix = ":" + key;
            	int lastIndex = query.lastIndexOf(indexPrefix);
            	if(lastIndex > 0) {
            		query.replace(lastIndex, lastIndex + indexPrefix.length() , "?");
            	}
    		}
        }
    	return query.toString();
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
    	statement = db.conn.prepareStatement(insertQuery);
        int count = statement.executeUpdate(insertQuery);
        db.conn.commit();
        return count;
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int update(String updateQuery) throws SQLException {
    	statement = db.conn.prepareStatement(updateQuery);
        int count = statement.executeUpdate(updateQuery);
        db.conn.commit();
        return count;
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void startInsert(String insertQuery) throws SQLException {
		statement = db.conn.prepareStatement(insertQuery);
		appendInsert(insertQuery);
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void appendInsert(String insertQuery) throws SQLException {
        try {
			statement.executeUpdate(insertQuery);
    	} catch (SQLException e) {
    		db.conn.rollback();
			throw new SQLException(e);
		}
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void endInsert(String insertQuery) throws SQLException {
    	appendInsert(insertQuery);
        db.conn.endRequest();
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void startUpdate(String updateQuery) throws SQLException {
		statement = db.conn.prepareStatement(updateQuery);
		appendUpdate(updateQuery);
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void appendUpdate(String updateQuery) throws SQLException {
        try {
			statement.executeUpdate(updateQuery);
    	} catch (SQLException e) {
    		db.conn.rollback();
			throw new SQLException(e);
		}
    }
    
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public void endUpdate(String updateQuery) throws SQLException {
    	appendUpdate(updateQuery);
        db.conn.endRequest();
    }
}