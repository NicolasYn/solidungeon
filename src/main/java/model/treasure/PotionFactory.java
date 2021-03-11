package model.treasure;

public class PotionFactory {

    public static Potion createPotion(String potionType, double quality) {
        if (potionType.equalsIgnoreCase("VITALITY")) return new VitalityPotion(quality);
        else if (potionType.equalsIgnoreCase("STRENGTH")) return new StrengthPotion(quality);
        else throw new IllegalArgumentException("Invalid potion type");
    }

}
