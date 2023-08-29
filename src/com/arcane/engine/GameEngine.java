package com.arcane.engine;

import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;
import com.arcane.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
  private static final int TREASURE_COUNT_FOR_VICTORY = Constants.TREASURE_COUNT_FOR_VICTORY;
  private final List<Adventurer> adventurers = new ArrayList<>();
  private GameBoard gameBoard;
  private int turn;

  private List<Creature> creatures;

  // Game initialization - create new board, set turn to 0, populate adventures and creatures
  public void initialiseGame() {
    gameBoard = new GameBoard();
    this.turn = 0;
    this.adventurers.addAll(gameBoard.getRoom(Constants.STARTING_ROOM_ID).getAdventurers());
    this.creatures = gameBoard.getRemainingCreatures();
  }

  public String simulateGame(Boolean shouldPrint) {
    printGame(shouldPrint);
    // Run the game until game over condition is achieved
    while (!isGameOver()) {
      // perform a turn and then print board
      performTurn();
      printGame(shouldPrint);
    }
    // If game ends print results
    return printGameResults();
  }

  private void printGame(Boolean shouldPrint) {
    if (shouldPrint) {
      System.out.println("----------Turn-" + turn + "----------");
      gameBoard.printBoard();
      printAdventurersStatus();
      printCreaturesStatus();
      turn++;
      System.out.println();
    }
  }

  // Helper method to print creature status
  private void printCreaturesStatus() {
    int orbiterCount = 0;
    int seekerCount = 0;
    int blinkerCount = 0;
    this.creatures = gameBoard.getRemainingCreatures();
    for (Creature creature : creatures) {
      switch (creature.getClass().getSimpleName()) {
        case "Orbiter":
          orbiterCount++;
          break;
        case "Seeker":
          seekerCount++;
          break;
        case "Blinker":
          blinkerCount++;
          break;
        default:
          break;
      }
    }
    System.out.println("Orbiters - " + orbiterCount + " Remaining");
    System.out.println("Seekers - " + seekerCount + " Remaining");
    System.out.println("Blinkers - " + blinkerCount + " Remaining");
    System.out.println();
  }

  // Helper method to print adventurers status
  private void printAdventurersStatus() {
    for (Adventurer adventurer : adventurers) {
      System.out.println(
          adventurer.getClass().getSimpleName()
              + " - "
              + adventurer.getTreasureCount()
              + " Treasure(s) - "
              + (3 - adventurer.getHealth())
              + " Damage");
    }
    System.out.println();
  }

  // Step1: Perform action for all adventurers while checking if game over is achieved
  // Step2: Perform action for all creatures while checking if game over is achieved
  public void performTurn() {
    for (Adventurer adventurer : adventurers) {
      adventurer.performAction(gameBoard);
      creatures = gameBoard.getRemainingCreatures();
      if (isGameOver()) {
        break;
      }
    }

    for (Creature creature : creatures) {
      creature.performAction(gameBoard);
      if (isGameOver()) {
        break;
      }
    }
  }

  public boolean isGameOver() {
    return creatures.isEmpty()
        || gameBoard.isAnyAdventurerAlive(adventurers)
        || gameBoard.getTotalTreasureCount(adventurers) >= TREASURE_COUNT_FOR_VICTORY;
  }

  public String printGameResults() {
    // Game ends if all creatures die
    if (creatures.isEmpty()) {
      System.out.println(Constants.ALL_CREATURES_KILLED);
      return Constants.ALL_CREATURES_KILLED;
      // Game ends if all adventurers are dead
    } else if (gameBoard.isAnyAdventurerAlive(adventurers)) {
      System.out.println(Constants.ALL_ADVENTURERS_KILLED);
      return Constants.ALL_ADVENTURERS_KILLED;
      // Game ends If 10 treasures are found
    } else if (gameBoard.getTotalTreasureCount(adventurers) >= TREASURE_COUNT_FOR_VICTORY) {
      System.out.println(Constants.ALL_TREASURES_FOUND);
      return Constants.ALL_TREASURES_FOUND;
    }
    return "";
  }
}
