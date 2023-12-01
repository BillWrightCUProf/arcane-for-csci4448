package com.arcane.treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreasureBag {
    private final List<Treasure> bag = new ArrayList<>();

//    Integer size() {
//        return bag.size();
//    }

//    Integer getCombatBonus() {
//        return bag.stream().map(Treasure::getCombatBonus).reduce(0, Integer::sum);
//    }

    public String toString() {
        return "Treasure Bag: " + bag.stream().map(Object::toString).collect(Collectors.joining(","));
    }

//    void addTreasure(Treasure treasure) {
//        bag.add(treasure);
//    }
//
//    Boolean hasArmor() {
//        return bag.stream().anyMatch(Treasure::isArmor);
//    }

//    void addTreasure(Treasure treasure) {
//        if (bag.stream().noneMatch(t -> t.getClass() == treasure.getClass())) {
//            System.out.println("Adding treasure " + treasure.toString() );
//            bag.add(treasure);
//        }
//    }
}
