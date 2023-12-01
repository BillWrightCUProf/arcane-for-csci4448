package com.arcane.treasure;

public class Potion extends Treasure {

    Integer potionEffect;

    Potion(Integer potionEffect) {
        super("Potion");
        this.potionEffect = potionEffect;
    }

//    @Override
//    public Integer getHealthBonus() {
//        return potionEffect;
//    }
}
