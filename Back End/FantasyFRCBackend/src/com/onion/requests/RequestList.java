package com.onion.requests;

import java.util.ArrayList;
import java.util.List;

public class RequestList {

    private static RequestList instance;

    public static RequestList getInstance(){
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
        System.out.println(request.getName() + " added.");
        requestList.add(request);
    }

    public Class<? extends Request> getRequest(final String requestID) throws IllegalAccessException, InstantiationException {
        for(Class<? extends Request> e: requestList){
            if(e.newInstance().getRequestID().equals(requestID)){
                System.out.println(e.getName());
                return e;
            }
        }
        return null;
    }
}
