package main.java.com.andreagenovese.chess;

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
}