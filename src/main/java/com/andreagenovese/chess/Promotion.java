package com.andreagenovese.chess;

import java.lang.reflect.Constructor;

import com.andreagenovese.chess.Pieces.Piece;

public class Promotion extends Move {
    private Class<? extends Piece> promotion;

    public Promotion(Square start, int destRow, int destColumn, Class<? extends Piece> promotion) {
        super(start, destRow, destColumn);
        this.promotion = promotion;
    }

    public Promotion(int startRow, int startColumn, int destRow, int destColumn, Class<? extends Piece> promotion) {
        super(startRow, startColumn, destRow, destColumn);
        this.promotion = promotion;
    }

    public void execute(ChessBoard board) {
        Piece[][] arr = board.getBoard();
        Piece toMove = arr[start.row()][start.column()];

        try {
            Constructor<? extends Piece> constructor = promotion.getConstructor(Square.class, ChessBoard.class,
                    boolean.class);
            Piece p = constructor.newInstance(dest, board, toMove.isWhite());
            arr[dest.row()][dest.column()] = p;
            arr[start.row()][start.column()] = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return super.toString() + "=" + promotion.getSimpleName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((promotion == null) ? 0 : promotion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Promotion other = (Promotion) obj;
        if (promotion == null) {
            if (other.promotion != null)
                return false;
        } else if (!promotion.equals(other.promotion))
            return false;
        return true;
    }

}
