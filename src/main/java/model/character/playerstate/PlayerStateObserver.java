package model.character.playerstate;

import model.description.DescriptionBuilder;

public interface PlayerStateObserver {

    void update(PlayerState state);
    String getDescription();
    DescriptionBuilder getDescriptionBuilder();
}
