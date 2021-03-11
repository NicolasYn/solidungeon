package model.treasure;

import model.character.Player;

public class SwordWeapon extends Weapon {

    public SwordWeapon(double quality) {
        super(quality);
    }

    @Override
    public void useEffect(Player player) {
        player.setStrength(player.getStrength() + quality);
    }

    @Override
    public String getType() {
        return "Sword";
    }
}
