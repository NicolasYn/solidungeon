package model.room;

import model.character.Player;
import model.duel.DuelStrategy;

public class BasicRoomChangeObserver implements RoomChangeObserver {

    private RoomManager roomManager;
    private DuelStrategy duelStrategy;

    public BasicRoomChangeObserver(RoomManager roomManager, Player player, DuelStrategy duelStrategy) {
        this.roomManager = roomManager;
        this.duelStrategy = duelStrategy;
        player.addRoomChangeObserver(this);
    }

    @Override
    public void update(Room newRoom) {
        roomManager.setVisited(newRoom);
        roomManager.initializeNeighborsRooms(newRoom);
        newRoom.provokeDuel(duelStrategy);
    }
}
