package com.example.model;

/**
 * Result of a position retrieval attempt.
 */
public class PositionResult {
    private PositionResultType resultType;
    private Position position;
    private String errorMessage;

    public PositionResult(PositionResultType resultType, Position position, String errorMessage) {
        this.resultType = resultType;
        this.position = position;
        this.errorMessage = errorMessage;
    }

    public PositionResult(PositionResultType resultType, Position position) {
        this(resultType, position, null);
    }

    public PositionResult(PositionResultType resultType) {
        this(resultType, null, null);
    }

    public boolean isSuccess() {
        return resultType == PositionResultType.SUCCESS;
    }

    public PositionResultType getResultType() {
        return resultType;
    }

    public Position getPosition() {
        return position;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}