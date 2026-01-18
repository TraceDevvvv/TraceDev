package com.example.model;

import java.util.Map;

/**
 * Memento for storing UI state.
 */
public class UIMemento extends SystemStateMemento {
    private String activeScreen;
    private Map<String, String> inputValues;

    public UIMemento() {
        super();
        inputValues = (Map<String, String>) stateData.getOrDefault("inputValues", new HashMap<String, String>());
    }

    public String getActiveScreen() {
        return activeScreen;
    }

    public void setActiveScreen(String screen) {
        this.activeScreen = screen;
        stateData.put("activeScreen", screen);
    }

    public Map<String, String> getInputValues() {
        return inputValues;
    }

    public void setInputValues(Map<String, String> inputValues) {
        this.inputValues = inputValues;
        stateData.put("inputValues", inputValues);
    }
}