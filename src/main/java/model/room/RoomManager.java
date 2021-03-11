package model.room;

import javafx.util.Pair;
import model.character.playerstate.Direction;
import model.treasure.TreasureDecoratorFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RoomManager {

    private Room[][] map;
    private boolean[][] visited;
    private Map<Room, Pair<Integer, Integer>> roomReferenceToCoordinates;
    private double visitedNumber;
    private double initializedNumber;
    private boolean isFinalRoomExists;

    private final String DEFAULT_TYPE_ROOM = "ORDINARY";
    private final String FINAL_TYPE_ROOM = "FINAL";
    final static String FIRST_TYPE_ROOM = "FIRST";
    private final int ORDINATE_FIRST_ROOM = 0;
    private final int ABSCISSA_FIRST_ROOM = 0;
    private final double PROBABILITY_WALL = 0.2;
    private static final double MINIMUM_ROOM_VISITED_BEFORE_FINAL = 0.75;
    private static final double PROBABILITY_FINAL = 0.33;
    public static final double MINIMUM_ROOM_VISITED_BEFORE_IMPROVER = 0.5;
    private static final double PROBABILITY_IMPROVER = 0.4;

    public RoomManager(int side) {
        this.map = new Room[side][side];
        this.visited = new boolean[side][side];
        this.roomReferenceToCoordinates = new HashMap<>();
        this.visitedNumber = 1;
        this.initializedNumber = 1;
        this.isFinalRoomExists = false;
        firstRoomInitialization();
    }

    public boolean isRoomAlreadyInitialized(int ordinate, int abscissa) {
        return map[ordinate][abscissa] != null;
    }

    public boolean isRoomAlreadyVisited(int ordinate, int abscissa) {
        return visited[ordinate][abscissa];
    }

    public Room[][] getMap() {
        return map;
    }

    public void setVisited(Room room) {
         visited[getCoordinates(room).getKey()][getCoordinates(room).getValue()] = true;
         visitedNumber++;
    }

    public void initializeNeighborsRooms(Room room) {
        initializeNeighborsRooms(getCoordinates(room).getKey(), getCoordinates(room).getValue(), DEFAULT_TYPE_ROOM);
    }

    private void initializeRoom(int ordinate, int abscissa, String roomType) {
        if (canInitializeRoom(ordinate, abscissa)){
            String roomTypeDecision = isFinalRoom() ? FINAL_TYPE_ROOM : roomType;
            isFinalRoomExists = roomTypeDecision.equals(FINAL_TYPE_ROOM);
            map[ordinate][abscissa] = RoomFactory.createRoom(roomTypeDecision, new HashMap<>());
            addReferenceCoordinatesMap(map[ordinate][abscissa], ordinate, abscissa);
            initializedNumber++;
            if (isRoomContainsImprover()) initializeImprover(map[ordinate][abscissa]);
        }
    }

    private void initializeNeighborsRooms(int ordinate, int abscissa, String roomType) {
        LinkedList<Pair<Integer, Integer>> positions = getPossibleNeighborsPosition(ordinate, abscissa);
        for (int i = 0; i < positions.size(); i++) {
            int ordinatePosition = positions.get(i).getKey();
            int abscissaPosition = positions.get(i).getValue();
            if (canInitializeRoom(ordinatePosition, abscissaPosition) && isVisitable()){
                initializeRoom(ordinatePosition, abscissaPosition, roomType);
            }
            addNeighbourRoom(ordinate, abscissa, i, ordinatePosition, abscissaPosition);
        }
    }

    private void addNeighbourRoom(int ordinate, int abscissa, int i, int ordinatePosition, int abscissaPosition) {
        if (canRoomExists(ordinatePosition, abscissaPosition) && isRoomAlreadyInitialized(ordinatePosition, abscissaPosition)) {
            map[ordinate][abscissa].addNeighbourRoom(Direction.values()[i], map[ordinatePosition][abscissaPosition]);
        }
    }

    private boolean canRoomExists(int ordinate, int abscissa) {
        return 0 <= ordinate && ordinate < map.length && 0 <= abscissa && abscissa < map.length;
    }

    private boolean canInitializeRoom(int ordinate, int abscissa) {
        return canRoomExists(ordinate, abscissa) && !isRoomAlreadyInitialized(ordinate, abscissa);
    }

    private boolean isVisitable() {
        return Math.random() > PROBABILITY_WALL;
    }

    private LinkedList<Pair<Integer, Integer>> getPossibleNeighborsPosition(int ordinate, int abscissa) {
        LinkedList<Pair<Integer, Integer>> positions = new LinkedList<>();
        positions.add(new Pair<>(ordinate-1, abscissa));
        positions.add(new Pair<>(ordinate+1, abscissa));
        positions.add(new Pair<>(ordinate, abscissa+1));
        positions.add(new Pair<>(ordinate, abscissa-1));
        return positions;
    }

    private void addReferenceCoordinatesMap(Room reference, int ordinate, int abscissa) {
        roomReferenceToCoordinates.putIfAbsent(reference, new Pair<>(ordinate, abscissa));
    }

    public Pair<Integer, Integer> getCoordinates(Room room) {
        return roomReferenceToCoordinates.get(room);
    }

    private double numberVisitedRoomsOnTotalRooms() {
        return visitedNumber / totalRoomsNumber();
    }

    private double totalRoomsNumber() {
        return visited.length*visited.length;
    }

    private boolean isFinalRoom() {
        return !isFinalRoomExists &&
                ((numberVisitedRoomsOnTotalRooms() > MINIMUM_ROOM_VISITED_BEFORE_FINAL && Math.random() < PROBABILITY_FINAL)
                        || initializedNumber == totalRoomsNumber()-1);
    }

    private void initializeImprover(Room room) {
        if (isRoomContainsImprover()) {
            room.getTreasures().add(TreasureDecoratorFactory.createRandomTreasureDecorator());
        }
    }

    private boolean isRoomContainsImprover() {
        return numberVisitedRoomsOnTotalRooms() >= MINIMUM_ROOM_VISITED_BEFORE_IMPROVER && Math.random() < PROBABILITY_IMPROVER;
    }

    private void firstRoomInitialization() {
        initializeRoom(ORDINATE_FIRST_ROOM, ABSCISSA_FIRST_ROOM, FIRST_TYPE_ROOM);
        initializeNeighborsRooms(ORDINATE_FIRST_ROOM, ABSCISSA_FIRST_ROOM, DEFAULT_TYPE_ROOM);
        visited[ORDINATE_FIRST_ROOM][ABSCISSA_FIRST_ROOM] = true;
    }

    public Room getFirstRoom() {
        return map[ORDINATE_FIRST_ROOM][ABSCISSA_FIRST_ROOM];
    }

    public boolean[][] getVisited() {
        return visited;
    }
}
