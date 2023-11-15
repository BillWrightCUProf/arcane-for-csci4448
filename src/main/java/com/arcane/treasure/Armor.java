package com.arcane.treasure;

public class Armor extends Treasure {

    public Armor() {
        super("Armor");
    }
    @Override
    public Boolean hasArmor() {
        return true;
    }

    @Override
    public Integer getCombatBonus() {
        return 5;
    }

}
