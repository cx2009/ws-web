package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.BluesDao;
import com.jsxk.ws.dao.CatalogueDao;
import com.jsxk.ws.model.Blues;
import com.jsxk.ws.model.Catalogue;
import com.jsxk.ws.service.CatalogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CatalogueServiceImpl implements CatalogueService {


    @Autowired
    CatalogueDao catalogueDao;

    @Autowired
    BluesDao bluesDao;

    @Override
    public boolean addCatalogue(Catalogue catalogue) {

        try {

            return catalogueDao.addCatalogue(catalogue) > 0;

        } catch (Exception ex) {

            log.error("新增目录错误{}", ex);

        }

        return false;
    }

    @Override
    public boolean deleCatalogue(int id) {


        return catalogueDao.deleteCatalogue(id) > 0;
    }

    @Override
    public boolean editCatalogue(Catalogue catalogue) {

        return catalogueDao.editCatalogue(catalogue) > 0;
    }

    @Override
    public List<Catalogue> getCatalogueList() {

        return catalogueDao.getCatalogueList();
    }

    @Override
    public List<Catalogue> getCatalogueByParentId(int parentId) {

        return catalogueDao.getCatalogueListByparentId(parentId);
    }

    @Override
    public List<Blues> getBlues() {


        return bluesDao.getBluesList();
    }

    @Override
    public List<Catalogue> getCatalogueListSecond(int parentId) {
        return catalogueDao.getCatalogueSecond(parentId);
    }


}
