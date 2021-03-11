package model.duel;

import model.character.Character;
import model.character.Player;
import model.character.playerstate.FightPlayerState;
import model.character.playerstate.ReceiveAttackPlayerState;

public abstract class DuelAutomatic extends DuelStrategy {

    public DuelAutomatic(Player player) {
        super(player);
    }

    public void battleSystem() {
        while (attacker.isAlive() && attacked.isAlive()) {
            attack(attacker, attacked);
            if (!attacked.isAlive()) break;
            attack(attacked, attacker);
        }
    }

    private void attack(Character attacker, Character attacked) {
        if (attacker.equals(player)) player.changeState(new FightPlayerState(player, attacked));
        else player.changeState(new ReceiveAttackPlayerState(attacker, player));
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }
}
