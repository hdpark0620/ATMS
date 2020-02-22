package com.atms.application.common;

import com.atms.App;

public abstract class Controller {
	
	private App application;

	public void setPage(Class<? extends Controller> cls, String fxmlPath, String cssPath, Object...params) {
      try{
         application.setPage(cls, fxmlPath, cssPath, params);
      }catch(Exception e){
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }
	
	public void setApplication(App app) {
		this.application = app;
	}
	
	public abstract void loadParameter(Object[] params);
}
