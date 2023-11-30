package com.arcane.mazeprinter;

import com.arcane.Element;
import com.arcane.board.Direction;
import com.arcane.board.rooms.ElementalRoom;
import com.arcane.board.rooms.Room;
import com.arcane.board.rooms.StartingRoom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsoleMazePrinterTest {

    @Test
    void findNorthWestCornerOfTwoByTwoMaze() {
        // Since when printing to console, we'll necessarily have to start in
        // the northwest corner, we need to find the room that only has connections
        // to the east and the south.

        Room northWestRoom = new ElementalRoom(Element.AIR,"northwest");
        Room northEastRoom = new ElementalRoom(Element.AIR,"northeast");
        Room southWestRoom = new ElementalRoom(Element.EARTH,"southwest");
        Room southEastRoom = new ElementalRoom(Element.AIR,"southeast");

        Room.connect(northWestRoom, northEastRoom, Direction.East);
        Room.connect(southWestRoom, southEastRoom, Direction.East);
        Room.connect(northWestRoom, southWestRoom, Direction.South);
        Room.connect(northEastRoom, southEastRoom, Direction.South);

        List<Room> twoByTwoMaze = new ArrayList<>();
        twoByTwoMaze.add(northWestRoom);
        twoByTwoMaze.add(northEastRoom);
        twoByTwoMaze.add(southWestRoom);
        twoByTwoMaze.add(southEastRoom);

        ConsoleMazePrinter consoleMazePrinter = new ConsoleMazePrinter(twoByTwoMaze);

        assert consoleMazePrinter.getNorthWestCorner() == northWestRoom;
        consoleMazePrinter.printGrid();
    }


    @Test
    void getNorthwestCornerOfOneRoomMaze() {
        Room northWestRoom = new ElementalRoom(Element.AIR,"northwest");
        List<Room> oneRoomMaze = new ArrayList<>();
        oneRoomMaze.add(northWestRoom);

        ConsoleMazePrinter consoleMazePrinter = new ConsoleMazePrinter(oneRoomMaze);
        assert consoleMazePrinter.getNorthWestCorner() == northWestRoom;

        consoleMazePrinter.printGrid();
    }

    @Test
    void findNorthWestCornerOfNonRectangularMaze() {
        // Since when printing to console, we'll necessarily have to start in
        // the northwest corner, we need to find the room that only has connections
        // to the east and the south.

        Room startingRoom = new StartingRoom("start here");

        Room northWestRoom = new ElementalRoom(Element.AIR,"northwest");
        Room northEastRoom = new ElementalRoom(Element.AIR,"northeast");
        Room southWestRoom = new ElementalRoom(Element.EARTH,"southwest");
        Room southMiddleRoom = new ElementalRoom(Element.AIR,"south middle");
        Room southEastRoom = new ElementalRoom(Element.AIR,"southeast");

        Room.connect(startingRoom, northWestRoom, Direction.South);
        Room.connect(northWestRoom, northEastRoom, Direction.East);
        Room.connect(southWestRoom, southMiddleRoom, Direction.East);
        Room.connect(southMiddleRoom, southEastRoom, Direction.East);
        Room.connect(northWestRoom, southWestRoom, Direction.South);
        Room.connect(northEastRoom, southMiddleRoom, Direction.South);

        List<Room> irregularMaze = new ArrayList<>();
        irregularMaze.add(startingRoom);
        irregularMaze.add(northWestRoom);
        irregularMaze.add(northEastRoom);
        irregularMaze.add(southWestRoom);
        irregularMaze.add(southMiddleRoom);
        irregularMaze.add(southEastRoom);

        ConsoleMazePrinter consoleMazePrinter = new ConsoleMazePrinter(irregularMaze);

        assert consoleMazePrinter.getNorthWestCorner() == startingRoom;
        consoleMazePrinter.printGrid();
    }
}
