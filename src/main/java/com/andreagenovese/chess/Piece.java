package main.java.com.andreagenovese.chess;

import java.util.HashSet;
import java.util.Set;

import main.java.com.andreagenovese.chess.Pieces.Bishop;
import main.java.com.andreagenovese.chess.Pieces.King;
import main.java.com.andreagenovese.chess.Pieces.Knight;
import main.java.com.andreagenovese.chess.Pieces.Pawn;
import main.java.com.andreagenovese.chess.Pieces.Queen;
import main.java.com.andreagenovese.chess.Pieces.Rook;

public abstract class Piece {
    protected boolean isWhite;
    protected ChessBoard board;
    protected int row, column;

    public Piece(boolean isWhite, ChessBoard board, int row, int column) {
        this.isWhite = isWhite;
        this.board = board;
        this.row = row;
        this.column = column;
    }

    public static Piece fromChar(char c, ChessBoard board, int row, int column) {
        boolean isWhite = Character.isUpperCase(c);
        switch (c) {
            case 'K', 'k':
                return new King(isWhite, board, row, column);
            case 'Q', 'q':
                return new Queen(isWhite, board, row, column);
            case 'R', 'r':
                return new Rook(isWhite, board, row, column);
            case 'B', 'b':
                return new Bishop(isWhite, board, row, column);
            case 'N', 'n':
                return new Knight(isWhite, board, row, column);
            case 'P', 'p':
                return new Pawn(isWhite, board, row, column);
            default:
                throw new IllegalArgumentException(c + " is not a valid piece");
        }
    }

    public abstract Set<Move> getMoves();

    private boolean shouldContinue(Set<Move> moves, int r, int c) {
        Piece p = this.board.getBoard()[r][c];
        if (p != null && p.isWhite == this.isWhite) {
            return false;
        }
        moves.add(new Move(this, r, c));
        if (p != null) {
            return false;
        }
        return true;
    }

    protected boolean addIfValid(Set<Move> moves, int r, int c) {
        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }
        Piece p = board.getBoard()[r][c];
        if (p == null || p.isWhite != this.isWhite) {
            moves.add(new Move(this, r, c));
            return true;
        }
        return false;
    }

    protected Set<Move> getBishopMoves() {
        Set<Move> moves = new HashSet<>();
        // diagonale basso-destra
        for (int r = row + 1, c = column + 1; r < 8 && c < 8; r++, c++) {
            if (!shouldContinue(moves, r, c)) {
                break;
            }
        }
        // diagonale basso-sinistra
        for (int r = row + 1, c = column - 1; r < 8 && c >= 0; r++, c--) {
            if (!shouldContinue(moves, r, c)) {
                break;
            }
        }
        // diagonale alto-destra
        for (int r = row - 1, c = column + 1; r >= 0 && c < 8; r--, c++) {
            if (!shouldContinue(moves, r, c)) {
                break;
            }
        }
        // diagonale alto-sinistra
        for (int r = row - 1, c = column - 1; r >= 0 && c >= 0; r--, c--) {
            if (!shouldContinue(moves, r, c)) {
                break;
            }
        }

        return moves;
    }

    protected Set<Move> getRookMoves() {
        Set<Move> moves = new HashSet<>();
        // movimento verticale verso basso
        for (int i = row + 1; i < 8; i++) {
            if (!shouldContinue(moves, i, column))
                break;
        }
        // movimento verticale verso alto
        for (int i = row - 1; i >= 0; i--) {
            if (!shouldContinue(moves, i, column))
                break;
        }
        // movimento orizzontale verso destra
        for (int i = column + 1; i < 8; i++) {
            if (!shouldContinue(moves, row, i))
                break;
        }
        // movimento orizzontale verso sinistra
        for (int i = column - 1; i >= 0; i--) {
            if (!shouldContinue(moves, row, i))
                break;
        }
        return moves;
    }
}