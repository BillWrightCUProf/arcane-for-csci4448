package com.arcane.engine;

import com.arcane.board.GameBoard;
import com.arcane.character.Character;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;
import com.arcane.ui.ConsoleGameDisplay;
import com.arcane.util.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameEngine {
  private List<Adventurer> adventurers;
  private GameBoard gameBoard;
  private int turn;

  private List<Creature> creatures;

  // Game initialization - create new board, set turn to 0, populate adventures and creatures
  public void initialiseGame() {
    gameBoard = new GameBoard();

    // Add any observers of the game board here.
    // Note that we don't have to keep a reference to ConsoleGameDisplay.
    // What would we do with that display?
    new ConsoleGameDisplay(gameBoard);

    adventurers = new ArrayList<>();
    this.turn = 0;
    this.adventurers.addAll(gameBoard.getRoom(Constants.STARTING_ROOM_ID).getAdventurers());
    this.creatures = gameBoard.getRemainingCreatures();
  }

  public String simulateGame(Boolean shouldPrint) {
    while (!isGameOver()) {
      performTurn();
    }
    // This is probably better done with a notify as well.
    // The engine probably shouldn't know about any display aspects
    return printGameResults();
  }

//  private void printGame(boolean shouldPrint) {
//    if (shouldPrint) {
//      System.out.println("----------Turn-" + turn + "----------");
//      gameBoard.renderBoard();
//      printAdventurersStatus();
//      printCreaturesStatus();
//      // What the heck is this? Updating turn in the printGame method??!! NO!
//      turn++;
//      System.out.println();
//    }
//  }

  public String toString() {
    StringBuilder representation = new StringBuilder("----------Turn-" + turn + "----------\n");
    representation
            .append(gameBoard.toString())
            .append(generateAdventurersStatus())
            .append(generateCreaturesStatus())
            .append("\n");
    return representation.toString();
  }

  // Helper method to print creature status
  private void printCreaturesStatus() {
    int fireBornCount = 0;
    int aquaridCount = 0;
    int terraVoreCount = 0;
    int zephyralCount = 0;
    this.creatures = gameBoard.getRemainingCreatures();
    for (Creature creature : creatures) {
      switch (creature.getAcronym()) {
        case FIREBORN -> fireBornCount++;
        case TERRAVORE -> terraVoreCount++;
        case AQUARID -> aquaridCount++;
        case ZEPHYRAL -> zephyralCount++;
      }
    }
    System.out.printf("FireBorns - %d Remaining%n", fireBornCount);
    System.out.printf("TerraVores - %d Remaining%n", terraVoreCount);
    System.out.printf("Aquarids - %d Remaining%n", aquaridCount);
    System.out.printf("Zephyrals - %d Remaining%n%n", zephyralCount);
  }

  // Helper method to print adventurers status
  private void printAdventurersStatus() {
    for (Adventurer adventurer : adventurers) {
      System.out.println(
              // What's going on here? ths should all be in the Adventurer toString() method
          adventurer.getClass().getSimpleName()
              + " - "
              + adventurer.getTreasureCount()
              + " Treasure(s) - "
              + (adventurer.getHealth())
              + " Health Remaining");
    }
    System.out.println();
  }

  private String generateAdventurersStatus() {
    return generateCharactersStatus(adventurers);
  }

  private String generateCreaturesStatus() {
    return generateCharactersStatus(creatures);
  }

  private String generateCharactersStatus(List<? extends Character> characters) {
    return characters
            .stream()
            .map(character -> character.toString())
            .collect(Collectors.joining(""));
  }

  // Step1: Perform action for all adventurers while checking if game over is achieved
  // Step2: Perform action for all creatures while checking if game over is achieved
  public void performTurn() {
    turn++;
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

    // Tell game board that the turn is over
    gameBoard.turnIsOver();
  }

  public boolean isGameOver() {
    return creatures.isEmpty()
        || gameBoard.areAllAdventuresDead(adventurers)
        || gameBoard.getTotalTreasureCount(adventurers) >= Constants.TREASURE_COUNT_FOR_VICTORY;
  }

  public String printGameResults() {
    // Game ends if all creatures die
    if (creatures.isEmpty()) {
      System.out.println(Constants.ALL_CREATURES_KILLED);
      return Constants.ALL_CREATURES_KILLED;
      // Game ends if all adventurers are dead
    } else if (gameBoard.areAllAdventuresDead(adventurers)) {
      System.out.println(Constants.ALL_ADVENTURERS_KILLED);
      return Constants.ALL_ADVENTURERS_KILLED;
      // Game ends If 10 treasures are found
    } else if (gameBoard.getTotalTreasureCount(adventurers) >= Constants.TREASURE_COUNT_FOR_VICTORY) {
      System.out.println(Constants.ALL_TREASURES_FOUND);
      return Constants.ALL_TREASURES_FOUND;
    }
    return "";
  }
}
