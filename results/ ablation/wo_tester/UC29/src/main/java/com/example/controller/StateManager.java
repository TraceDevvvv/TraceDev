package com.example.controller;

import com.example.model.SystemStateMemento;
import com.example.model.UIMemento;
import com.example.model.SearchMemento;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages system state using the Memento pattern.
 */
public class StateManager {
    private Object currentState; // Simplified system state
    private List<SystemStateMemento> mementos;

    public StateManager() {
        this.mementos = new ArrayList<>();
    }

    public SystemStateMemento saveState() {
        UIMemento uiMemento = new UIMemento();
        uiMemento.setActiveScreen("SearchScreen");
        mementos.add(uiMemento);
        return uiMemento;
    }

    public void restoreState(SystemStateMemento memento) {
        if (memento != null) {
            this.currentState = memento.getStateData();
        }
    }

    public SystemStateMemento getLastState() {
        if (mementos.isEmpty()) {
            return saveState(); // Create initial state if none exists
        }
        return mementos.get(mementos.size() - 1);
    }
}