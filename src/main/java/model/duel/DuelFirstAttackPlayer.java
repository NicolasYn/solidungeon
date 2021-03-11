package model.duel;

import model.character.Player;

public class DuelFirstAttackPlayer extends DuelAutomatic {

   public DuelFirstAttackPlayer(Player player) {
       super(player);
       setAttacker(player);
   }

}
