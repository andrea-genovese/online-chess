package com.andreagenovese.chess;

public class EnPassant extends Move {

    public EnPassant(Square start, Square dest) {
        super(start,dest);
    }

    @Override
    public String toString() {
        
        return super.toString()+" (en passant)";
    }
}
