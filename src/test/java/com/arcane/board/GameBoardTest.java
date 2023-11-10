package com.arcane.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void testStringRepresentation() {
        GameBoard board = new GameBoard();
        String representation = board.toString();
        System.out.println(representation);
        assertTrue(representation.contains("EMBER_KNIGHT"));
    }

}