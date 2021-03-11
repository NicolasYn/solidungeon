package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;
import model.treasure.Treasure;

public class UseTreasurePlayerState implements PlayerState {

    private int index;
    private Treasure usedTreasure;
    private Player player;

    public UseTreasurePlayerState(int index) {
        this.index = index;
    }

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom())) {
            if (indexIsCorrect(player)) {
                this.player = player;
                usedTreasure = player.getInventory().getTreasures().get(index);
                usedTreasure.useEffect(player);
                player.getInventory().remove(index);
            }
        }
    }

    @Override
    public boolean isPossible(Room room) {
        return true;
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        if (usedTreasure != null && player != null) {
            descriptionBuilder.useTreasureDescription(player, usedTreasure);
        } else {
            descriptionBuilder.cantUseTreasure();
        }
    }

    private boolean indexIsCorrect(Player player) {
        return index < player.getInventory().size();
    }
}
