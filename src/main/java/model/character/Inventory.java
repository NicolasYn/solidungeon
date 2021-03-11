package model.character;

import model.treasure.Treasure;

import java.util.List;

public abstract class Inventory {

    protected List<Treasure> treasures;
    protected int capacity;

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == capacity();
    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return treasures.size();
    }

    public void remove(int index) {
        treasures.remove(index);
    }

    public void add(Treasure treasure) {
        treasures.add(treasure);
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
