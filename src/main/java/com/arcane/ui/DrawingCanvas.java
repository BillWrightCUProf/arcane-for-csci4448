package com.arcane.ui;

import com.arcane.board.rooms.Room;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class DrawingCanvas extends Canvas {
    Logger logger = Logger.getLogger(DrawingCanvas.class.getName());
    logger.setLevel(Level.FINE)
    private final Map<Room, Point> roomPositions;

    public DrawingCanvas(List<Room> rooms) {
        this.roomPositions = calculateRoomPositions(rooms);
    }

    private Map<Room, Point> calculateRoomPositions(List<Room> rooms) {
        Map<Room, Point> positions = new HashMap<>();
        int totalRooms = rooms.size();

        // Calculate positions based on a circular layout
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        logger.log(Level.ALL, "Center is: " + centerX + ", " + centerY);

        int radius = Math.min(centerX, centerY) - 50;
        double angleIncrement = 2 * Math.PI / totalRooms;
        double currentAngle = 0;

        for (Room room : rooms) {
            int x = (int) (centerX + radius * Math.cos(currentAngle));
            int y = (int) (centerY + radius * Math.sin(currentAngle));
            positions.put(room, new Point(x, y));

            currentAngle += angleIncrement;
        }

        return positions;
    }

    @Override
    public void paint(Graphics g) {
        for (Map.Entry<Room, Point> entry : roomPositions.entrySet()) {
            Room room = entry.getKey();
            Point position = entry.getValue();
            int roomX = position.x - 25; // Adjust for room width
            int roomY = position.y - 25; // Adjust for room height

            // Draw Room
            System.out.println("Drawing a room of 50x50 starting at " + roomX + ", " + roomY);
            g.drawRect(roomX, roomY, 50, 50);

            // Draw Connections
            for (Room connectedRoom : room.getConnectedRooms()) {
                Point connectedPosition = roomPositions.get(connectedRoom);
                int connectedX = connectedPosition.x;
                int connectedY = connectedPosition.y;

                // Draw Line
                g.drawLine(position.x, position.y, connectedX, connectedY);
            }
        }
    }
}
