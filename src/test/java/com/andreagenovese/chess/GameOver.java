package com.andreagenovese.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.andreagenovese.chess.Exceptions.CheckMateException;
import com.andreagenovese.chess.Exceptions.HalfMovesException;
import com.andreagenovese.chess.Exceptions.InsufficientMaterialException;
import com.andreagenovese.chess.Exceptions.StaleMateException;
import com.andreagenovese.chess.Moves.Move;
import com.andreagenovese.chess.Moves.Promotion;
import com.andreagenovese.chess.Pieces.Bishop;

public class GameOver {
    @Test
    void checkMate() {
        ChessBoard c = new ChessBoard("rnbqkbnr/ppppp2p/5p2/6p1/4P3/2N5/PPPP1PPP/R1BQKBNR w KQkq - 0 3");
        ChessBoard next = c.execute(new Move("d1", "h5"));
        try {
            next.isGameOver();
            fail();
        } catch (CheckMateException e) {
            assertEquals(true, e.getWinner());
        }

    }

    @Test
    void staleMate() {
        ChessBoard c = new ChessBoard("2k5/2P5/8/2K5/8/8/8/8 w - - 0 1");
        c = c.execute(new Move("c5", "c6"));
        try {
            c.isGameOver();
            fail();
        } catch (StaleMateException e) {

        }
    }

    @Test
    void fiftyMoves() {
        ChessBoard c = new ChessBoard("8/2k5/8/8/8/5P2/2K5/8 w - - 49 1");
        c = c.execute(new Move("c2", "c1"));
        try {
            c.isGameOver();
            fail();
        } catch (HalfMovesException e) {

        }
    }
    @Test 
    void insufficientMaterial(){
        ChessBoard c = new ChessBoard("8/8/4k3/5P2/8/3K4/8/8 b - - 0 3");
        c = c.execute(new Move("e6","f5"));
        try {
            c.isGameOver();
            fail();
        } catch (InsufficientMaterialException e) {
            
        }
    }
    @Test 
    void insufficientMaterial2(){
        ChessBoard c = new ChessBoard("8/5P2/8/2k2K2/8/8/8/8 w - - 3 9");
        c.isGameOver();
        c = c.execute(new Promotion("f7","f8", Bishop.class));
        try {
            c.isGameOver();
            fail();
        } catch (InsufficientMaterialException e) {}
    }
}
