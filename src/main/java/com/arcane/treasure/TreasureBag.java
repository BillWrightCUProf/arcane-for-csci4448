package com.arcane.treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreasureBag {
    private List<Treasure> bag = new ArrayList<>();

    Integer size() {
        return bag.size();
    }

    Integer getCombatBonus() {
        return bag.stream().map(Treasure::getCombatBonus).reduce(0, Integer::sum);
    }
    public String toString() {
        return "Treasure Bag: " + bag.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    void addTreasure(Treasure treasure) {
        if (! bag.stream().map(Object::getClass).toList().contains(treasure.getClass())) {
            System.out.println("Adding treasure " + treasure.toString() );
            bag.add(treasure);
        }
    }
}
