package model.room;

import model.character.Monster;
import model.character.playerstate.Direction;
import model.duel.DuelStrategy;
import model.treasure.Treasure;

import java.util.List;

public interface Room {

    boolean containsTreasure();
    boolean containsMonster();
    int getNumberMonsters();
    int getNumberTreasures();
    void provokeDuel(DuelStrategy duelStrategy);
    List<Treasure> getTreasures();
    List<Monster> getMonsters();
    boolean roomExists(Direction direction);
    Room getNeighbourRoomInDirection(Direction direction);
    void addNeighbourRoom(Direction direction, Room room);
    String getType();
}
