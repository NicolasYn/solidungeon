package model.character.playerstate;

import model.character.Character;
import model.character.Player;

public class FightPlayerState extends DuelPlayerState implements PlayerState {

    public FightPlayerState(Player attacker, Character attacked) {
        super(attacker, attacked);
        this.player = attacker;
    }
}
