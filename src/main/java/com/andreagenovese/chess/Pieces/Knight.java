package com.andreagenovese.chess.Pieces;

import java.util.HashSet;
import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Square;
import com.andreagenovese.chess.Moves.Move;

public class Knight extends Piece {

    public Knight(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }
    public Knight(boolean isWhite, ChessBoard board, Square square) {
        super(isWhite, board, square);
    }
    @Override
    public String toString() {
        return isWhite ? "N" : "n";
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = new HashSet<>();
        int row = this.square.row();
        int column = this.square.column();
        addIfValid(moves, row - 1, column - 2);
        addIfValid(moves, row - 2, column - 1);
        addIfValid(moves, row - 2, column + 1);
        addIfValid(moves, row - 1, column + 2);
        addIfValid(moves, row + 1, column - 2);
        addIfValid(moves, row + 2, column - 1);
        addIfValid(moves, row + 2, column + 1);
        addIfValid(moves, row + 1, column + 2);
        return moves;
    }
}
