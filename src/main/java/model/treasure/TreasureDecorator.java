package model.treasure;

import model.character.Player;

public abstract class TreasureDecorator extends Treasure {

    protected Treasure treasure;

    public TreasureDecorator(Treasure treasure) {
        super(treasure.quality);
        this.treasure = treasure;
    }

    @Override
    public void useEffect(Player player) {
        treasure.useEffect(player);
    }

    @Override
    public String getType() {
        return treasure.getType();
    }
}
