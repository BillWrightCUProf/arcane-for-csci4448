Feature: Showing the capabilities of the Adventurers

  Scenario: The Adventurer's health can be updated
    Given an Adventurer with a health of 5
    When the Adventurer loses 3 health points
    Then the Adventurer is still alive

  Scenario: The Adventurer can die
    Given an Adventurer with a health of 5
    When the Adventurer loses 6 health points
    Then the Adventurer is dead