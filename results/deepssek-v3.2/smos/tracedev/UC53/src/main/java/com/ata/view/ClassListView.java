package com.ata.view;

import com.ata.entity.Class;
import java.util.List;
import java.util.stream.Collectors;

/**
 * View model for class list display. Contains classes and provides access to registry keys.
 */
public class ClassListView {
    private List<Class> classes;

    public ClassListView(List<Class> classes) {
        this.classes = classes;
    }

    /**
     * Returns the list of classes.
     */
    public List<Class> getClasses() {
        return classes;
    }

    /**
     * Returns a list of registry keys for all classes.
     * Satisfies requirement: "system provides a key to access the registry of each class".
     */
    public List<String> getClassKeys() {
        return classes.stream()
                      .map(Class::getRegistryKey)
                      .collect(Collectors.toList());
    }
}