package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.VoidesDao;
import com.jsxk.ws.model.Bo.VoidesQuery;
import com.jsxk.ws.model.Order;
import com.jsxk.ws.model.Po.VoidesCatalog;
import com.jsxk.ws.model.Voides;
import com.jsxk.ws.service.VoidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoidesServiceImpl implements VoidesService {

    @Autowired
    private VoidesDao voidesDao;

    @Override
    public List<Voides> getVoidesList(VoidesQuery voidesQuery) {

        return voidesDao.getVoidesList(voidesQuery);

    }

    @Override
    public Boolean addVoides(Voides voides) {
        return voidesDao.addVoides(voides) > 0;
    }

    @Override
    public Boolean editVoides(Voides voides) {
        return voidesDao.editVoides(voides) > 0;
    }

    @Override
    public Boolean deletVoides(int id) {
        return voidesDao.deletVoides(id) > 0;
    }

    @Override
    public Voides getVoidesById(int id) {

        return voidesDao.getVoidesById(id);
    }

    @Override
    public List<Voides> getVoidesByCatalogId(int catalogId) {
        return voidesDao.getVoidesByCatalogId(catalogId);
    }

    @Override
    public Boolean addVoidesNum(int voideId) {
        return voidesDao.AddVoidesNum(voideId) > 0;
    }

    @Override
    public VoidesCatalog getVoidesCatalogById(int id) {
        return voidesDao.getVoidesCatalogById(id);
    }


}
