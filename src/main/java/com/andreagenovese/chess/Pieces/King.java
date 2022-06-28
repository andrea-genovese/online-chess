package main.java.com.andreagenovese.chess.Pieces;


import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;

public class King extends Piece{
    public King(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

    @Override
    public Set<Move> getMoves() {
        // TODO Auto-generated method stub
        return null;
    }
}
