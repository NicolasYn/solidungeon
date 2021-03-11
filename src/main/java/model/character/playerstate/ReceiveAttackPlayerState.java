package model.character.playerstate;

import model.character.Character;
import model.character.Player;

public class ReceiveAttackPlayerState extends DuelPlayerState implements PlayerState {

    public ReceiveAttackPlayerState(Character attacker, Player attacked) {
        super(attacker, attacked);
        this.player = attacked;
    }
}
