package model.character;

import java.util.ArrayList;

public class DefaultInventory extends Inventory {

    private final int DEFAULT_CAPACITY = 10;

    public DefaultInventory() {
        this.capacity = DEFAULT_CAPACITY;
        this.treasures = new ArrayList<>();
    }
}
