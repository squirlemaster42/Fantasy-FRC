package com.tst.scoring;

import com.onion.team.Team;
import com.onion.teamstatus.Event;
import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class EventStatusTest {

    @Test
    void testEventStatus(){
        Constants.getInstance().setTBAAuthKey("DthYAt40rXjLrvWZpxlnsFSvSDzPUjCtQ4deP8eJuy7sEpCmhxWITxqkhpTgi6Jf");

        Team team = new Team(2403);

        Event statusEvent = null;
        try {
            statusEvent = Constants.getInstance().getGson().fromJson(HttpReqUtils.makeRequest(HttpReqUtils.makeTeamEventStatusRequest(team, "2019tur")), Event.class);
            System.out.printf("%s %s", statusEvent.getAlliance().getName(), statusEvent.getPlayoffStatusStr());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
