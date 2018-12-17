package com.onion.game;

import java.util.HashMap;
import java.util.Map;

public class Drafts {

    private final Map<Integer, Draft> drafts;

    public Drafts(){
        this.drafts = new HashMap<>();
    }

    public void createDraft(){
        final Draft d = new Draft();
        addDraft(d.getID(), d);
    }

    public void addDraft(final Integer ID, final Draft draft){
        drafts.put(ID, draft);
    }

    public Draft getDraft(final Integer ID){
        return drafts.get(ID);
    }
}
