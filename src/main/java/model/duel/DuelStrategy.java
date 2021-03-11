package model.duel;

import model.character.Character;
import model.character.Player;
import model.character.playerstate.DiePlayerState;

public abstract class DuelStrategy {

    protected Character attacker;
    protected Character attacked;
    protected Player player;
    protected TurnBasedStrategy turnBasedStrategy;

    public DuelStrategy(Player player) {
        this.player = player;
    }

    public void setAttacker(Character attacker) {
        this.attacker = attacker;
    }

    public void setAttacked(Character attacked) {
        this.attacked = attacked;
    }

    public void battle(Character opponent) {
        if (attacker == null) setAttacker(opponent);
        else setAttacked(opponent);
        if (isAutomatic()) {
            battleSystem();
            if (!isPlayerWinner()) player.changeState(new DiePlayerState());
        }
    }

    public abstract void battleSystem();

    public abstract boolean isAutomatic();

    public boolean isPlayerWinner() {
        return player.isAlive();
    }

    public TurnBasedStrategy getTurnBasedStrategy() {
        return turnBasedStrategy;
    }
}
