package main.java.com.andreagenovese.chess.Pieces;

import main.java.com.andreagenovese.chess.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public String toString() {
        return isWhite ? "P" : "p";
    }
}
