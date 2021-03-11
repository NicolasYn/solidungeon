package model.duel;

public class OneActionPerRound extends TurnBasedStrategy {

    private final int ACTION_PER_ROUND_NUMBER = 1;

    public OneActionPerRound() {
        this.actionPerRound = ACTION_PER_ROUND_NUMBER;
        this.currentActionPerRound = actionPerRound;
    }
}
