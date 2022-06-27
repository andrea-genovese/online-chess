package main.java.com.andreagenovese.chess.Pieces;

import main.java.com.andreagenovese.chess.Piece;

public class King extends Piece{

    public King(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

}
