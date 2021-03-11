package model.duel;

import model.character.Player;

public class DuelFirstAttackMonster extends DuelAutomatic {

    public DuelFirstAttackMonster(Player player) {
        super(player);
        setAttacked(player);
    }

}
