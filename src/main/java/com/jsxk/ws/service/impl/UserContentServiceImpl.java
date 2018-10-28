package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.StoreDao;
import com.jsxk.ws.dao.VoidesDao;
import com.jsxk.ws.model.Po.VoidesContent;
import com.jsxk.ws.model.Store;
import com.jsxk.ws.model.Voides;
import com.jsxk.ws.service.UserContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContentServiceImpl implements UserContentService {


    @Autowired
    VoidesDao voidesDao;

    @Autowired
    StoreDao storeDao;

    @Override
    public List<VoidesContent> getContetnList(String catalogue, int state) {

        return voidesDao.getVoidesByCatatlogue(catalogue,state);
    }

    @Override
    public boolean addStore(int voidesId, int userId) {
        return storeDao.addStore(voidesId, userId) > 0;
    }

    @Override
    public boolean deletStore(int voidesId, int userId) {
        return storeDao.deletStore(voidesId, userId) > 0;
    }

    @Override
    public List<Store> getStore(int userId) {
        return storeDao.getStoreListByuserId(userId);
    }

    @Override
    public Boolean modifyStore(int voidesId, int userId) {

        if (storeDao.getStore(voidesId, userId) > 0) {
            return storeDao.addStore(voidesId, userId) > 0;
        } else {

            return storeDao.deletStore(voidesId, userId) > 0;
        }

    }


}
