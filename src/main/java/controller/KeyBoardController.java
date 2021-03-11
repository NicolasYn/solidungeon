package controller;

import model.Dungeon;
import model.character.Monster;
import model.character.Player;
import model.character.playerstate.*;
import model.duel.*;
import model.room.FinalRoom;
import view.ConsoleView;

import java.util.Scanner;

public class KeyBoardController {

    private Dungeon dungeon;
    private ConsoleView view;

    public KeyBoardController(Dungeon dungeon, ConsoleView view) {
        this.dungeon = dungeon;
        this.view = view;
        read();
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        Player player = dungeon.getPlayer();
        dungeon.getPlayerStateObserver().getDescriptionBuilder().dungeonDescription(dungeon);
        printAndResetDescription();
        while (true) {
            displayBasicInformation(player);
            String key = scanner.next();
            switch (key) {
                case "z": case "s": case "d": case "q":
                    goDirection(player, getDirectionFromKey(key));
                    break;
                case "p":
                    pickUpTreasure(player);
                    break;
                case "u":
                    useTreasure(player);
                    break;
                case "l":
                    lookPlayer(player);
                    break;
                case "e":
                    view.print("Are you sure ? (o/n)");
                    if (new Scanner(System.in).nextLine().equalsIgnoreCase("o")) return;
                    else break;
                default:
                    defaultPlayerState(player);
                    break;
            }
            if (isPlayerInDuelTurnBased(player)) {
                duelTurnBased(player);
            }
            if (isPlayerDead(player)) break;
            if (hasWin(player)) break;
            printAndResetDescription();
        }

    }

    private boolean hasWin(Player player) {
        if (player.getRoom().getType().equals(FinalRoom.ROOM_TYPE) && !player.getRoom().containsMonster()) {
            view.printVictory();
            return true;
        }
        return false;
    }

    private boolean isPlayerDead(Player player) {
        if (player.getState().getClass().equals(DiePlayerState.class)) {
            view.printDefeat();
            return true;
        }
        return false;
    }

    private void duelTurnBased(Player player) {
        while (player.getRoom().containsMonster()) {
            oneRoundDuelTurnBased(player);
            printAndResetDescription();
            if (!player.isAlive()) {
                player.changeState(new DiePlayerState());
                break;
            }
        }
    }

    private void oneRoundDuelTurnBased(Player player) {
        TurnBasedStrategy turnBasedStrategy = dungeon.getDuelStrategy().getTurnBasedStrategy();
        ActionTurnBased actionTurnBased = new ActionTurnBased(player);
        for (int i = 0; i < turnBasedStrategy.actionPerRound; i++) {
            view.print("Enter your action (u : use treasure, a : attack)");
            String action = new Scanner(System.in).next();
            switch (action) {
                case "u":
                    view.print("Enter index treasure");
                    view.printInventory(player.getInventory());
                    int index = new Scanner(System.in).nextInt();
                    if (index < 0 || index > player.getInventory().size()) break;
                    view.printPlayerAttributes(player);
                    turnBasedStrategy.addAction(new UseTreasureCommand(actionTurnBased, index));
                    break;
                case "a": default:
                    turnBasedStrategy.addAction(new AttackCommand(actionTurnBased, player.getRoom().getMonsters().get(0)));
                    break;
            }
        }
        dungeon.getDuelStrategy().battleSystem();
    }

    private boolean isPlayerInDuelTurnBased(Player player) {
        return !dungeon.getDuelStrategy().isAutomatic() && player.getRoom().containsMonster();
    }

    private void defaultPlayerState(Player player) {
        player.changeState(new DefaultPlayerState());
    }

    private void pickUpTreasure(Player player) {
        player.changeState(new PickUpTreasurePlayerState());
    }

    private void lookPlayer(Player player) {
        view.print("Enter a direction (z,s,d,q)");
        player.changeState(new LookPlayerState(getDirectionFromKey(new Scanner(System.in).nextLine())));
    }

    private void useTreasure(Player player) {
        view.print("Enter index treasure");
        view.printInventory(player.getInventory());
        view.printPlayerAttributes(player);
        player.changeState(new UseTreasurePlayerState(new Scanner(System.in).nextInt()));
    }

    private void goDirection(Player player, Direction direction) {
        player.changeState(new MovePlayerState(direction));
    }

    private void displayBasicInformation(Player player) {
        //view.printMap(dungeon.getRoomManager().getMap());
        view.printVisited(dungeon.getRoomManager().getVisited());
        view.printPlayerPosition(dungeon.getRoomManager(), player);
        view.printPlayerAttributes(player);
        view.printInventory(player.getInventory());
        view.print("Enter your move");
    }

    private void printAndResetDescription() {
        view.print(dungeon.getPlayerStateObserver().getDescription());
        dungeon.getPlayerStateObserver().getDescriptionBuilder().resetDescription();
    }

    private Direction getDirectionFromKey(String key) {
        switch (key) {
            case "z" :
                return Direction.NORTH;
            case "s" :
                return Direction.SOUTH;
            case "d" :
                return Direction.EAST;
            case "q" :
                return Direction.WEST;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

}
