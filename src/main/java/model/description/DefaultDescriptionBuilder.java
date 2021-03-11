package model.description;

import model.Dungeon;
import model.character.Character;
import model.character.Player;
import model.character.playerstate.Direction;
import model.room.Room;
import model.treasure.Treasure;

import java.util.ArrayList;
import java.util.List;

public class DefaultDescriptionBuilder implements DescriptionBuilder {

    private StringBuilder description;
    private List<String> descriptionDuel;

    public DefaultDescriptionBuilder() {
        this.description = new StringBuilder();
        this.descriptionDuel = new ArrayList<>(3);
        descriptionDuel.add(" "); descriptionDuel.add(" ");

    }

    @Override
    public void dungeonDescription(Dungeon dungeon) {
        description.append("Welcome to the Dungeon ").append(dungeon.getRoomManager().getMap().length).append("*")
                .append(dungeon.getRoomManager().getMap().length).append(" rooms \n");
    }

    @Override
    public void roomDescription(Room room) {
        if (descriptionDuel.size() > 2) {
            descriptionDuel.set(0, "You entered in room with "+room.getNumberTreasures()
                    + " treasures and " + room.getNumberMonsters() + " monsters \n");
        } else {
            description.append("You entered in room with ").append(room.getNumberTreasures())
                    .append(" treasures and ").append(room.getNumberMonsters()).append(" monsters \n");
        }
    }

    @Override
    public void pickUpTreasureDescription(Treasure treasure) {
        description.append("You pick up a " + treasure.description());
    }

    @Override
    public void useTreasureDescription(Player player, Treasure treasure) {
        description.append("Use of " + treasure.toString() + "\n" + player.getType() + " with " + player.getStrength()
                + " strength and " + player.getVitality() + " vitality");
    }

    @Override
    public void lookDescription(Direction direction) {
        description.append("There is a room to the " + direction);
    }

    @Override
    public void lookDescriptionWall(Direction direction) {
        description.append("There is a wall to the " + direction);
    }

    @Override
    public void duelDescription(Character attacker, Character attacked) {
        descriptionDuel.set(1, "Duel between " + attacker.getType() + " and " + attacked.getType()+"\n");
    }

    @Override
    public void descriptionAttackDuel(Character attacker, Character attacked, double damage) {
        descriptionDuel.add(attacker.getType() + " attack on " + attacked.getType() + " with " + damage + "\n");
    }

    @Override
    public void impossibleRoomDescription() {
        description.append("You can't go in this direction");
    }

    @Override
    public void duelBeforeLeaveRoom() {
        description.append("You can't leave room before duel, press c to duel");
    }

    @Override
    public void notContainsTreasure() {
        description.append("This room doesn't contains treasure");
    }

    @Override
    public void cantUseTreasure() {
        description.append("You can't use a treasure in a room that contains monsters");
    }

    @Override
    public void deathPlayerDescription() {
        description.append("You are dead");
    }

    @Override
    public String getDescription() {
        return descriptionDuel.size() < 3 ? description.toString() + "\n" : formatDescriptionDuelString();
    }

    @Override
    public void resetDescription() {
        description = new StringBuilder();
        descriptionDuel = new ArrayList<>(3);
        descriptionDuel.add(" "); descriptionDuel.add(" ");
    }

    private String formatDescriptionDuelString() {
        return descriptionDuel.toString().replace("[", "")
                .replace(",", "").replace("]","");
    }
}
