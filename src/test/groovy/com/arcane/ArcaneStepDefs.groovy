package com.arcane

import com.arcane.character.adventurer.Adventurer
import com.arcane.character.adventurer.AdventurerAcronym
import com.arcane.character.adventurer.EmberKnight
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import java.time.LocalDateTime

class ArcaneStepDefs {

    Adventurer adventurer

    @Given("an Adventurer with a health of {int}")
    void createAdventurer(Integer health) {
        // Randomize name
        String name = 'Adventurer' + LocalDateTime.now().getNano()
        adventurer = new EmberKnight()
    }

    @When("the Adventurer loses {int} health points")
    void iAddToTheHealth(Integer healthLoss) {
//        adventurer.loseHealth(healthLoss);
    }

    @Then("the Adventurer is still alive")
    void theAdventurerHasAHealthOf() {
        assert adventurer.isAlive()
    }

    @Then("the Adventurer is dead")
    void theAdventurerIsDead() {
        assert !adventurer.isAlive();
    }
}