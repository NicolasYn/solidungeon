package model;

import model.character.Player;
import model.character.playerstate.PlayerStateObserver;
import model.duel.DuelStrategy;
import model.room.RoomChangeObserver;
import model.room.RoomManager;

public class Dungeon {

    private RoomManager roomManager;
    private Player player;
    private RoomChangeObserver roomChangeObserver;
    private PlayerStateObserver playerStateObserver;
    private DuelStrategy duelStrategy;

    public Dungeon(RoomManager roomManager, Player player, RoomChangeObserver roomChangeObserver,
                   PlayerStateObserver playerStateObserver, DuelStrategy duelStrategy) {
        this.roomManager = roomManager;
        this.player = player;
        this.roomChangeObserver = roomChangeObserver;
        this.playerStateObserver = playerStateObserver;
        this.duelStrategy = duelStrategy;
        player.setRoom(roomManager.getFirstRoom());
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public Player getPlayer() {
        return player;
    }

    public RoomChangeObserver getRoomChangeObserver() {
        return roomChangeObserver;
    }

    public PlayerStateObserver getPlayerStateObserver() {
        return playerStateObserver;
    }

    public DuelStrategy getDuelStrategy() {
        return duelStrategy;
    }
}
