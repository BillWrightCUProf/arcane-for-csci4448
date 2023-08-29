package com.arcane.board.rooms;

import com.arcane.Element;

// This class is an example for encapsulation
// All the attributes are hidden from other classes
// it provides getters and setters to access these attributes
public class ElementalRoom extends Room {

  private final Element element;

  public ElementalRoom(Element element, int row, int column) {
    super(row, column);
    this.element = element;
  }

  @Override
  public String getRoomId() {
    return element + "-" + row + "-" + column;
  }
}
