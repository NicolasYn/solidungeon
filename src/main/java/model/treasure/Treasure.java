package model.treasure;

import model.character.Player;

public abstract class Treasure {

    protected double quality;
    static final double MAX_QUALITY_TREASURE = 10;

    public Treasure(double quality) {
        this.quality = quality;
    }

    public String description() {
        return getType() + " with a power of " + quality;
    }

    public abstract void useEffect(Player player);

    public abstract String getType();

    @Override
    public String toString() {
        return description();
    }
}
