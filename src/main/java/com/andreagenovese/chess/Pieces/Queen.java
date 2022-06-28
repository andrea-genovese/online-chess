package main.java.com.andreagenovese.chess.Pieces;

import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;

public class Queen extends Piece {

    public Queen(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "Q" : "q";
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = getBishopMoves();
        moves.addAll(getRookMoves());
        return moves;
    }
}
