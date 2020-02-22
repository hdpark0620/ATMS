package com.atms.application.linechart;

import com.atms.application.common.Controller;
import com.atms.application.main.SampleController;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class LineChartController extends Controller {
	
    @FXML 
    private LineChart<String, Number> lineChart;
    
	@SuppressWarnings("unchecked")
	public void btn() {
    	lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", 200));
        series.getData().add(new XYChart.Data<String, Number>("Feb", 500));
        series.getData().add(new XYChart.Data<String, Number>("Mar", 300));
        series.getData().add(new XYChart.Data<String, Number>("Apr", 600));
        series.setName("Pay 1");
        
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(new XYChart.Data<String, Number>("Jan", 400));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", 100));
        series1.getData().add(new XYChart.Data<String, Number>("Mar", 800));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", 500));
        series1.setName("Pay 2");
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.getData().add(new XYChart.Data<String, Number>("Jan", 500));
        series2.getData().add(new XYChart.Data<String, Number>("Feb", 900));
        series2.getData().add(new XYChart.Data<String, Number>("Mar", 450));
        series2.getData().add(new XYChart.Data<String, Number>("Apr", 650));
        series2.setName("Pay 3");
        
        lineChart.getData().addAll(series, series1, series2);
    }

	@SuppressWarnings("unchecked")
	@Override
	public void loadParameter(Object[] params) {
		// TODO Auto-generated method stub
		
		lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", 200));
        series.getData().add(new XYChart.Data<String, Number>("Feb", 500));
        series.getData().add(new XYChart.Data<String, Number>("Mar", 300));
        series.getData().add(new XYChart.Data<String, Number>("Apr", 600));
        series.setName("Pay 1");
        
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(new XYChart.Data<String, Number>("Jan", 400));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", 100));
        series1.getData().add(new XYChart.Data<String, Number>("Mar", 800));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", 500));
        series1.setName("Pay 2");
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.getData().add(new XYChart.Data<String, Number>("Jan", 500));
        series2.getData().add(new XYChart.Data<String, Number>("Feb", 900));
        series2.getData().add(new XYChart.Data<String, Number>("Mar", 450));
        series2.getData().add(new XYChart.Data<String, Number>("Apr", 650));
        series2.setName("Pay 3");
        
        lineChart.getData().addAll(series, series1, series2);
		
	}
	
	public void backAction() {
		setPage(SampleController.class, "sample.fxml", "sample.css");
	}
}