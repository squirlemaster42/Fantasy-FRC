package com.onion.requests;

public abstract class Request{

    private final String requestID;

    public Request(final String requestID){
        this.requestID = requestID;
    }

    //TODO Determine if String is the best return
    //Used to construct what is returned to client
    public abstract String handleRequest(final String input);

    public String getRequestID(){
        return requestID;
    }
}
