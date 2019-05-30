package com.tst.scoring;

import com.onion.parser.Parser;
import com.onion.scoring.Event;
import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TeamEventPull {

    @Test
    void pullTeamEvent(){
        Constants.getInstance().setTBAAuthKey("DthYAt40rXjLrvWZpxlnsFSvSDzPUjCtQ4deP8eJuy7sEpCmhxWITxqkhpTgi6Jf");

        Event events = null;
        try {
            System.out.println(HttpReqUtils.makeRequest(HttpReqUtils.makeTeamEventRequest("frc2403", "2019")));
            events = Parser.parseTeamEventList((String) HttpReqUtils.makeRequest(HttpReqUtils.makeTeamEventRequest("frc254", "2019")));
            for(String e : events.eventKey){
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
