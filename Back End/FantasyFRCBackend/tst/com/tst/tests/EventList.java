package com.tst.tests;

import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;

import java.io.IOException;
import java.util.ArrayList;

public class EventList {
    public static void main(String[] args) {
        Constants.getInstance().setTBAAuthKey("s7dK3qnaU07lm7HXyNsg2xRQ3ZBzXWqMZDyfr7z75fqipULBhLjA046AUn1ONAEA");
        try {
            Object output = HttpReqUtils.makeRequest(HttpReqUtils.makeYearEventListReq(2018));
            String output2 = (String)output;
            String[] eventlst = output2.split(",");
            ArrayList<String> events = new ArrayList<String>();
            for(int i = 0; i <= 256; i++){
                events.add(eventlst[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
