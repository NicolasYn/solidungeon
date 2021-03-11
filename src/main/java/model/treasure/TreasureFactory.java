package model.treasure;

import java.util.Random;

public class TreasureFactory {

    public static Treasure createRandomTreasure() {
        return Math.random() < 0.5 ?
                WeaponFactory.createWeapon(randomEnum(WeaponEnum.class), randomQuality())
                : PotionFactory.createPotion(randomEnum(PotionEnum.class), randomQuality());
    }

    public static String randomEnum(Class<? extends Enum<?>> enumeration) {
        return enumeration.getEnumConstants()[new Random().nextInt(enumeration.getEnumConstants().length)].toString();
    }

    static int randomQuality() {
        return new Random().nextInt((int) (Treasure.MAX_QUALITY_TREASURE))+1;
    }

}
