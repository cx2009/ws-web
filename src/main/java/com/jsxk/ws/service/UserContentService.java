package com.jsxk.ws.service;

import com.jsxk.ws.model.Po.VoidesContent;
import com.jsxk.ws.model.Store;
import com.jsxk.ws.model.Voides;

import java.util.List;

public interface UserContentService {

    List<VoidesContent> getContetnList(String catalogId, int state);


    boolean addStore(int voidesId, int userId);

    boolean deletStore(int voidesId, int userId);

    List<Store> getStore(int userId);

    Boolean modifyStore(int voidesId, int userId);


}
