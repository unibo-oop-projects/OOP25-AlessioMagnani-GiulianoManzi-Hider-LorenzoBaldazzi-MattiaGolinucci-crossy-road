package it.unibo.crossyroad.controller.impl;

import it.unibo.crossyroad.controller.api.AppController;
import it.unibo.crossyroad.controller.api.MenuController;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Implementation of MenuController.
 *
 * @see MenuController
 */
public class MenuControllerImpl implements MenuController {

    private final AppController appController;
    private final StateManager stateManager;
    private final MenuView menuView;

    /**
     * Constructor.
     *
     * @param appController the application controller.
     * @param s the state manager.
     */
    public MenuControllerImpl(AppController appController, StateManager s) {
        this.appController = appController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void addView(MenuView v) {
        if (Objects.nonNull(v)) {
            this.menuView = v;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        this.menuView.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideMenu() {
        this.menuView.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGame() {
        this.appController.showGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Path p) {
        this.stateManager.save(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(Path p) {
        this.stateManager.load(p);
    }
}
