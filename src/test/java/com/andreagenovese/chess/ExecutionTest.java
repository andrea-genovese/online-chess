package com.andreagenovese.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.andreagenovese.chess.Pieces.Piece;

@SpringBootTest
public class ExecutionTest {
    @Test
    void enPassant() {
        ChessBoard c = new ChessBoard("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3");
        assertNull(c.execute(new Move(3, 3, 4, 3)));
        c = c.execute(new EnPassant(3, 4, 2, 5));
        assertNotNull(c);
        assertNull(c.getPiece(3, 4));
        assertNull(c.getPiece(3, 5));
        Piece pawn = c.getPiece(2, 5);
        assertNotNull(pawn);
        assertEquals(new Square(2, 5), pawn.square());
        assertEquals(new ChessBoard("rnbqkbnr/ppp1p1pp/5P2/3p4/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 3"), c);
    }
    @Test
    void enPassantBlack(){
        ChessBoard c = new ChessBoard("1k6/8/8/8/3pP3/8/8/2K5 b - e3 0 1");
		c = c.execute(new EnPassant(4, 3, 5, 4));
        assertEquals(new ChessBoard("1k6/8/8/8/8/4p3/8/2K5 w - - 0 2"), c);
    }
    //TODO testare arrocco, scacchi e mosse illegali
}
