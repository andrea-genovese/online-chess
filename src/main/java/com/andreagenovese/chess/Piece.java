package main.java.com.andreagenovese.chess;

import main.java.com.andreagenovese.chess.Pieces.Bishop;
import main.java.com.andreagenovese.chess.Pieces.King;
import main.java.com.andreagenovese.chess.Pieces.Knight;
import main.java.com.andreagenovese.chess.Pieces.Pawn;
import main.java.com.andreagenovese.chess.Pieces.Queen;
import main.java.com.andreagenovese.chess.Pieces.Rook;

public abstract class Piece {
    protected boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public static Piece fromChar(char c) {
        boolean isWhite = Character.isUpperCase(c);
        switch (c) {
            case 'K', 'k':
                return new King(isWhite);
            case 'Q', 'q':
                return new Queen(isWhite);
            case 'R', 'r':
                return new Rook(isWhite);
            case 'B', 'b':
                return new Bishop(isWhite);
            case 'N', 'n':
                return new Knight(isWhite);
            case 'P', 'p':
                return new Pawn(isWhite);
            default:
                throw new IllegalArgumentException(c + " is not a valid piece");
        }
    }
}