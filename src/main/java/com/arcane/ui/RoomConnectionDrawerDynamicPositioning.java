package com.arcane.ui;

import com.arcane.Element;
import com.arcane.board.rooms.ElementalRoom;
import com.arcane.board.rooms.Room;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class RoomConnectionDrawerDynamicPositioning extends Frame {
    public RoomConnectionDrawerDynamicPositioning(List<Room> rooms) {
        setTitle("Room Connection Drawer");
        setSize(600, 600);

        DrawingCanvas drawingCanvas = new DrawingCanvas(rooms);
        add(drawingCanvas);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        Room room1 = new ElementalRoom(Element.AIR, 1,1);
        Room room2 = new ElementalRoom(Element.AIR, 1,2);
        Room room3 = new ElementalRoom(Element.AIR, 2,1);
        Room room4 = new ElementalRoom(Element.AIR, 2,2);

        room1.setConnectedRooms(List.of(room2, room3));
        room2.setConnectedRooms(List.of(room1, room4));
        room3.setConnectedRooms(List.of(room1, room4));
        room4.setConnectedRooms(List.of(room3));

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        new RoomConnectionDrawerDynamicPositioning(rooms);
    }
}