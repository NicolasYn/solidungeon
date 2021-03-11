package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public interface PlayerState {

    void doAction(Player player);
    boolean isPossible(Room room);
    void description(DescriptionBuilder descriptionBuilder);

}
