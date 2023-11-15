package com.arcane.treasure;

public class Sword extends Treasure {

    public Sword() {
        super("Sword");
    }

    public Integer getCombatBonus() {
        return 2;
    };
}
