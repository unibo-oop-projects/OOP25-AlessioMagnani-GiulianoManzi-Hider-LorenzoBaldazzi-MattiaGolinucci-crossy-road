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
     * Before using this constructor, ensure that the parameters are validated with the apposite function.
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
        validateParameters(coinMultiplier, carSpeedMultiplier, trainSpeedMultiplier, coinCount);
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
        if (coinMultiplier < 1) {
            throw new IllegalArgumentException("Coin multiplier must be >= 1");
        }
        this.coinMultiplier = coinMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarSpeedMultiplier(final double carSpeedMultiplier) {
        if (carSpeedMultiplier <= 0.0) {
            throw new IllegalArgumentException("Car speed multiplier must be > 0.0");
        }
        this.carSpeedMultiplier = carSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrainSpeedMultiplier(final double trainSpeedMultiplier) {
        if (trainSpeedMultiplier <= 0.0) {
            throw new IllegalArgumentException("Train speed multiplier must be > 0.0");
        }
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
    public void setCoinCount(final int coinCount) {
        if (coinCount < 0) {
            throw new IllegalArgumentException("Coin count must be >= 0");
        }
        this.coinCount = coinCount;
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
    public GameParametersImpl loadFromFile(final String filepath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
            return parse(fileInputStream);
        } catch (final IOException e) {
            throw new IOException("Error loading game parameters from file: " + filepath, e);
        }
    }

    /**
     * Parses game parameters from an InputStream containing key=value pairs.
     * Each line in the stream should have the format "key=value", where key is one of the parameter names.
     * If a parameter is missing, its default value is used.
     * If the key is unknown, an IllegalArgumentException is thrown.
     * If a line is malformed or contains an invalid value, an IllegalArgumentException is thrown.
     *
     * @param input the input stream.
     * @return a GameParametersImpl instance with loaded parameters.
     * @throws IOException if an I/O error occurs.
     */
    private GameParametersImpl parse(final InputStream input) throws IOException {
        final GameParametersBuilder builder = new GameParametersBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(input), StandardCharsets.UTF_8))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                final String[] elements = line.split("=");
                if (line.isEmpty() || elements.length != 2) {
                    continue;
                }
                final String key = elements[0].trim();
                final String value = elements[1].trim();
                try {
                    switch (key) {
                        case "coinMultiplier" -> builder.setCoinMultiplier(Integer.parseInt(value));
                        case "carSpeedMultiplier" -> builder.setCarSpeedMultiplier(Double.parseDouble(value));
                        case "trainSpeedMultiplier" -> builder.setTrainSpeedMultiplier(Double.parseDouble(value));
                        case "invincibility" -> builder.setInvincibility(Boolean.parseBoolean(value));
                        case "coinCount" -> builder.setCoinCount(Integer.parseInt(value));
                        default -> throw new IllegalArgumentException("Unknown parameter key: " + key);
                    }
                } catch (final NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid format for key " + key + ": " + value, e);
                }
                line = bufferedReader.readLine();
            }
            return builder.build();
        } catch (final IOException e) {
            throw new IOException("Error reading from input stream", e);
        }
    }

    /**
     * Validates the game parameters.
     *
     * @param coinMult the coin multiplier.
     * @param carSpeedMult the car speed multiplier.
     * @param trainSpeedMult the train speed multiplier.
     * @param coinStart the coin start count.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    private void validateParameters(final int coinMult, final double carSpeedMult,
                                       final double trainSpeedMult, final int coinStart) {
        if (coinMult < 1) {
            throw new IllegalArgumentException("Coin multiplier must be >= 1");
        }
        if (carSpeedMult <= 0.0 || trainSpeedMult <= 0.0) {
            throw new IllegalArgumentException("Speed multipliers must be > 0.0");
        }
        if (coinStart < 0) {
            throw new IllegalArgumentException("Coin count must be >= 0");
        }
    }
}
