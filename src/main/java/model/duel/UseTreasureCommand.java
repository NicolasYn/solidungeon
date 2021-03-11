package model.duel;

import model.character.playerstate.UseTreasurePlayerState;

public class UseTreasureCommand implements ActionCommand {

    private ActionTurnBased actionTurnBased;
    private int index;

    public UseTreasureCommand(ActionTurnBased actionTurnBased, int index) {
        this.actionTurnBased = actionTurnBased;
        this.index = index;
    }

    @Override
    public void execute() {
        actionTurnBased.doAction(new UseTreasurePlayerState(index));
    }
}
