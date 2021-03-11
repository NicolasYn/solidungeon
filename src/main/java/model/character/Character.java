package model.character;

public abstract class Character {

    protected double strength;
    protected double vitality;

    public Character(double strength, double vitality) {
        this.strength = strength;
        this.vitality = vitality;
    }

    public boolean isAlive() {
        return vitality > 0;
    }

    public void attack(Character attacked, double attackDamage) {
        attacked.setVitality(attacked.getVitality() - attackDamage);
    }

    public abstract String getType();

    public double getStrength() {
        return strength;
    }

    public double getVitality() {
        return vitality;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setVitality(double vitality) {
        this.vitality = vitality;
    }

    @Override
    public String toString() {
        return getType() + "{strength=" + strength + ", vitality=" + vitality + "} " + hashCode();
    }
}
