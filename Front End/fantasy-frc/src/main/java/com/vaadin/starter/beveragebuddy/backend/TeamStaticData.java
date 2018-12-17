package com.vaadin.starter.beveragebuddy.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TeamStaticData{
    private static final String TITANIUM = "Titanium";
    private static final String STEEL = "Steel";
    private static final String IRON = "Iron";
    private static final String ALUMINUM = "Aluminum";
    private static final String WOOD = "Wood";

    static final Map<String, String> TEAMS = new LinkedHashMap<>();

    static {
        Stream.of("Evian",
                "Voss",
                "Veen",
                "San Pellegrino",
                "Perrier")
                .forEach(name -> TEAMS.put(name, TITANIUM));

        Stream.of("Coca-Cola",
                "Fanta",
                "Sprite")
                .forEach(name -> TEAMS.put(name, STEEL));

        Stream.of("Maxwell Ready-to-Drink Coffee",
                "Nescafé Gold",
                "Starbucks East Timor Tatamailau")
                .forEach(name -> TEAMS.put(name, IRON));

        Stream.of("Prince Of Peace Organic White Tea",
                "Pai Mu Tan White Peony Tea",
                "Tazo Zen Green Tea",
                "Dilmah Sencha Green Tea",
                "Twinings Earl Grey",
                "Twinings Lady Grey",
                "Classic Indian Chai")
                .forEach(name -> TEAMS.put(name, ALUMINUM));

        Stream.of("Pan Galactic Gargle Blaster",
                "Mead",
                "Soma")
                .forEach(name -> TEAMS.put(name, WOOD));

    }

    /** This class is not meant to be instantiated. */
    private TeamStaticData() {
    }
}


