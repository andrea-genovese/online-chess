package com.andreagenovese.chess.Moves;

import com.andreagenovese.chess.ChessBoard;
import com.andreagenovese.chess.Square;
import com.andreagenovese.chess.Pieces.King;
import com.andreagenovese.chess.Pieces.Pawn;
import com.andreagenovese.chess.Pieces.Piece;
import com.andreagenovese.chess.Pieces.Rook;

public class Move {
	protected Square start;
	protected Square dest;

	public Move(int startRow, int startColumn, int destRow, int destColumn) {
		this(new Square(startRow, startColumn), new Square(destRow, destColumn));

	}

	public Move(Square start, Square dest) {
		this.start = start;
		this.dest = dest;
	}

	public Move(Square start, int destRow, int destColumn) {
		this(start, new Square(destRow, destColumn));
	}

	public Move(String start, String dest) {
		this(Square.fromString(start), Square.fromString(dest));
	}

	public Square start() {
		return start;
	}

	public boolean isCapture(ChessBoard board) {
		return board.getPiece(dest) != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Move other = (Move) obj;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public String toString() {
		return start.toString() + "->" + dest.toString();
	}

	public void execute(ChessBoard board) {
		Piece p = board.getPiece(start);
		// setting enpassant
		if (p instanceof Pawn && Math.abs(start.row() - dest.row()) == 2) {
			Square enPassant = new Square(start.row() + (p.isWhite() ? -1 : 1), start.column());
			board.setEnpassant(enPassant);
		} else {
			board.setEnpassant(null);
		}
		// setting halfMoves
		if (p instanceof Pawn || isCapture(board)) {
			board.setHalfMoves((short) 0);
		} else {
			board.setHalfMoves((short) (board.getHalfMoves() + 1));
		}
		// castling cancellation
		if(board.getPiece(dest) instanceof Rook rook){
			cancelCastlingRook(board, rook);
		}
		if (p instanceof Rook rook) {
			cancelCastlingRook(board, rook);
		} else if (p instanceof King) {
			board.removeCastling(p.isWhite());
		}
		board.move(p, dest);
	}

	private void cancelCastlingRook(ChessBoard board, Rook r) {
		Square kingRook;
		Square queenRook;
		if (r.isWhite()) {
			kingRook = new Square(7, 7);
			queenRook = new Square(7, 0);
		} else {
			queenRook = new Square(0, 0);
			kingRook = new Square(0, 7);
		}
		if (r.square().equals(kingRook)) {
			board.removeCastling(r.isWhite(), true);
		} else if (r.square().equals(queenRook)) {
			board.removeCastling(r.isWhite(), false);
		}
	}
}