package com.jsxk.ws.service;

import com.jsxk.ws.model.Blues;
import com.jsxk.ws.model.Catalogue;

import java.util.List;

public interface CatalogueService {

    boolean addCatalogue(Catalogue catalogue);

    boolean deleCatalogue(int catalogue);

    boolean editCatalogue(Catalogue catalogue);

    List<Catalogue> getCatalogueList();

    List<Catalogue> getCatalogueByParentId(int parentId);

    List<Blues> getBlues();

    List<Catalogue> getCatalogueListSecond(int parentId);


}
