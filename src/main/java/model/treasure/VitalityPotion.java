package model.treasure;

import model.character.Player;

public class VitalityPotion extends Potion {

    public VitalityPotion(double quality) {
        super(quality);
    }

    @Override
    public void useEffect(Player player) {
        player.setVitality(player.getVitality() + quality);
    }

    @Override
    public String getType() {
        return "Vitality Potion";
    }
}
