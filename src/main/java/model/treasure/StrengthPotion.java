package model.treasure;

import model.character.Player;

public class StrengthPotion extends Potion {

    public StrengthPotion(double quality) {
        super(quality);
    }

    @Override
    public void useEffect(Player player) {
        player.setStrength(player.getStrength() + quality);
    }

    @Override
    public String getType() {
        return "Strength Potion";
    }
}
