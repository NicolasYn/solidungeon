package model.character;

import java.util.Random;

public class DefaultMonster extends Monster {

    private static final double MIN_STRENGTH_VALUE = 1;
    private static final double MAX_STRENGTH_VALUE = 5;
    private static final double MIN_VITALITY_VALUE = 7;
    private static final double MAX_VITALITY_VALUE = 20;
    public static final String TYPE = "DEFAULT";

    public DefaultMonster() {
        super(generateStrengthValue(), generateVitalityValue());
    }

    private static double generateStrengthValue() {
        return new Random().nextInt((int) MAX_STRENGTH_VALUE) + MIN_STRENGTH_VALUE;
    }

    private static double generateVitalityValue() {
        return new Random().nextInt((int) (MAX_VITALITY_VALUE+1 - MIN_VITALITY_VALUE)) + MIN_VITALITY_VALUE;
    }

    @Override
    public String description() {
        return "Default Monster with "+ strength + " strength and "+ vitality + " vitality";
    }
}
