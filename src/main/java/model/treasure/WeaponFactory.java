package model.treasure;

public class WeaponFactory {

    public static Weapon createWeapon(String weaponType, double quality) {
        if (weaponType.equalsIgnoreCase("SWORD")) return new SwordWeapon(quality);
        else throw new IllegalArgumentException("Invalid weapon type");
    }
}
