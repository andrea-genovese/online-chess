package com.andreagenovese.chess;

import com.andreagenovese.chess.Pieces.Piece;

public class Promotion extends Move{
    private Class<? extends Piece> promotion;
    
    public Promotion(Square start, int destRow, int destColumn, Class<? extends Piece> promotion) {
        super(start, destRow, destColumn);
        this.promotion = promotion;
    }

    @Override
    public String toString() {        
        return super.toString()+ "=" + promotion.getSimpleName();
    }
    
}
