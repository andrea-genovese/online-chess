package com.andreagenovese.chess;

public class EnPassant extends Move {

    public EnPassant(Square start, Square dest) {
        super(start,dest);
    }
    
    public EnPassant(int startRow, int startColumn, int destRow, int destColumn) {
        super(startRow, startColumn, destRow, destColumn);
    }

    @Override
    public String toString() {
        
        return super.toString()+" (en passant)";
    }
}
