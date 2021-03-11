package model.treasure;

import model.character.Player;

public class SuperWeaponDecorator extends TreasureDecorator {

    public static final double COEFFICIENT_ADD_VITALITY = 0.5;

    public SuperWeaponDecorator(Treasure treasure) {
        super(treasure);
    }

    @Override
    public void useEffect(Player player) {
        treasure.useEffect(player);
        player.setVitality(player.getVitality() + player.getVitality() * COEFFICIENT_ADD_VITALITY);
    }

    @Override
    public String getType() {
        return "SuperWeapon";
    }
}
