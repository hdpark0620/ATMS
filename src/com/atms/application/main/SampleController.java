package com.atms.application.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.atms.application.common.Controller;
import com.atms.application.linechart.LineChartController;
import com.atms.infra.manage.DBConnect;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SampleController extends Controller {
	
	@FXML 
	private TextField testTextField;
	
	public void handleSubmitButtonAction() {
		setPage(LineChartController.class, "linechart.fxml", "sample.css");
	}

	@Override
	public void loadParameter(Object[] params) {
		
		try {
            StringBuilder query = new StringBuilder();
        	HashMap<String, String> paramMap = new HashMap<String, String>();
        	
        	// SQL
            query.append(" select * from customer \n");
            query.append(" where id = :id \n");

            // parameters
            paramMap.put("id", "123123");
            
            ResultSet resultSet = DBConnect.getDbCon().select(query, paramMap);
            while(resultSet.next()) {
            	 testTextField.setText("id: " + resultSet.getString("id") + " name:" + resultSet.getString("name"));
            	 break;
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
