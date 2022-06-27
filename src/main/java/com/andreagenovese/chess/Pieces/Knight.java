package main.java.com.andreagenovese.chess.Pieces;


import main.java.com.andreagenovese.chess.Piece;

public class Knight extends Piece{

    public Knight(boolean isWhite) {
        super(isWhite);
    }
    @Override
    public String toString() {
        return isWhite ? "N" : "n";
    }
}
