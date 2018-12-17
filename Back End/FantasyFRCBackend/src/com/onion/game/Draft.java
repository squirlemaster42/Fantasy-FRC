package com.onion.game;

import com.onion.user.User;
import com.onion.utils.Constants;

import java.util.ArrayList;

public class Draft {

    private final ArrayList<User> players;
    private final int ID;
    private boolean isActive;
    private long endTime = 0; //Set to 0 if draft is still active

    public Draft(){
        this.players = new ArrayList<>();
        this.ID = super.hashCode();
        isActive = true;
    }

    public void addPlayer(final User player){
        if(players.size() <= Constants.DRAFT_SIZE){
            players.add(player);
        }else{
            throw new DraftFullException();
        }
    }

    public void endDraft(){
        endTime = System.nanoTime();
        isActive = false;
    }

    public int getID(){
        return this.ID;
    }

    public ArrayList<User> getPlayers(){
        return players;
    }
}
