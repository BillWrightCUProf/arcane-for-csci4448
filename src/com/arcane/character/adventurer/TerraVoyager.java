package com.arcane.character.adventurer;

import com.arcane.Element;

public class TerraVoyager extends Adventurer {

  private boolean isResonance = false;

  public TerraVoyager() {
    super(7, 10, Element.EARTH, Element.FIRE, AdventurerAcronym.TERRA_VOYAGER);
  }

  @Override
  protected void elementalResonance() {
    isResonance = true;
    this.addHealth(3);
  }

  @Override
  protected void elementalDiscord() {
    isResonance = false;
    this.addHealth(-3);
  }

  @Override
  protected void elementalReset() {
    if (isResonance) {
      this.addHealth(-3);
    } else {
      this.addHealth(+3);
    }
  }
}
