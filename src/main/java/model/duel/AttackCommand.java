package model.duel;

import model.character.Character;
import model.character.playerstate.FightPlayerState;

public class AttackCommand implements ActionCommand {

    private ActionTurnBased actionTurnBased;
    private Character attacked;

    public AttackCommand(ActionTurnBased actionTurnBased, Character attacked) {
        this.actionTurnBased = actionTurnBased;
        this.attacked = attacked;
    }

    @Override
    public void execute() {
        actionTurnBased.doAction(new FightPlayerState(actionTurnBased.getPlayer(), attacked));
    }
}
