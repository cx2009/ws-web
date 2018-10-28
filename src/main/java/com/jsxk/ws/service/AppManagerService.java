package com.jsxk.ws.service;

import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Po.AppMangers;

import java.util.List;


public interface AppManagerService {


    boolean addAppManager(AppManager appManager);

    boolean editAppManager(AppManager appManager);

    boolean deletAppManager(int id);

    List<AppMangers> getAppManagerList();


}
