package com.arcane.ui;

import com.arcane.Element;
import com.arcane.board.rooms.ElementalRoom;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import com.arcane.board.rooms.Room;

public class MazeDrawer extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private List<Room> rooms;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Room Connections");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);

        // Create rooms and add neighbors
        rooms = createRooms();

        // Draw connections on canvas
        drawConnections(canvas.getGraphicsContext2D());

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        Room room1 = new ElementalRoom(Element.AIR,1, 1);
        Room room2 = new ElementalRoom(Element.AIR,3, 1);
        Room room3 = new ElementalRoom(Element.AIR,3, 3);

        room1.setConnectedRooms(List.of(room2, room3));
        room2.setConnectedRooms(List.of(room3));

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return rooms;
    }

    private void drawConnections(GraphicsContext gc) {
        for (Room room : rooms) {
            double startX = room.column * 100;
            double startY = room.row * 100;

            for (Room neighbor : room.getConnectedRooms()) {
                double endX = neighbor.column;
                double endY = neighbor.row;

                // Draw line between rooms
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                gc.strokeLine(startX, startY, endX, endY);
            }
        }

        // Draw rooms
        for (Room room : rooms) {
            gc.setFill(Color.BLUE);
            gc.fillOval(room.column - 10, room.row - 10, 20, 20);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
