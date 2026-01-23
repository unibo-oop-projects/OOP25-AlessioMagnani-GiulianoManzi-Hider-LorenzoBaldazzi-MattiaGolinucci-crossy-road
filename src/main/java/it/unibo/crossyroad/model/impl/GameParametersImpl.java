package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.GameParameters;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the GameParameters interface.
 */
public class GameParametersImpl implements GameParameters {
    private int coinMultiplier;
    private double carSpeedMultiplier;
    private double trainSpeedMultiplier;
    private boolean invincibility;
    private int coinCount;

    /**
     * Constructor to initialize game parameters using {@link GameParametersBuilder} to create instance.
     *
     * @param coinMultiplier the coin multiplier.
     * @param carSpeedMultiplier the car speed multiplier.
     * @param trainSpeedMultiplier the train speed multiplier.
     * @param invincibility the invincibility status.
     * @param coinCount the initial coin count.
     */
    public GameParametersImpl(final int coinMultiplier, final double carSpeedMultiplier,
                              final double trainSpeedMultiplier, final boolean invincibility,
                              final int coinCount) {
        this.coinMultiplier = coinMultiplier;
        this.carSpeedMultiplier = carSpeedMultiplier;
        this.trainSpeedMultiplier = trainSpeedMultiplier;
        this.invincibility = invincibility;
        this.coinCount = coinCount;
    }

    /**
     * Default constructor initializing parameters to default values.
     */
    public GameParametersImpl() {
        this(1, 1.0, 1.0, false, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoinMultiplier(final int coinMultiplier) {
        this.coinMultiplier = coinMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarSpeedMultiplier(final double carSpeedMultiplier) {
        this.carSpeedMultiplier = carSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrainSpeedMultiplier(final double trainSpeedMultiplier) {
        this.trainSpeedMultiplier = trainSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInvincibility(final boolean invincibility) {
        this.invincibility = invincibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoinMultiplier() {
        return this.coinMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCarSpeedMultiplier() {
        return this.carSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTrainSpeedMultiplier() {
        return this.trainSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInvincible() {
        return this.invincibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoinCount() {
        return this.coinCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementCoinCount() {
        this.coinCount += this.coinMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameParametersImpl loadFromFile(final String filepath) {
        try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
            return parse(fileInputStream);
        } catch (final IOException e) {
            System.err.println("Failed to load game parameters from file: " + e.getMessage()); //NOPMD
            return null;
        }
    }

    /**
     * Parses game parameters from an InputStream containing key=value pairs.
     * Each line in the stream should have the format "key = value", where key is one of the parameter names.
     *
     * @param input the input stream.
     * @return a GameParametersImpl instance with parsed parameters.
     */
    private GameParametersImpl parse(final InputStream input) {
        final GameParametersBuilder builder = new GameParametersBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(input), StandardCharsets.UTF_8))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    continue;
                }
                final String[] elements = line.split("=");
                if (elements.length != 2) {
                    continue;
                }
                final String key = elements[0];
                final String value = elements[1];
                try {
                    switch (key) {
                        case "coinMultiplier" -> builder.setCoinMultiplier(Integer.parseInt(value));
                        case "carSpeedMultiplier" -> builder.setCarSpeedMultiplier(Double.parseDouble(value));
                        case "trainSpeedMultiplier" -> builder.setTrainSpeedMultiplier(Double.parseDouble(value));
                        case "invincibility" -> builder.setInvincibility(Boolean.parseBoolean(value));
                        case "coinCount" -> builder.setCoinCount(Integer.parseInt(value));
                        default -> System.err.println("Unknown key: " + key); //NOPMD
                    }
                } catch (final NumberFormatException e) {
                    System.err.println("Invalid value for key " + key + ": " + value); //NOPMD
                }
                line = bufferedReader.readLine();
            }
            return builder.build();
        } catch (final IOException e) {
            System.err.println("Failed to parse game parameters: " + e.getMessage()); //NOPMD
            return null;
        }
    }
}
