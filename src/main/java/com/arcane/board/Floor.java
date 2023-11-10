package com.arcane.board;

import com.arcane.Element;
import com.arcane.board.rooms.ElementalRoom;
import com.arcane.board.rooms.Room;
import com.arcane.util.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.center;

public class Floor {
  private final Room[][] rooms;
  private final Element element;

  public Floor(Element element) {
    this.element = element;
    rooms = new ElementalRoom[Constants.VERTICAL_ROOMS][Constants.HORIZONTAL_ROOMS];
    buildFloor();
    createConnections();
  }

  private void buildFloor() {
    for (int row = 0; row < Constants.VERTICAL_ROOMS; row++) {
      for (int column = 0; column < Constants.HORIZONTAL_ROOMS; column++) {
        rooms[row][column] = new ElementalRoom(element, row, column);
      }
    }
  }

  private void createConnections() {
    for (int row = 0; row < Constants.VERTICAL_ROOMS; row++) {
      for (int column = 0; column < Constants.HORIZONTAL_ROOMS; column++) {
        populateConnectedRooms(row, column);
      }
    }
  }

  private void populateConnectedRooms(int row, int column) {
    List<Room> connectedRooms = new ArrayList<>();
    if (row - 1 >= 0) connectedRooms.add(getRoom(row - 1, column));
    if (column - 1 >= 0) connectedRooms.add(getRoom(row, column - 1));
    if (row + 1 < Constants.VERTICAL_ROOMS) connectedRooms.add(getRoom(row + 1, column));
    if (column + 1 < Constants.HORIZONTAL_ROOMS) connectedRooms.add(getRoom(row, column + 1));
    rooms[row][column].setConnectedRooms(connectedRooms);
  }

  public Room getRoom(int row, int col) {
    return rooms[row][col];
  }

  public Element getElement() {
    return element;
  }

  public Room[][] getRooms() {
    return rooms;
  }

  private String generateBorder(Integer roomWidth) {
    String border = "+";
    for (int row = 0; row < Constants.HORIZONTAL_ROOMS; row++) {
      border += "-".repeat(roomWidth) + "+";
    }
    return border + "\n";
  }
  public String toString(Integer roomWidth) {
    StringBuilder representation = new StringBuilder(element.name() + " Floor:\n");
    String border = generateBorder(roomWidth);
    for (int row = 0; row < Constants.HORIZONTAL_ROOMS; row++) {
      representation.append(border);
      for (int column = 0; column < Constants.VERTICAL_ROOMS; column++) {
        Room room = rooms[row][column];
        representation.append("|");
        representation.append(center(room.toString(), roomWidth));
      }
      representation.append("|\n");
    }
    representation.append(border);
    return representation.toString();
  }

  public String toString() {
    int roomWidth = 30;
    return toString(roomWidth);
  }
}
