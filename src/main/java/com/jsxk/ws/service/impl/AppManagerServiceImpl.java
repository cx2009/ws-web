package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.AppManagerDao;
import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Po.AppMangers;
import com.jsxk.ws.service.AppManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppManagerServiceImpl implements AppManagerService {

    @Autowired
    AppManagerDao appManagerDao;

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
}
