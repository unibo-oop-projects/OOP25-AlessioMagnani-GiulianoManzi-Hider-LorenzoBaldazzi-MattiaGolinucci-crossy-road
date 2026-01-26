package it.unibo.crossyroad.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.crossyroad.model.api.Direction;
import it.unibo.crossyroad.model.api.GameManager;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.impl.GameManagerImpl;
import it.unibo.crossyroad.model.impl.GameParametersBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameManagerTest {
    private GameManager gameManager;
    private GameParameters gameParameters;


    @BeforeEach
    void setup() {
        this.gameParameters = new GameParametersBuilder()
                              .setCoinMultiplier(1)
                              .setCarSpeedMultiplier(1)
                              .setTrainSpeedMultiplier(1)
                              .setInvincibility(false)
                              .setCoinCount(0)
                              .build();
        this.gameManager = new GameManagerImpl(this.gameParameters);
    }

    @Test
    void testPlayerMovement() {
        for (int i = 0; i < 5; i++) {
            this.gameManager.movePlayer(Direction.UP);
        }
        assertFalse(this.gameManager.isGameOver());
    }
}
