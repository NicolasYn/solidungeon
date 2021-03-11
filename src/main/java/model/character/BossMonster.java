package model.character;

public class BossMonster extends Monster {

    private static final double STRENGTH_VALUE = 10;
    private static final double VITALITY_VALUE = 30;
    public static final String TYPE = "BOSS";

    public BossMonster() {
        super(STRENGTH_VALUE, VITALITY_VALUE);
    }

    @Override
    public String description() {
        return "Boss Monster with "+ strength + " strength and "+ vitality + " vitality";
    }
}
