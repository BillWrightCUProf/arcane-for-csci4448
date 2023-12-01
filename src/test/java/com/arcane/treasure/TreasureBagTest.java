package com.arcane.treasure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreasureBagTest {

    TreasureBag bag;

    @BeforeEach
    void setUp() {
        bag = new TreasureBag();
    }

    @Test
    void testAddTreasure() {
        bag.addTreasure(new Armor());
        assert bag.size() == 1;

//        bag.addTreasure(new Sword());
//        assert bag.size() == 2;
    }

//    @Test
//    void testHavingArmor() {
//        assert !bag.hasArmor();
//        bag.addTreasure(new Armor());
//        assert bag.hasArmor();
//    }
//    @Test
//    void testCombatValue() {
//        assert bag.getCombatBonus() == 0;
//        bag.addTreasure(new Armor());
//        assert bag.getCombatBonus() == 5;
//        bag.addTreasure(new Sword());
//        assert bag.getCombatBonus() == 7;
//    }

//    @Test
//    void testNotAddingDuplicate() {
//        bag.addTreasure(new Armor());
//        bag.addTreasure(new Sword());
//        assert bag.size() == 2;
//
//        // Now let's test that we can't add duplicate
//        bag.addTreasure(new Armor());
//        assert bag.size() == 2;
//    }
//    @Test
//    void testToString() {
//        // Mention how I didn't have "assert" initially at the start of these lines, so that the test passed when
//        // it should have been failing -- emphasis the importance of a FAILING test first!
//        assert bag.toString().contains("Treasure Bag: ");
//        System.out.println(bag);
//        bag.addTreasure(new Armor());
//        assert bag.toString().contains("Armor");
//        System.out.println(bag);
//        bag.addTreasure(new Sword());
//        assert bag.toString().contains("Armor");
//        assert bag.toString().contains("Sword");
//        System.out.println(bag);
//    }
}