package com.example.checkers;

public class MoveResult {
    private MoveType type;
    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public MoveType getType() {
        return type;
    }

    public void setType(MoveType type) {
        this.type = type;
    }

    public MoveResult(MoveType type) {
        this(type, null);
    }

    public MoveResult(MoveType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
}
