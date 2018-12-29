package com.onion.requests;

import java.util.ArrayList;
import java.util.List;

public class RequestList {

    private RequestList instance;

    public RequestList getInstance(){
        if(instance == null){
            instance = new RequestList();
        }
        return instance;
    }

    private final List<Class<? extends Request>> requestList;

    public RequestList(){
        requestList = new ArrayList<>();
    }

    public void addRequest(final Class<? extends Request> request){
        requestList.add(request);
    }

    public Class<? extends Request> getRequest(final String requestID) throws IllegalAccessException, InstantiationException {
        for(Class<? extends Request> e: requestList){
            if(e.newInstance().getRequestID().equals(requestID)){
                return e;
            }
        }
        return null;
    }
}
