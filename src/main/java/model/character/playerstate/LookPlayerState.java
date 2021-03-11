package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public class LookPlayerState implements PlayerState {

    private Direction direction;
    private boolean roomExists;

    public LookPlayerState(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom())) {
            roomExists = player.getRoom().roomExists(direction);
        }
    }

    @Override
    public boolean isPossible(Room room) {
        return !room.containsMonster();
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        if (roomExists) descriptionBuilder.lookDescription(direction);
        else descriptionBuilder.lookDescriptionWall(direction);
    }
}
