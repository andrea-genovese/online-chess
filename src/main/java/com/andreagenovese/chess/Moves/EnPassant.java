package com.andreagenovese.chess.Moves;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Square;
import com.andreagenovese.chess.Pieces.Piece;

public class EnPassant extends Move {

    public EnPassant(Square start, Square dest) {
        super(start,dest);
    }
    
    public EnPassant(int startRow, int startColumn, int destRow, int destColumn) {
        super(startRow, startColumn, destRow, destColumn);
    }
    @Override
    public void execute(ChessBoard board){
        board.move(start,dest);
        board.setEnpassant(null);
        board.setHalfMoves((short)0);
        Piece[][] arr = board.getBoard();
        arr[start.row()][dest.column()] = null;
    }
    @Override
    public String toString() {
        
        return super.toString()+" (en passant)";
    }
}
