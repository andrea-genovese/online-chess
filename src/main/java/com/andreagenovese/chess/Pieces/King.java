package main.java.com.andreagenovese.chess.Pieces;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Piece;

public class King extends Piece{
    
    
    public King(boolean isWhite, ChessBoard board, byte row, byte column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

}
