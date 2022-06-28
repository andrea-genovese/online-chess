package main.java.com.andreagenovese.chess.Pieces;

import java.util.HashSet;
import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;

public class Knight extends Piece {

    public Knight(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "N" : "n";
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = new HashSet<>();
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
