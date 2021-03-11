package model.duel;

import model.character.Player;
import model.character.playerstate.PlayerState;

public class ActionTurnBased {

    private Player player;

    public ActionTurnBased(Player player) {
        this.player = player;
    }

    public void doAction(PlayerState playerState) {
        player.changeState(playerState);
    }

    public Player getPlayer() {
        return player;
    }
}
