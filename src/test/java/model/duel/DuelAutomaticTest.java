package model.duel;

import model.character.DefaultPlayer;
import model.character.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DuelAutomaticTest {

    @Test
    public void equalsCharacter() {
        Player player = new DefaultPlayer(10, 10);
        DuelStrategy duelStrategy = new DuelFirstAttackPlayer(player);
        assertEquals(player, duelStrategy.attacker);
        assertEquals(duelStrategy.attacker, player);
        assertNotEquals(duelStrategy.attacked, player);
    }

}