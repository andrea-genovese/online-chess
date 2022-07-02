package com.andreagenovese.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.andreagenovese.chess.Moves.Castling;
import com.andreagenovese.chess.Moves.EnPassant;
import com.andreagenovese.chess.Moves.Move;
import com.andreagenovese.chess.Moves.Promotion;
import com.andreagenovese.chess.Pieces.Piece;
import com.andreagenovese.chess.Pieces.Queen;

@SpringBootTest
public class ExecutionTest {
    @Test
    void capture() {
        ChessBoard c = new ChessBoard("1k5Q/8/8/8/8/8/8/2K5 b - - 0 1");
        assertTrue(c.someoneCanCapture(0, 1, true));
    }

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
    void enPassantBlack() {
        ChessBoard c = new ChessBoard("1k6/8/8/8/3pP3/8/8/2K5 b - e3 0 1");
        c = c.execute(new EnPassant(4, 3, 5, 4));
        assertEquals(new ChessBoard("1k6/8/8/8/8/4p3/8/2K5 w - - 0 2"), c);
    }

    @Test
    void promotion() {
        ChessBoard c = new ChessBoard("1k6/7P/8/8/8/8/8/2K5 w - - 0 1");
        c = c.execute(new Promotion(1, 7, 0, 7, Queen.class));
        assertEquals(new ChessBoard("1k5Q/8/8/8/8/8/8/2K5 b - - 0 1"), c);
        c = c.execute(new Move(0, 1, 0, 2));
        assertNull(c);
    }

    @Test
    void pinnedPiece() {
        ChessBoard c = new ChessBoard("r1bqkbnr/ppp2ppp/2np4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 4");
        assertNull(c.execute(new Move("c6", "d4")));
    }

    @Test
    void check() {
        ChessBoard c = new ChessBoard("r1bqkbnr/ppp2ppp/2Bp4/4p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 0 4");
        assertNull(c.execute(new Move("e8", "d7")));
        assertNull(c.execute(new Move("h7", "h6")));
        assertEquals(new ChessBoard("r2qkbnr/pppb1ppp/2Bp4/4p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 5"),
                c.execute(new Move("c8", "d7")));
    }

    @Test
    void castle(){
        ChessBoard c = new ChessBoard("r3kbnr/pppq1ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 6");
        c = c.execute(new Castling(7, 4, 7, 6));
        assertEquals(new ChessBoard("r3kbnr/pppq1ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 0 6"), c);
        
    }
    @Test 
    void rookMovement(){
        ChessBoard c = new ChessBoard("r1bqkbn1/pppp1ppr/2n4p/4p3/2B1P3/5N1P/PPPP1PP1/RNBQK2R w KQq - 1 5");
        c = c.execute(new Move("h1","h2"));
        assertEquals(new ChessBoard("r1bqkbn1/pppp1ppr/2n4p/4p3/2B1P3/5N1P/PPPP1PPR/RNBQK3 b Qq - 2 5"), c);
    }
}
