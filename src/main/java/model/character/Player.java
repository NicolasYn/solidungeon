package model.character;

import model.character.playerstate.DefaultPlayerState;
import model.character.playerstate.PlayerState;
import model.character.playerstate.PlayerStateObserver;
import model.room.Room;
import model.room.RoomChangeObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Character {

    protected Inventory inventory;
    protected Room room;
    protected PlayerState state;
    private List<RoomChangeObserver> roomChangeObservers;
    private List<PlayerStateObserver> playerStateObservers;

    public Player(double strength, double vitality) {
        super(strength, vitality);
        this.room = null;
        this.inventory = new DefaultInventory();
        this.state = new DefaultPlayerState();
        this.roomChangeObservers = new ArrayList<>();
        this.playerStateObservers = new ArrayList<>();
    }

    public void changeState(PlayerState state) {
        setState(state);
        doAction();
        notifyPlayerStateObserver(state);
    }

    private void setState(PlayerState state) {
        this.state = state;
    }

    public abstract String description();

    public abstract void doAction();

    @Override
    public String getType() {
        return "Player";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public PlayerState getState() {
        return state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        notifyRoomChangeObservers(room);
    }

    public void addRoomChangeObserver(RoomChangeObserver observer) {
        roomChangeObservers.add(observer);
    }

    private void notifyRoomChangeObservers(Room newRoom) {
        for (RoomChangeObserver observer : roomChangeObservers) {
            observer.update(newRoom);
        }
    }

    public void addPlayerStateObservers(PlayerStateObserver observer) {
        playerStateObservers.add(observer);
    }

    private void notifyPlayerStateObserver(PlayerState state) {
        for (PlayerStateObserver observer : playerStateObservers) {
            observer.update(state);
        }
    }

    public List<PlayerStateObserver> getPlayerStateObservers() {
        return playerStateObservers;
    }
}
