package main.java.com.andreagenovese.chess.Pieces;

import java.util.List;
import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;

public class Rook extends Piece {

    
    public Rook(boolean isWhite, ChessBoard board, byte row, byte column) {
        super(isWhite, board, row, column);
    }
    public Set<Move> getMoves(){
        return getRookMoves();
    }
    @Override
    public String toString() {
        return isWhite ? "R" : "r";
    }
}
