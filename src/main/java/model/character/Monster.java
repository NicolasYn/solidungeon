package model.character;

public abstract class Monster extends Character {

    protected double strength;
    protected double vitality;

    public Monster(double strength, double vitality) {
        super(strength, vitality);
        this.strength = strength;
        this.vitality = vitality;
    }
    
    @Override
    public String getType() {
        return "Monster";
    }

    public abstract String description();

}
