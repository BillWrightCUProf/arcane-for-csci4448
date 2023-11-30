package com.arcane.board.rooms;

import com.arcane.Element;

import java.util.List;

public class ElementalRoom extends Room {

  private final Element element;

  public ElementalRoom(Element element, String name) {
    super(name);
    this.element = element;
  }

  public ElementalRoom(Element element, Integer row, Integer column) {
    super(row.toString() + column.toString(), row, column);
    this.element = element;
  }

  @Override
  public String getRoomId() {
    return element + "-" + row + "-" + column;
  }

  @Override
  public List<String> getRepresentation() {
    List<String> repr = super.getRepresentation();
    repr.add(element.toString());
    if (element.equals(Element.EARTH)) {
      repr.add("extra");
    }
    return repr;
  }
}
