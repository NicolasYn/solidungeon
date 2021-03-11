import controller.KeyBoardController;
import model.Dungeon;
import model.character.DefaultPlayer;
import model.character.Player;
import model.character.playerstate.BasicPlayerStateObserver;
import model.character.playerstate.PlayerStateObserver;
import model.description.DefaultDescriptionBuilder;
import model.duel.DuelFirstAttackPlayer;
import model.duel.DuelStrategy;
import model.duel.DuelTurnBased;
import model.duel.OneActionPerRound;
import model.room.BasicRoomChangeObserver;
import model.room.RoomChangeObserver;
import model.room.RoomManager;
import view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        Player player = new DefaultPlayer(10, 30);
        PlayerStateObserver playerStateObserver = new BasicPlayerStateObserver(player, new DefaultDescriptionBuilder());
        RoomManager roomManager = new RoomManager(5);
        DuelStrategy duelStrategy = new DuelTurnBased(player, new OneActionPerRound());
        RoomChangeObserver basicRoomChangeObserver = new BasicRoomChangeObserver(roomManager, player, duelStrategy);
        Dungeon dungeon = new Dungeon(roomManager, player, basicRoomChangeObserver, playerStateObserver, duelStrategy);
        KeyBoardController keyBoardController = new KeyBoardController(dungeon, new ConsoleView());
    }

}
