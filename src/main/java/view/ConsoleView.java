package view;

import model.character.Inventory;
import model.character.Player;
import model.room.Room;
import model.room.RoomManager;

import java.util.Arrays;

public class ConsoleView {

    public void printMap(Room[][] map) {
        System.out.println(Arrays.deepToString(map).replace("], ", "\n")
                .replace("[",  "").replace("null", "0")
                .replace("]]", "").replace(",", "|"));
    }

    public void printVisited(boolean[][] visited) {
        System.out.println(Arrays.deepToString(visited)
                .replace("], ", "]\n----------------------------\n")
                .replace("[[", "[").replace("]]", "]")
                .replace("[", " | ").replace("]", " | ")
                .replace(",", " | ").replace("false", "ø")
                .replace("true", "*"));
    }

    public void printPlayerPosition(RoomManager roomManager, Player player) {
        System.out.println("You are in <" +roomManager.getCoordinates(player.getRoom()).toString()
                .replace("=", ",") + ">");
    }

    public void printPlayerAttributes(Player player) {
        System.out.println(player.getType() + " with " + player.getStrength() + " strength and " +
                player.getVitality() + " vitality");
    }

    public void print(String message) {
        System.out.println(message + "\n");
    }

    public void printInventory(Inventory inventory) {
        System.out.println("Inventory :");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("    Item n°"+i + " : " + inventory.getTreasures().get(i));
        }
    }

    public void printDefeat() {
        System.out.println("Defeat");
    }

    public void printVictory() {
        System.out.println("Win");
    }

}
