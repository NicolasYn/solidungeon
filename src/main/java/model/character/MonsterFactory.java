package model.character;

import model.treasure.TreasureFactory;

public class MonsterFactory {

    public static Monster createMonster(String monsterType) {
        if(monsterType.equalsIgnoreCase("DEFAULT")) return new DefaultMonster();
        else if(monsterType.equalsIgnoreCase("BOSS")) return new BossMonster();
        else throw new IllegalArgumentException("Invalid monster type");
    }

    public static Monster createRandomMonster() {
        return createMonster(TreasureFactory.randomEnum(MonsterEnum.class));
    }

}
