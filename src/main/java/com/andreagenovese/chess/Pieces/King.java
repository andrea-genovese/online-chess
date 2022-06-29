package main.java.com.andreagenovese.chess.Pieces;

import java.util.HashSet;
import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;

public class King extends Piece {
    public King(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = new HashSet<>();
        addIfValid(moves, row - 1, column - 1);
        addIfValid(moves, row - 1, column);
        addIfValid(moves, row - 1, column + 1);

        addIfValid(moves, row, column + 1);
        addIfValid(moves, row, column - 1);

        addIfValid(moves, row + 1, column + 1);
        addIfValid(moves, row + 1, column);
        addIfValid(moves, row + 1, column - 1);
        return moves;
    }
}
