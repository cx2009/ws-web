package com.jsxk.ws.service;

import com.jsxk.ws.model.Bo.VoidesQuery;
import com.jsxk.ws.model.Order;
import com.jsxk.ws.model.Po.VoidesCatalog;
import com.jsxk.ws.model.Voides;
import com.jsxk.ws.model.couster;

import java.util.List;

public interface VoidesService {


    List<Voides> getVoidesList(VoidesQuery voidesQuery);

    Boolean addVoides(Voides voides);

    Boolean editVoides(Voides voides);

    Boolean deletVoides(int id);

    Voides getVoidesById(int id);

    List<Voides> getVoidesByCatalogId(int catalogId);

    Boolean addVoidesNum(int voideId);


    VoidesCatalog getVoidesCatalogById(int id);

    int editcouster(String url);

    couster getcouster();


}
