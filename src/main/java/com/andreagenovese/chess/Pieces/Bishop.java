package main.java.com.andreagenovese.chess.Pieces;

import main.java.com.andreagenovese.chess.Piece;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }
    @Override
    public String toString() {
        return isWhite ? "B" : "b";
    }
        
}
