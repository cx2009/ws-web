package com.jsxk.ws.service;

import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Novel;
import com.jsxk.ws.model.Po.AppMangerList;
import com.jsxk.ws.model.Po.AppMangers;

import java.util.List;


public interface AppManagerService {


    boolean addAppManager(AppManager appManager);

    boolean editAppManager(AppManager appManager);

    boolean deletAppManager(int id);

    List<AppMangers> getAppManagerList();

    List<AppMangerList>getAppManagerListById();


    List<Novel>getNovelList(int catalogId);

    Novel getNovelById(int id);




}
