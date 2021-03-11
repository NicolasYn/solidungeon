package model.duel;

import model.character.Player;

public class DuelFirstAttackRandom extends DuelAutomatic {

    public DuelFirstAttackRandom(Player player) {
        super(player);
        if (Math.random() < 0.5) playerIsAttacker();
        else playerIsAttacked();
    }

    private void playerIsAttacker() {
       setAttacker(player);
    }

    private void playerIsAttacked() {
        setAttacked(player);
    }

}
