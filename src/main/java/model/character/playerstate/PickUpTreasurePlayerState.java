package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;
import model.treasure.Treasure;

import java.util.Random;

public class PickUpTreasurePlayerState implements PlayerState {

    private Treasure randomTreasure;

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom()) && !player.getInventory().isFull()) {
            randomTreasure = chooseRandomTreasure(player.getRoom());
            player.getRoom().getTreasures().remove(randomTreasure);
            player.getInventory().add(randomTreasure);
        }
    }

    @Override
    public boolean isPossible(Room room) {
        return room.containsTreasure();
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        if (randomTreasure != null) {
            descriptionBuilder.pickUpTreasureDescription(randomTreasure);
        } else {
            descriptionBuilder.notContainsTreasure();
        }
    }

    private Treasure chooseRandomTreasure(Room room) {
        return room.getTreasures().get(new Random().nextInt(room.getNumberTreasures()));
    }
}
