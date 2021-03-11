package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public class DiePlayerState implements PlayerState {

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom())) {
            player.setStrength(0);
            player.setVitality(0);
        }
    }

    @Override
    public boolean isPossible(Room room) {
        return room.containsMonster();
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        descriptionBuilder.deathPlayerDescription();
    }
}
