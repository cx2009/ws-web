package com.jsxk.ws.service;

import com.jsxk.ws.model.Po.VoidesContent;
import com.jsxk.ws.model.Store;
import com.jsxk.ws.model.Voides;

import java.util.List;

public interface UserContentService {

    List<VoidesContent> getContetnList(String catalogId, int state);


    boolean addStore(String voidesId, String userId);

    boolean deletStore(String voidesId, String userId);

    List<Store> getStore(String userId);

    Boolean modifyStore(String voidesId, String userId);

    Boolean IsStore(String voideId, String userId);


    List<VoidesContent> getMyStore(String userId);


}
