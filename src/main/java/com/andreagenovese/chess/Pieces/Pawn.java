package main.java.com.andreagenovese.chess.Pieces;

import java.util.HashSet;
import java.util.Set;

import main.java.com.andreagenovese.chess.ChessBoard;
import main.java.com.andreagenovese.chess.Move;
import main.java.com.andreagenovese.chess.Piece;
import main.java.com.andreagenovese.chess.Square;

public class Pawn extends Piece {

    public Pawn(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "P" : "p";
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = new HashSet<>();
        int start = isWhite ? 6 : 1;
        int nextRow = isWhite ? row - 1 : row + 1;
        int secondRow = isWhite ? row - 2 : row + 2;

        Piece nextRowPiece = board.getBoard()[nextRow][column];
        // normal movement
        if (nextRowPiece == null) {
            addMove(moves, nextRow, column);
            // double movement
            if (row == start && board.getBoard()[secondRow][column] == null) {
                addMove(moves, secondRow, column);
            }
        }
        // capture left
        if (column - 1 >= 0 && board.getBoard()[nextRow][column - 1] != null) {
            addMove(moves, nextRow, column - 1);
        }
        // capture right
        if (column + 1 < 8 && board.getBoard()[nextRow][column + 1] != null) {
            addMove(moves, nextRow, column + 1);
        }

        return moves;
    }

    protected void addMove(Set<Move> moves, int r, int c) {
        int lastRow = isWhite ? 0 : 7;
        Piece p = board.getBoard()[r][c];
        if (p != null && p.isWhite() == this.isWhite) {
            return;
        }
        if (r == lastRow) {
            moves.add(new Move(this, r, c, Queen.class));
            moves.add(new Move(this, r, c, Rook.class));
            moves.add(new Move(this, r, c, Bishop.class));
            moves.add(new Move(this, r, c, Knight.class));
        } else {
            moves.add(new Move(this, r, c));
        }
    }
}
