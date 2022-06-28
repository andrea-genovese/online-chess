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
    protected byte row, column;

    public Piece(boolean isWhite, ChessBoard board, byte row, byte column) {
        this.isWhite = isWhite;
        this.board = board;
        this.row = row;
        this.column = column;
    }

    public static Piece fromChar(char c, ChessBoard board, byte row, byte column) {
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

    private boolean shouldContinue(Set<Move> moves, byte r, byte c) {
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

    protected Set<Move> getBishopMoves() {
        Set<Move> moves = new HashSet<>();
        // diagonale basso-destra
        for (byte r = (byte) (row + 1), c = (byte) (column + 1); r < 8 && c < 8; r++, c++) {
            if(!shouldContinue(moves, r, c)){
                break;
            }
        }
        //diagonale basso-sinistra
        for (byte r = (byte) (row + 1), c = (byte) (column - 1); r < 8 && c < 8; r++, c--) {
            if(!shouldContinue(moves, r, c)){
                break;
            }
        }
        //diagonale alto-destra
        for (byte r = (byte) (row - 1), c = (byte) (column + 1); r < 8 && c < 8; r--, c++) {
            if(!shouldContinue(moves, r, c)){
                break;
            }
        }
        //diagonale alto-sinistra
        for (byte r = (byte) (row - 1), c = (byte) (column - 1); r < 8 && c < 8; r--, c--) {
            if(!shouldContinue(moves, r, c)){
                break;
            }
        }

        return moves;
    }

    protected Set<Move> getRookMoves() {
        Set<Move> moves = new HashSet<>();
        // movimento verticale verso basso
        for (byte i = (byte) (row + 1); i < 8; i++) {
            if (!shouldContinue(moves, i, column))
                break;
        }
        // movimento verticale verso alto
        for (byte i = (byte) (row - 1); i >= 0; i--) {
            if (!shouldContinue(moves, i, column))
                break;
        }
        // movimento orizzontale verso destra
        for (byte i = (byte) (column + 1); i < 8; i++) {
            if (!shouldContinue(moves, row, i))
                break;
        }
        // movimento orizzontale verso sinistra
        for (byte i = (byte) (column - 1); i >= 0; i--) {
            if (!shouldContinue(moves, row, i))
                break;
        }
        return moves;
    }
}