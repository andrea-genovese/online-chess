package com.andreagenovese.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.andreagenovese.chess.Pieces.Bishop;
import com.andreagenovese.chess.Pieces.Knight;
import com.andreagenovese.chess.Pieces.Queen;
import com.andreagenovese.chess.Pieces.Rook;

@SpringBootTest
class GetMovesTest {

	@Test
	void enPassant() {
		ChessBoard c = new ChessBoard("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3");
		Set<Move> moves = c.getPiece(3, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(3, 4, 2, 4));
		expected.add(new EnPassant(3, 4, 2, 5));

		assertEquals(expected, moves);

	}

	@Test
	void enPassantBlack() {
		ChessBoard c = new ChessBoard("1k6/8/8/8/3pP3/8/8/2K5 b - e3 0 1");
		Set<Move> moves = c.getPiece(4, 3).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(4, 3, 5, 3));
		expected.add(new EnPassant(4, 3, 5, 4));

		assertEquals(expected, moves);

	}

	@Test
	void enPassant2() {
		ChessBoard c = new ChessBoard("rnb1kbnr/ppp1p1pp/3q4/3pPp2/8/7P/PPPP1PP1/RNBQKBNR w KQkq f6 1 4");
		Set<Move> moves = c.getPiece(3, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(3, 4, 2, 4));
		expected.add(new EnPassant(3, 4, 2, 5));
		expected.add(new Move(3, 4, 2, 3));
		assertEquals(expected, moves);
	}

	@Test
	void promotion() {
		ChessBoard c = new ChessBoard("1k6/7P/8/8/8/8/8/2K5 w - - 0 1");
		Set<Move> moves = c.getPiece(1, 7).getMoves();
		Set<Move> expected = new HashSet<>();

		expected.add(new Promotion(1, 7, 0, 7, Queen.class));
		expected.add(new Promotion(1, 7, 0, 7, Rook.class));
		expected.add(new Promotion(1, 7, 0, 7, Bishop.class));
		expected.add(new Promotion(1, 7, 0, 7, Knight.class));

		assertEquals(expected, moves);
	}

	@Test
	void blockedPawn() {
		ChessBoard c = new ChessBoard("1k2Nn2/5P2/8/8/8/8/8/2K5 w - - 0 1");
		Set<Move> moves = c.getPiece(1, 5).getMoves();
		assertEquals(new HashSet<>(), moves);
	}

	@Test
	void rook() {
		ChessBoard c = new ChessBoard("1k6/8/8/4r3/3pP3/8/8/2K5 b - e4 0 1");
		Set<Move> moves = c.getPiece(3, 4).getMoves();
		Set<Move> expected = new HashSet<>();

		expected.add(new Move(3, 4, 4, 4));

		expected.add(new Move(3, 4, 3, 0));
		expected.add(new Move(3, 4, 3, 1));
		expected.add(new Move(3, 4, 3, 2));
		expected.add(new Move(3, 4, 3, 3));
		expected.add(new Move(3, 4, 3, 5));
		expected.add(new Move(3, 4, 3, 6));
		expected.add(new Move(3, 4, 3, 7));

		expected.add(new Move(3, 4, 0, 4));
		expected.add(new Move(3, 4, 1, 4));
		expected.add(new Move(3, 4, 2, 4));

		assertEquals(expected, moves);
	}

	@Test
	void castling() {
		ChessBoard c = new ChessBoard("1k6/8/8/8/8/8/3PPPPP/4K2R b K e4 0 1");
		Set<Move> moves = c.getPiece(7, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Castling(7, 4, 7, 6));
		expected.add(new Move(7, 4, 7, 5));
		expected.add(new Move(7, 4, 7, 3));
		assertEquals(expected, moves);
	}
	@Test
	void castlingImpossible(){//there is a piece in between
		ChessBoard c = new ChessBoard("1k6/8/8/8/8/8/3PPPPP/4K1NR b K e4 0 1");
		Set<Move> moves = c.getPiece(7, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(7, 4, 7, 5));
		expected.add(new Move(7, 4, 7, 3));
		assertEquals(expected, moves);
	}
	@Test
	void castlingImpossible2(){//king has been moved
		ChessBoard c = new ChessBoard("1k6/8/8/8/8/8/3PPPPP/4K2R b kq e4 0 1");
		Set<Move> moves = c.getPiece(7, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(7, 4, 7, 5));
		expected.add(new Move(7, 4, 7, 3));
		assertEquals(expected, moves);
	}
	@Test
	void castlingImpossible3(){//a piece is covering a square the king has to cross
		ChessBoard c = new ChessBoard("1k6/8/8/8/8/7n/3PPPPP/4K2R b K e4 0 1");
		Set<Move> moves = c.getPiece(7, 4).getMoves();
		Set<Move> expected = new HashSet<>();
		expected.add(new Move(7, 4, 7, 5));
		expected.add(new Move(7, 4, 7, 3));
		assertEquals(expected, moves);
	}
}
