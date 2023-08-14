package com.thekingoftime.game.domains.game.player;

import com.thekingoftime.game.domains.game.shared.Shared;
import com.thekingoftime.game.domains.game.shared.ports.ServicesInterface;

public class PlayerService implements ServicesInterface {

    @Override
    public void execute() {
        var stage = Shared.getInstance().getData().stage;

        PlayerActor playerActor = new PlayerActor();
        stage.addActor(playerActor);
    }
}
