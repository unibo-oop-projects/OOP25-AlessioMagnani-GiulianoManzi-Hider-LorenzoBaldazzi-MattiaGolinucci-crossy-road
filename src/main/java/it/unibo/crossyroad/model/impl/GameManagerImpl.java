package it.unibo.crossyroad.model.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.crossyroad.model.api.ActiveChunk;
import it.unibo.crossyroad.model.api.ActiveObstacle;
import it.unibo.crossyroad.model.api.Chunk;
import it.unibo.crossyroad.model.api.Dimension;
import it.unibo.crossyroad.model.api.Direction;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameManager;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Obstacle;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.api.Positionable;

/**
 * Implementation of the GameManager interface.
 * @see GameManager
 */
public class GameManagerImpl implements GameManager {

    private final static Position PLAYER_START_POSITION = new Position(5, 0);
    private final static Position CHUNK_START_POSITION = new Position(0, 0);
    private final static int Y_MOVE_MAP_MARK = 4;
    private final static int Y_DISPOSE_CHUNK_MARK = 12;
    private final static int Y_CREATE_CHUNK_MARK = 2;
    private final static int Y_MAP_MOVEMENT = 1;
    private final static Dimension CHUNK_DIMENSION = new Dimension(10, 3);
    private final static int MAP_WIDTH = 10;
    private final static int MAP_HEIGHT = 3;
    private PositionablePlayer player;
    private final GameParameters gameParameters;
    private List<Chunk> chunks;

    /**
     * Initializes the GameManager with the GameParameters.
     * 
     * @param g the GameParameters to use in the game.
     */
    public GameManagerImpl(GameParameters g) {
        this.gameParameters = g;
        this.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Positionable> getPositionables() {
        final List<Positionable> positionables = new LinkedList<>();
        positionables.addAll(this.getObstaclesOnMap());

        return List.copyOf(positionables);   //TODO update when i get Pickables.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<EntityType, Long> getActivePowerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActivePowerUps'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long deltaTime) {
        this.chunks.stream()
                   .filter(c -> c instanceof ActiveChunk)
                   .map(c -> (ActiveChunk)c)
                   .forEach(ac -> ac.update(this.gameParameters, deltaTime));

        // this.checkCoinsCollisions();
        // this.checkPowerUpCollisions(); //TODO when I have the objects.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(Direction d) {
        if (this.canPlayerMove(d)) {
            this.player.move(d);

            if (this.player.getPosition().y() >= Y_MOVE_MAP_MARK) {
                this.moveMap();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.checkDeadlyCollisions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.player = new PositionablePlayer(PLAYER_START_POSITION);
        this.chunks = new LinkedList<>();

        //Adds the first chunks to start the game
        this.chunks.add(new Grass(CHUNK_START_POSITION, CHUNK_DIMENSION));
        this.chunks.add(new Grass(new Position(3, 0), CHUNK_DIMENSION));
        this.chunks.add(new Grass(new Position(6, 0), CHUNK_DIMENSION));
        this.chunks.add(new Grass(new Position(9, 0), CHUNK_DIMENSION));
    }

    /**
     * Generates a new Chunk.
     */
    private void generateChunk() {
        final Random rnd = new Random();

        //TODO add new Chunks when ready.
        switch (rnd.nextInt(3)) {
            case 0:
                this.chunks.add(new Grass(CHUNK_START_POSITION, CHUNK_DIMENSION));
                break;
            // case 1:
            //     this.chunks.add(new Road(CHUNK_START_POSITION, CHUNK_DIMENSION));
            //     break;
            // case 2:
            //     this.chunks.add(new Railway(CHUNK_START_POSITION, CHUNK_DIMENSION));
            //     break;
        }
    }

    /**
     * Wheter the player can move in the given direction.
     * 
     * @param d the direction the player wants to move upon.
     * 
     * @return true if the player can move, false otherwise.
     */
    private boolean canPlayerMove(Direction d) {
        //Checks Passive obstacles collisions
        for (Obstacle obs : this.getObstaclesOnMap()) {
            if ((obs instanceof Rock || obs instanceof Tree) && d.apply(this.player.getPosition()) == obs.getPosition()) {
                return false;
            }
        }

        //Checks map border collisions
        if (d.apply(this.player.getPosition()).y() > MAP_WIDTH || d.apply(this.player.getPosition()).x() > MAP_HEIGHT) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the player is colliding with an active obstacle.
     * 
     * @return true if there's a collision, false otherwise.
     */
    private boolean checkDeadlyCollisions() {
        for (Obstacle obs : this.getObstaclesOnMap()) {
            if (obs instanceof ActiveObstacle && obs.getPosition() == this.player.getPosition()) {
                return true;
            }
        }

        return false;
    }

    //TODO when I have the object
    // private int checkCoinsCollision() {
        
    // }

    //TODO when I have the object
    // private PowerUp checkPowerUpCollisions() {

    // }

    /**
     * Gets the Obstacles currently present on the map.
     * 
     * @return a List of the Obstacles currently present on the map.
     */
    private List<Obstacle> getObstaclesOnMap() {
        final List<Obstacle> obstacles = new LinkedList<>();

        for (Chunk c : this.chunks) {
            obstacles.addAll(c.getObstacles());
        }

        return obstacles;
    }

    /**
     * Handles the map movement.
     */
    private void moveMap() {
        //Chunk movement
        this.chunks.forEach(c -> c.increaseY(Y_MAP_MOVEMENT));
        this.chunks.removeIf(c -> c.getPosition().y() >= Y_DISPOSE_CHUNK_MARK);

        //Elements movement
        for (Chunk c : this.chunks) {
            for (Obstacle o : c.getObstacles()) {
                o.increaseY(Y_MAP_MOVEMENT); //TODO update when i get Pickables.
            }
        }

        //Generate new Chunk if necessary
        if (this.chunks.stream().anyMatch(c -> c.getPosition().y() == Y_CREATE_CHUNK_MARK)) {
            this.generateChunk();
        }
    }
}
