package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public class DefaultPlayerState implements PlayerState {

    @Override
    public void doAction(Player player) {

    }

    @Override
    public boolean isPossible(Room room) {
        return true;
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {

    }
}
