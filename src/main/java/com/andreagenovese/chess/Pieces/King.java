package com.andreagenovese.chess.Pieces;

import java.util.HashSet;
import java.util.Set;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Square;
import com.andreagenovese.chess.Moves.Castling;
import com.andreagenovese.chess.Moves.Move;

public class King extends Piece {
    public King(boolean isWhite, ChessBoard board, int row, int column) {
        super(isWhite, board, row, column);
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

    public King(boolean isWhite, ChessBoard board, Square square) {
        super(isWhite, board, square);
    }

    @Override
    public Set<Move> getMoves() {
        Set<Move> moves = new HashSet<>();
        int row = this.square.row();
        int column = this.square.column();
        addIfValid(moves, row - 1, column - 1);
        addIfValid(moves, row - 1, column);
        addIfValid(moves, row - 1, column + 1);

        addIfValid(moves, row, column + 1);
        addIfValid(moves, row, column - 1);

        addIfValid(moves, row + 1, column + 1);
        addIfValid(moves, row + 1, column);
        addIfValid(moves, row + 1, column - 1);
        boolean kingSide = isWhite ? board.whiteKingCastling() : board.blackKingCastling();
        boolean queenSide = isWhite ? board.whiteQueenCastling() : board.blackQueenCastling();
        int castleRow = isWhite ? 7 : 0;
        //TODO controllare prima di arroccare di non essere sotto scacco
        //TODO impedire ricorsione infinita di someOneCanCapture
        if (kingSide &&
                board.getPiece(castleRow, 5) == null &&
                board.getPiece(castleRow, 6) == null &&
                !board.someoneCanCaptureNoKing(castleRow, 5, !isWhite) &&
                !board.someoneCanCaptureNoKing(castleRow, 6, !isWhite)) {
            moves.add(new Castling(this.square, castleRow, 6));
        }
        if (queenSide &&
                board.getPiece(castleRow, 3) == null &&
                board.getPiece(castleRow, 2) == null &&
                board.getPiece(castleRow, 1) == null &&
                !board.someoneCanCapture(castleRow, 2, !isWhite) &&
                !board.someoneCanCapture(castleRow, 3, !isWhite)) {
            moves.add(new Castling(this.square, castleRow, 2));
        }
        return moves;
    }
}
