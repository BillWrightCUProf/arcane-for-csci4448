package com.arcane.mazeprinter;

import com.arcane.board.Direction;
import com.arcane.board.rooms.Room;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.center;

public class ConsoleMazePrinter {
    public static final int ROOM_WIDTH = 30;
    List<Room> rooms;

    ConsoleMazePrinter(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getNorthWestCorner() {
        // get northwest corner of grid
        for (Room room : rooms) {
            if (
                    room.hasNeighborInDirection(Direction.East) &&
                            room.hasNeighborInDirection(Direction.South) &&
                            !room.hasNeighborInDirection(Direction.North) &&
                            !room.hasNeighborInDirection(Direction.West)
            ) {
                return room;
            }
        }

        // get furthest west of a row
        for (Room room : rooms) {
            if (
                    room.hasNeighborInDirection(Direction.East) &&
                            !room.hasNeighborInDirection(Direction.North) &&
                            !room.hasNeighborInDirection(Direction.West)
            ) {
                return room;
            }
        }

        // get furthest north of a column
        for (Room room : rooms) {
            if (
                    !room.hasNeighborInDirection(Direction.North) &&
                            room.hasNeighborInDirection(Direction.South)
            ) {
                return room;
            }
        }
        // We should never get here.
        return rooms.get(0);
    }

    public List<List<String>> generateRow(List<List<String>> roomRepresentations, Room startingRoom) {
        List<String> roomLines = startingRoom.getRepresentation();
        roomRepresentations.add(roomLines);
        if (startingRoom.hasNeighborInDirection(Direction.East)) {
            return generateRow(roomRepresentations, startingRoom.getNeighbor(Direction.East));
        }
        return roomRepresentations;
    }
    public void printGrid() {
        List<List<List<String>>> rows = new ArrayList<>();
        Room westEdge = getNorthWestCorner();
        List<List<String>> currentRow = generateRow(new ArrayList<>(), westEdge);
        rows.add(currentRow);

        while (westEdge.hasNeighborInDirection(Direction.South)) {
            westEdge = westEdge.getNeighbor(Direction.South);
            currentRow = generateRow(new ArrayList<>(), westEdge);
            rows.add(currentRow);
        }

        for (List<List<String>> row : rows) {
            printRowDivider(row.size());

            int maxLines = row.stream().map(List::size).max(Integer::compareTo).orElse(0);
            for (int roomLine = 0; roomLine < maxLines; roomLine++) {
                StringBuilder oneLine = new StringBuilder("|");
                for (List<String> strings : row) {
                    try {
                        oneLine.append(center(strings.get(roomLine), ROOM_WIDTH));
                    } catch (IndexOutOfBoundsException ex) {
                        oneLine.append(center("", 30));
                    }
                    oneLine.append('|');
                }
                System.out.println(oneLine);
            }
        }
        printRowDivider(rows.get(rows.size()-1).size());
    }

    private void printRowDivider(int numRooms) {
        String divider = "+";
        divider += "-".repeat(ROOM_WIDTH);
        System.out.println(divider.repeat(numRooms) + "+");
    }
}