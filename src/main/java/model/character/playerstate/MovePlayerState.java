package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public class MovePlayerState implements PlayerState {

    private Direction direction;
    private Room newRoom;
    private boolean containsMonster;

    public MovePlayerState(Direction direction) {
        this.direction = direction;
        this.containsMonster = false;
    }

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom())) {
            newRoom = player.getRoom().getNeighbourRoomInDirection(direction);
            if (containsMonster(player.getRoom())) {
                containsMonster = true;
                return;
            }
            player.setRoom(newRoom);
        }
    }

    private boolean containsMonster(Room room) {
        return room.containsMonster();
    }

    @Override
    public boolean isPossible(Room room) {
        return room.roomExists(direction);
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        if (newRoom != null && !containsMonster) {
            descriptionBuilder.roomDescription(newRoom);
        } else if (containsMonster) {
            descriptionBuilder.duelBeforeLeaveRoom();
        } else {
            descriptionBuilder.impossibleRoomDescription();
        }
    }

}
