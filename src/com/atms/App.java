package com.atms;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.atms.application.common.Controller;
import com.atms.application.common.Define;
import com.atms.application.main.SampleController;
import com.atms.infra.security.LockUtility;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	private static Logger logger = LogManager.getLogger(App.class);
	private Stage stage;
	
	static void showThreadInfo(String message) {
		Thread t = Thread.currentThread();
		logger.info(t.getName() + ":[" + t.getId() + "] " + message);
	}
	
	public static void main(String[] args) {
		
		try {
			DOMConfigurator.configure("config/log4j-config.xml");
			showThreadInfo("start");
			
			if(LockUtility.lock()) {
				showThreadInfo("main");
				launch(args);
			    Platform.exit();
			    return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	@Override
	public void init() throws Exception {
		showThreadInfo("init");
	}
	
	@Override
	public void stop() throws Exception {
		showThreadInfo("stop");
	}
	 
	@Override
	public void start(Stage argStage) throws Exception {
		stage = argStage;
		stage.setTitle(Define.Window.WINDOW_TITLE);
		String iconPath = Paths.get("").toAbsolutePath() + "\\config\\machine-temperature-icon.jpg";
		stage.getIcons().add(new Image("file:"+ iconPath));
		setPage(SampleController.class, "sample.fxml", "sample.css");
	}
	
	public void setPage(Class<?> cls, String fxmlPath, String cssPath, Object...params) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		Scene scene = new Scene((Parent)loader.load(cls.getResourceAsStream(fxmlPath)), 
				Define.Window.WINDOW_WIDTH, Define.Window.WINDOW_HEIGHT);
		
		Controller page = (Controller)loader.getController();
		page.setApplication(this);
		page.loadParameter(params);
		stage.setScene(scene);
		stage.show();
	}
}
