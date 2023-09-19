package com.arcane;

import com.arcane.engine.GameEngine;
import com.arcane.util.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameEngineTest {

    @Test
    public void testSingleGame() {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initialiseGame();
        gameEngine.simulateGame(true);

        assertTrue(gameEngine.isGameOver());
    }

    @Test
    public void testMultipleGames() {
        GameEngine gameEngine = new GameEngine();
        int allTreasuresFound = 0;
        int allCreaturesKilled = 0;
        int allAdventurersKilled = 0;

        // Based on win condition increment the counters
        for (int i = 0; i < 30; i++) {
            gameEngine.initialiseGame();
            switch (gameEngine.simulateGame(false)) {
                case Constants.ALL_TREASURES_FOUND:
                    allTreasuresFound++;
                    break;
                case Constants.ALL_ADVENTURERS_KILLED:
                    allAdventurersKilled++;
                    break;
                case Constants.ALL_CREATURES_KILLED:
                    allCreaturesKilled++;
                    break;
                default:
                    break;
            }
        }
        System.out.println(
                "Win scenarios count=>"
                        + " All treasures found: "
                        + allTreasuresFound
                        + ", All creatures killed: "
                        + allCreaturesKilled
                        + ", All adventurers killed: "
                        + allAdventurersKilled);

        assertTrue(gameEngine.isGameOver());
    }
}

