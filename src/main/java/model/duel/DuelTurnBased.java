package model.duel;

import model.character.Character;
import model.character.Player;
import model.character.playerstate.DiePlayerState;
import model.character.playerstate.FightPlayerState;
import model.character.playerstate.ReceiveAttackPlayerState;
import model.character.playerstate.UseTreasurePlayerState;

public class DuelTurnBased extends DuelStrategy {

    public DuelTurnBased(Player player, TurnBasedStrategy turnBasedStrategy) {
        super(player);
        setAttacker(player);
        this.turnBasedStrategy = turnBasedStrategy;
    }

    @Override
    public void battleSystem() {
        if (attacker.isAlive() && attacked.isAlive()) {
            action(attacker);
            if (!attacked.isAlive()) return;
            action(attacked);
        }
        else if (!isPlayerWinner()) player.changeState(new DiePlayerState());

    }

    private void action(Character attacker) {
        if (attacker.equals(player) && turnBasedStrategy.canDoAction()) {
            turnBasedStrategy.doActionOneRound();
        } else player.changeState(new ReceiveAttackPlayerState(attacker, player));
    }

    @Override
    public boolean isAutomatic() {
        return false;
    }

}
