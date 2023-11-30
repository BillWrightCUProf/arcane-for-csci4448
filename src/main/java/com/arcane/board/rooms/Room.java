package com.arcane.board.rooms;

import com.arcane.board.Direction;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Room {

  protected String name;
  protected Map<Direction, Room> neighbors;
  protected Integer row;
  protected Integer column;
  protected final List<Adventurer> adventurers;
  protected final List<Creature> creatures;
  protected List<Room> connectedRooms;

  public static void connect(Room roomA, Room roomB, Direction directionFromAToB) {
    roomA.addConnection(roomB, directionFromAToB);
    roomB.addConnection(roomA, directionFromAToB.opposite());
  }

  public Room(String name) {
    this.name = name;
    this.neighbors = new HashMap<>();
    this.adventurers = new ArrayList<>();
    this.creatures = new ArrayList<>();
  }

  public void addConnection(Room room, Direction direction) {
    neighbors.put(direction, room);
  }

  protected Room(String name, Integer row, Integer column) {
    this(name);
    this.row = row;
    this.column = column;
  }

  public List<Room> getConnectedRooms() {
    return connectedRooms;
  }

  public void setConnectedRooms(List<Room> connectedRooms) {
    this.connectedRooms = connectedRooms;
  }

  public List<Adventurer> getAdventurers() {
    return adventurers;
  }

  public void addAdventurer(Adventurer adventurer) {
    adventurer.setCurrentRoomId(this.getRoomId());
    this.getAdventurers().add(adventurer);
  }

  public List<Creature> getCreatures() {
    return creatures;
  }

  public void addCreature(Creature creature) {
    creature.setCurrentRoomId(this.getRoomId());
    this.getCreatures().add(creature);
  }

  public void removeCreature(Creature creature) {
    this.getCreatures().remove(creature);
  }

  public String getCurrentPosition() {
    return this.row + "-" + this.column;
  }

  public abstract String getRoomId();

  public String toString() {
    StringBuilder buffer = new StringBuilder("Room " + name + ": ");
    for (Map.Entry<Direction, Room> neighbor : neighbors.entrySet()) {
      buffer.append(neighbor.getKey().toString());
      buffer.append(" -> ");
      buffer.append(neighbor.getValue().name);
      buffer.append(", ");
    }
    return buffer.toString();
  }

  public Boolean hasNeighborInDirection(Direction direction) {
    return neighbors.containsKey(direction);
  }

  public Room getNeighbor(Direction direction) {
    if (hasNeighborInDirection(direction)) {
      return neighbors.get(direction);
    }
    return null;
  }

  public List<String> getRepresentation() {
    List<String> strings = new ArrayList<>();
    strings.add(name);
    strings.add("<adventurers here>");
    strings.add("<creatures here>");
    return strings;
  }
}
