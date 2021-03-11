package model.room;

import model.character.BossMonster;
import model.character.Monster;
import model.character.MonsterFactory;
import model.character.playerstate.Direction;
import model.duel.DuelStrategy;
import model.treasure.Treasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalRoom implements Room {

    public static final String ROOM_TYPE = "FINAL";

    private Monster bossMonster;

    public FinalRoom() {
        this.bossMonster = generateMonster();
    }

    @Override
    public boolean containsTreasure() {
        return false;
    }

    @Override
    public boolean containsMonster() {
        return bossMonster.isAlive();
    }

    @Override
    public int getNumberMonsters() {
        return containsMonster() ? 1 : 0;
    }

    @Override
    public int getNumberTreasures() {
        return 0;
    }

    @Override
    public void provokeDuel(DuelStrategy duelStrategy) {
        if (containsMonster()) duelStrategy.battle(bossMonster);
    }

    @Override
    public List<Treasure> getTreasures() {
        return null;
    }

    @Override
    public List<Monster> getMonsters() {
        return new ArrayList<>(Collections.singleton(bossMonster));
    }

    @Override
    public boolean roomExists(Direction direction) {
        return false;
    }

    @Override
    public Room getNeighbourRoomInDirection(Direction direction) {
        return null;
    }

    @Override
    public void addNeighbourRoom(Direction direction, Room room) {

    }

    @Override
    public String getType() {
        return ROOM_TYPE;
    }

    private Monster generateMonster() {
       return MonsterFactory.createMonster(BossMonster.TYPE);
    }

    @Override
    public String toString() {
        return "2";
    }
}
