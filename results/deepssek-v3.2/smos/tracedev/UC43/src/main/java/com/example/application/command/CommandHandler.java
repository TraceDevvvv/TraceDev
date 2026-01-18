package com.example.application.command;

public interface CommandHandler<T> {
    void handle(T command);
}