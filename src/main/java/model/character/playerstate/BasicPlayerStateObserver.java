package model.character.playerstate;

import model.character.Player;
import model.description.DescriptionBuilder;

public class BasicPlayerStateObserver implements PlayerStateObserver {

    private String description;
    private DescriptionBuilder descriptionBuilder;

    public BasicPlayerStateObserver(Player player, DescriptionBuilder descriptionBuilder) {
        description = "";
        this.descriptionBuilder = descriptionBuilder;
        player.addPlayerStateObservers(this);
    }

    @Override
    public void update(PlayerState state) {
        state.description(descriptionBuilder);
    }

    @Override
    public String getDescription() {
        return descriptionBuilder.getDescription();
    }

    public DescriptionBuilder getDescriptionBuilder() {
        return descriptionBuilder;
    }
}
