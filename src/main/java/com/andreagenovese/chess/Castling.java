package com.andreagenovese.chess;

public class Castling extends Move {

    public Castling(int startRow, int startColumn, int destRow, int destColumn) {
        super(startRow, startColumn, destRow, destColumn);
    }

    public Castling(Square square, int destRow, int destColumn) {
        super(square, destRow, destColumn);
    }

    @Override
    public String toString() {
        if(dest.column()-start.column() == 2) {
            return "O-O";
        }
        else if(dest.column() -start.column() == -2)
        return "O-O-O";
        else return "error";
    }
}
