package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.AppManagerDao;
import com.jsxk.ws.dao.CatalogueDao;
import com.jsxk.ws.dao.NovelDao;
import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Catalogue;
import com.jsxk.ws.model.Novel;
import com.jsxk.ws.model.Po.AppMangerList;
import com.jsxk.ws.model.Po.AppMangers;
import com.jsxk.ws.service.AppManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

@Service
@Slf4j
public class AppManagerServiceImpl implements AppManagerService {

    @Autowired
    AppManagerDao appManagerDao;


    @Autowired
    CatalogueDao catalogueDao;


    @Autowired
    NovelDao novelDao;


    @Override
    public boolean addAppManager(AppManager appManager) {
        try {

            if (appManagerDao.addAppManager(appManager) > 0) {
                return true;
            }

        } catch (Exception ex) {

            log.error("新增错误{}", ex);

        }

        return false;
    }

    @Override
    public boolean editAppManager(AppManager appManager) {
        try {

            if (appManagerDao.editAppManager(appManager) > 0) {
                return true;
            }

        } catch (Exception ex) {

            log.error("更新错误{}", ex);

        }

        return false;
    }

    @Override
    public boolean deletAppManager(int id) {
        try {

            if (appManagerDao.deletAppManager(id) > 0)
                return true;

        } catch (Exception ex) {

            log.error("删除错误{}", ex);
        }

        return false;
    }

    @Override
    public List<AppMangers> getAppManagerList() {

        return appManagerDao.getAppManagerList();
    }

    @Override
    public List<AppMangerList> getAppManagerListById() {

        List<AppMangerList> appMangerLists = new ArrayList<>();

        try {

            List<Catalogue> catalogues = catalogueDao.getCatalogueSecond(4);

            if (catalogues != null) {

                for (Catalogue catalogue : catalogues) {

                    AppMangerList appMangerList = new AppMangerList();

                    List<com.jsxk.ws.model.Po.AppManager> appManagers = appManagerDao.getAppManagerByCataLogId(catalogue.getId());
                    appMangerList.setCatalogId(catalogue.getId());
                    appMangerList.setCatalogName(catalogue.getName());
                    appMangerList.setAppMangers(appManagers);
                    appMangerLists.add(appMangerList);

                }
            }

        } catch (Exception ex) {

            log.error("查询应用列表错误{}", ex);
        }


        return appMangerLists;


    }

    @Override
    public List<Novel> getNovelList(int catalogId) {
        return novelDao.getNovelList(catalogId);
    }

    @Override
    public Novel getNovelById(int id) {
        return novelDao.getNovelById(id);
    }


}
