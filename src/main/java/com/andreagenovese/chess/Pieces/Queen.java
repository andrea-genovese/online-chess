package com.andreagenovese.chess.Pieces;

import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Move;
import com.andreagenovese.chess.Square;

public class Queen extends Piece {
    public Queen(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }
    public Queen(boolean isWhite, ChessBoard board, Square square) {
        super(isWhite, board, square);
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
