package model.character;

public class DefaultPlayer extends Player {

    public DefaultPlayer(double strength, double vitality) {
        super(strength, vitality);
    }

    @Override
    public String description() {
        return "Default Player Level 0";
    }

    @Override
    public void doAction() {
        state.doAction(this);
    }
}
