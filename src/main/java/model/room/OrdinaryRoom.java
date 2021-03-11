package model.room;

import model.character.DefaultMonster;
import model.character.Monster;
import model.character.MonsterFactory;
import model.character.playerstate.Direction;
import model.duel.DuelStrategy;
import model.treasure.Treasure;
import model.treasure.TreasureFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdinaryRoom implements Room {

    static final int MAX_NUMBER_MONSTERS = 1;
    static final int MAX_NUMBER_TREASURE = 1;
    static final String ROOM_TYPE = "ORDINARY";

    private List<Treasure> treasures;
    private List<Monster> monsters;
    private Map<Direction, Room> neighborsRooms;

    public OrdinaryRoom(int numberTreasures, int numberMonsters, Map<Direction, Room> neighborsRooms) {
        treasures = generateTreasures(numberTreasures);
        monsters = generateMonsters(numberMonsters);
        this.neighborsRooms = neighborsRooms;
    }

    @Override
    public boolean containsTreasure() {
        return !treasures.isEmpty();
    }

    @Override
    public boolean containsMonster() {
        for (Monster monster : monsters) {
            if (monster.isAlive()) return true;
        }
        return false;
    }

    @Override
    public int getNumberMonsters() {
        return monsters.size();
    }

    @Override
    public int getNumberTreasures() {
        return treasures.size();
    }

    @Override
    public void provokeDuel(DuelStrategy duelStrategy) {
        for (Monster monster : monsters) {
            duelStrategy.battle(monster);
            if (!duelStrategy.isPlayerWinner()) break;
        }
    }

    private List<Treasure> generateTreasures(int numberTreasures) {
        List<Treasure> treasures = new ArrayList<>();
        for (int i = 0; i < numberTreasures; i++) {
            treasures.add(createRandomTreasure());
        }
        return treasures;
    }

    private List<Monster> generateMonsters(int numberMonsters) {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < numberMonsters; i++) {
            monsters.add(createDefaultMonster());
        }
        return monsters;
    }

    private Treasure createRandomTreasure() {
        return TreasureFactory.createRandomTreasure();
    }

    private Monster createDefaultMonster() {
        return MonsterFactory.createMonster(DefaultMonster.TYPE);
    }

    @Override
    public List<Treasure> getTreasures() {
        return treasures;
    }

    @Override
    public List<Monster> getMonsters() {
        for (Monster monster : monsters) {
            if (!monster.isAlive()) monsters.remove(monster);
        }
        return monsters;
    }

    @Override
    public boolean roomExists(Direction direction) {
        return neighborsRooms.containsKey(direction);
    }

    @Override
    public Room getNeighbourRoomInDirection(Direction direction) {
        if (!roomExists(direction)) return null;
        return neighborsRooms.get(direction);
    }

    @Override
    public void addNeighbourRoom(Direction direction, Room room) {
        neighborsRooms.putIfAbsent(direction, room);
    }

    @Override
    public String getType() {
        return ROOM_TYPE;
    }

    @Override
    public String toString() {
        return "1";
    }
}
