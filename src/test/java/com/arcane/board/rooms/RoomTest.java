package com.arcane.board.rooms;

import com.arcane.character.adventurer.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    @Test
    void testStringRepresentation() {
        Room room = new StartingRoom();
        Adventurer knight = new EmberKnight();
        room.addAdventurer(knight);
        String roomRepresentation = room.toString();
        assertEquals("EMBER_KNIGHT:", roomRepresentation);
    }
}
