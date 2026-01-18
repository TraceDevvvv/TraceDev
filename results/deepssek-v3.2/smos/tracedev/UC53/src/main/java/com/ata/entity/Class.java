package com.ata.entity;

/**
 * Represents a Class entity with properties as per the class diagram.
 * The registryKey provides access to the registry of each class.
 */
public class Class {
    private String classId;
    private String className;
    private String description;
    private String registryKey;

    public Class(String classId, String className, String description, String registryKey) {
        this.classId = classId;
        this.className = className;
        this.description = description;
        this.registryKey = registryKey;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public String getRegistryKey() {
        return registryKey;
    }
}