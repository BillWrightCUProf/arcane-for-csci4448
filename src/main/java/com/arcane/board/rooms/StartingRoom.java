package com.arcane.board.rooms;

import com.arcane.util.Constants;

import java.util.List;

public class StartingRoom extends Room {

  public StartingRoom() {
    super("StartingRoom", 0, 0);
  }

  public StartingRoom(String name) {
    super(name);
  }

  @Override
  public String getRoomId() {
    return Constants.STARTING_ROOM_ID;
  }

  @Override
  public List<String> getRepresentation() {
    List<String> repr = super.getRepresentation();
    repr.add("STARTING ROOM");
    return repr;
  }
}
