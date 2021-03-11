package model.description;

import model.Dungeon;
import model.character.Character;
import model.character.Player;
import model.character.playerstate.Direction;
import model.room.Room;
import model.treasure.Treasure;

public interface DescriptionBuilder {

    String getDescription();

    void resetDescription();

    void roomDescription(Room room);

    void dungeonDescription(Dungeon dungeon);

    void pickUpTreasureDescription(Treasure treasure);

    void useTreasureDescription(Player player, Treasure treasure);

    void lookDescription(Direction direction);

    void lookDescriptionWall(Direction direction);

    void duelDescription(Character attacker, Character attacked);

    void descriptionAttackDuel(Character attacker, Character attacked, double damage);

    void impossibleRoomDescription();

    void duelBeforeLeaveRoom();

    void notContainsTreasure();

    void cantUseTreasure();

    void deathPlayerDescription();
}
