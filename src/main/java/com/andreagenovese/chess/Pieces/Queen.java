package main.java.com.andreagenovese.chess.Pieces;

import main.java.com.andreagenovese.chess.Piece;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public String toString() {
        return isWhite ? "Q" : "q";
    }
}
