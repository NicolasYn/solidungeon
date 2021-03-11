package model.duel;

import java.util.ArrayList;
import java.util.List;

public abstract class TurnBasedStrategy {

    protected List<ActionCommand> actionCommandList;
    public int actionPerRound;
    protected int currentActionPerRound;

    public TurnBasedStrategy() {
        this.actionCommandList = new ArrayList<>();
    }

    public void addAction(ActionCommand actionCommand) {
        actionCommandList.add(actionCommand);
    }

    public boolean canDoAction() {
        return currentActionPerRound > 0;
    }

    public void doActionOneRound() {
        for (int i = 0; i < actionPerRound; i++) {
            actionCommandList.get(i).execute();
        }
        actionCommandList.clear();
    }

}
