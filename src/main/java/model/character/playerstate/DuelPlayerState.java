package model.character.playerstate;

import model.character.Character;
import model.character.Player;
import model.description.DescriptionBuilder;
import model.room.Room;

public abstract class DuelPlayerState implements PlayerState {

    private Character attacker;
    private Character attacked;
    protected Player player;

    public DuelPlayerState(Character attacker, Character attacked) {
        this.attacker = attacker;
        this.attacked = attacked;
    }

    @Override
    public void doAction(Player player) {
        if (isPossible(player.getRoom())) {
            attack();
        }
    }

    private void attack() {
        double attackDamage = getAttackDamage();
        attacker.attack(attacked, attackDamage);
        descriptionAttackDuel(attackDamage);
    }

    @Override
    public boolean isPossible(Room room) {
        return room.containsMonster();
    }

    @Override
    public void description(DescriptionBuilder descriptionBuilder) {
        descriptionBuilder.duelDescription(attacker, attacked);
    }

    private void descriptionAttackDuel(double damage) {
        for (PlayerStateObserver playerStateObserver : player.getPlayerStateObservers()) {
            playerStateObserver.getDescriptionBuilder().descriptionAttackDuel(attacker, attacked, damage);
        }
    }

    private double getAttackDamage() {
        return attacker.getStrength();
    }

}
