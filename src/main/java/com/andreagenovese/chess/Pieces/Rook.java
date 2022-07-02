package com.andreagenovese.chess.Pieces;

import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Move;
import com.andreagenovese.chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite, ChessBoard board, Square square) {
        super(isWhite, board, square);
    }

    public Rook(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    public Set<Move> getMoves() {
        return getRookMoves();
    }

    @Override
    public String toString() {
        return isWhite ? "R" : "r";
    }
}
