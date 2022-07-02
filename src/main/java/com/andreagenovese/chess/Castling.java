package com.andreagenovese.chess;

public class Castling extends Move {

    public Castling(int startRow, int startColumn, int destRow, int destColumn) {
        super(startRow, startColumn, destRow, destColumn);
    }

    public Castling(Square square, int destRow, int destColumn) {
        super(square, destRow, destColumn);
    }

    @Override
    public void execute(ChessBoard board) {
        boolean isKingSide = dest.column() == 6;
        Square rookSquare = new Square(start.row(), isKingSide ? 7 : 0);
        Square rookDest = new Square(start.row(), isKingSide ? 5 : 3);

        board.move(start, dest);
        board.move(rookSquare, rookDest);
    }

    @Override
    public String toString() {
        if (dest.column() - start.column() == 2) {
            return "O-O";
        } else if (dest.column() - start.column() == -2)
            return "O-O-O";
        else
            return "error";
    }
}
