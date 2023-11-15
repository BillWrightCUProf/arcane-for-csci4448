package com.arcane.board;

import com.arcane.Element;
import com.arcane.board.rooms.Room;
import com.arcane.board.rooms.StartingRoom;
import com.arcane.character.adventurer.*;
import com.arcane.character.creature.*;
import com.arcane.observer.Subject;
import com.arcane.util.Constants;
import java.util.*;

public class GameBoard extends Subject {

  private Map<String, Room> roomMap;
  private Map<Element, Floor> elementalFloors;

  public GameBoard() {
    initialiseBoard();
    addAdventures();
    addCreatures();
  }

  // called by the engine whenever a complete turn is over
  public void turnIsOver() {
    notifyObservers();
  }

  private void initialiseBoard() {
    roomMap = new LinkedHashMap<>();
    elementalFloors = createElementalFloors();
    StartingRoom startingRoom = createStartingRoom(elementalFloors);
    addStartingRoomConnections(elementalFloors, startingRoom);
    populateRoomMap(startingRoom, elementalFloors);
  }

  private void populateRoomMap(StartingRoom startingRoom, Map<Element, Floor> elementalFloors) {
    roomMap.put(startingRoom.getRoomId(), startingRoom);
    for (Floor floor : elementalFloors.values()) {
      for (Room[] row : floor.getRooms()) {
        for (Room room : row) {
          roomMap.put(room.getRoomId(), room);
        }
      }
    }
  }

  private void addStartingRoomConnections(
      Map<Element, Floor> elementalFloors, StartingRoom startingRoom) {
    for (Floor floor : elementalFloors.values()) {
      Room room = floor.getRoom(Constants.VERTICAL_ROOMS / 2, Constants.HORIZONTAL_ROOMS / 2);
      List<Room> connectedRooms = room.getConnectedRooms();
      connectedRooms.add(startingRoom);
    }
  }

  private Map<Element, Floor> createElementalFloors() {
    Map<Element, Floor> elementFloorMap = new EnumMap<>(Element.class);
    for (Element element : Element.values()) {
      elementFloorMap.put(element, new Floor(element));
    }
    return elementFloorMap;
  }

  private StartingRoom createStartingRoom(Map<Element, Floor> elementalFloors) {
    StartingRoom startingRoom = new StartingRoom();
    List<Room> connectedRooms = new ArrayList<>();
    for (Floor floor : elementalFloors.values()) {
      connectedRooms.add(
          floor.getRoom(Constants.VERTICAL_ROOMS / 2, Constants.HORIZONTAL_ROOMS / 2));
    }
    startingRoom.setConnectedRooms(connectedRooms);
    return startingRoom;
  }

  private void addAdventures() {
    List<Adventurer> adventurers =
        Arrays.asList(new EmberKnight(), new MistWalker(), new TerraVoyager(), new ZephyrRogue());
    adventurers.forEach(
        adventurer -> this.roomMap.get(adventurer.getCurrentRoomId()).addAdventurer(adventurer));
  }

  private void addCreatures() {
    List<Creature> creatures = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      creatures.add(new FireBorn());
      creatures.add(new Aquarid());
      creatures.add(new TerraVore());
      creatures.add(new Zephyral());
    }
    creatures.forEach(
        creature -> this.roomMap.get(creature.getCurrentRoomId()).addCreature(creature));
  }

  public Room getRoom(String currentRoomId) {
    return roomMap.get(currentRoomId);
  }

  public String toString() {
    StringBuilder representation = new StringBuilder();
    Room startingRoom = this.roomMap.get(Constants.STARTING_ROOM_ID);
    representation.append("Starting Room:\n");
    representation.append(startingRoom.toString());
    representation.append("\n");
    for (Floor floor : elementalFloors.values()) {
      representation.append(floor);
    }
    representation.append("\n");
    return representation.toString();
  }

  // iterates through each room and returns a list of creatures still alive
  public List<Creature> getRemainingCreatures() {
    List<Creature> creatures = new ArrayList<>();
    this.roomMap.values().forEach((room -> creatures.addAll(room.getCreatures())));
    return creatures;
  }

  // returns true if even one adventurer is alive
  // returns false if all adventurers are dead
  public boolean areAllAdventuresDead(List<Adventurer> adventurers) {
    for (Adventurer adventurer : adventurers) {
      if (adventurer.isAlive()) {
        return false;
      }
    }
    return true;
  }

  // returns a cumulative count of treasures found by all adventurers
  public int getTotalTreasureCount(List<Adventurer> adventurers) {
    int totalTreasureCollected = 0;
    for (Adventurer adventurer : adventurers) {
      totalTreasureCollected += adventurer.getTreasureCount();
    }
    return totalTreasureCollected;
  }
}
