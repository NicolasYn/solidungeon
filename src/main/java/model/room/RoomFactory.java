package model.room;

import model.character.playerstate.Direction;

import java.util.Map;
import java.util.Random;

public class RoomFactory {

    public static Room createRoom(String roomType, Map<Direction, Room> neighborsRooms) {
        if (roomType.equalsIgnoreCase(OrdinaryRoom.ROOM_TYPE)) {
            return new OrdinaryRoom(new Random().nextInt(OrdinaryRoom.MAX_NUMBER_TREASURE+1),
                    new Random().nextInt(OrdinaryRoom.MAX_NUMBER_MONSTERS+1), neighborsRooms);
        } else if (roomType.equalsIgnoreCase(FinalRoom.ROOM_TYPE)) return new FinalRoom();
        else if (roomType.equalsIgnoreCase(RoomManager.FIRST_TYPE_ROOM)) {
            return new OrdinaryRoom(0, 0, neighborsRooms);
        }
        else throw new IllegalArgumentException("Invalid room type");
    }

}
