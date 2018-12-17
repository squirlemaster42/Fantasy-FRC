package com.onion.states;

import com.onion.utils.Handler;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private static StateManager instance;

    public static StateManager getInstance(){
        if(instance == null){
            instance = new StateManager();
        }
        return instance;
    }

    private final Map<String, State> stateMap;
    private State currentState;

    public StateManager(){
        stateMap = new HashMap<>();
    }

    public void addState(final String name, final State state){
        stateMap.put(name, state);
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setCurrentState(final String name) throws NotFound { //TODO Get better Exception
        if(stateMap.containsKey(name)){
            currentState = stateMap.get(name);
            currentState.setScene(Handler.getInstance().getPrimaryStage());
        }else{
            throw new NotFound();
        }
    }
}
