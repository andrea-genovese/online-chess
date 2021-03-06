package com.andreagenovese.chess.Pieces;

import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Square;
import com.andreagenovese.chess.Moves.Move;

public class Bishop extends Piece {

    public Bishop(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    public Bishop(boolean isWhite, ChessBoard board, Square square) {
        super(isWhite, board, square);
    }

    @Override
    public String toString() {
        return isWhite ? "B" : "b";
    }

    @Override
    public Set<Move> getMoves() {
        return getBishopMoves();
    }

}
