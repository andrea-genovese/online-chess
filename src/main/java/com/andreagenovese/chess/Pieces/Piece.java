package com.andreagenovese.chess.Pieces;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Move;
import com.andreagenovese.chess.Square;

public abstract class Piece {
    protected boolean isWhite;
    protected ChessBoard board;
    protected Square square;

    public Piece(boolean isWhite, ChessBoard board, Square square) {
        this.isWhite = isWhite;
        this.board = board;
        this.square = square;
    }

    public Piece(boolean isWhite, ChessBoard board, int row, int column) {
        this(isWhite, board, new Square(row, column));
    }

    public final Piece clone(ChessBoard board) {
        try {
            Constructor<? extends Piece> constructor = getClass().getConstructor(boolean.class, ChessBoard.class,
                    Square.class);
            return constructor.newInstance(isWhite, board, square);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public boolean canCapture(Square s) {
        return getMoves().contains(new Move(square, s));
    }

    public Square square() {
        return square;
    }

    private boolean shouldContinue(Set<Move> moves, int r, int c) {

        Piece p = this.board.getPiece(r, c);
        if (p != null && p.isWhite == this.isWhite) {
            return false;
        }
        moves.add(new Move(this.square, r, c));
        if (p != null) {
            return false;
        }
        return true;
    }

    protected boolean addIfValid(Set<Move> moves, int r, int c) {
        Square dest = new Square(r, c);
        if (!dest.doesExists()) {
            return false;
        }
        Piece p = board.getPiece(dest);
        if (p == null || p.isWhite != this.isWhite) {
            moves.add(new Move(this.square, dest));
            return true;
        }
        return false;
    }

    protected Set<Move> getBishopMoves() {
        Set<Move> moves = new HashSet<>();
        int row = this.square.row();
        int column = this.square.column();
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
        int row = this.square.row();
        int column = this.square.column();
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

    public boolean isWhite() {
        return isWhite;
    }

    public void square(Square square) {
        this.square = square;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isWhite ? 1231 : 1237);
        result = prime * result + ((square == null) ? 0 : square.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (isWhite != other.isWhite)
            return false;
        if (square == null) {
            if (other.square != null)
                return false;
        } else if (!square.equals(other.square))
            return false;
        return true;
    }
    
}