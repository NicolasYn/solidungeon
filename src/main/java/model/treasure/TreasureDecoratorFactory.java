package model.treasure;

public class TreasureDecoratorFactory {

    public static Treasure createRandomTreasureDecorator() {
        return new SuperWeaponDecorator(new SwordWeapon(TreasureFactory.randomQuality()));
    }
}
