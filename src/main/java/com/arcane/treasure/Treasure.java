package com.arcane.treasure;

public abstract class Treasure {
    final String name;

    protected Treasure(String name) {
        this.name = name;
    }

//    Boolean isArmor() {
//        return false;
//    };

//    Integer getCombatBonus() {
//        return 0;
//    };
//
//    Integer getHealthBonus() {
//        return 0;
//    };

    public String toString() {
      return name;
    }
}
