package com.andreagenovese.chess;

import java.util.Arrays;
import java.util.Objects;

import com.andreagenovese.chess.Pieces.King;
import com.andreagenovese.chess.Pieces.Piece;

public class ChessBoard {
    public static final String INITIAL_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private Piece[][] board;
    private boolean isWhiteTurn,
            whiteKingCastling,
            whiteQueenCastling,
            blackKingCastling,
            blackQueenCastling;
    private short halfMoves, moves;
    private Square enPassant;

    public Piece[][] getBoard() {
        return board;
    }

    public Square getEnPassant() {
        return enPassant;
    }

    public boolean whiteKingCastling() {
        return whiteKingCastling;
    }

    public boolean whiteQueenCastling() {
        return whiteQueenCastling;
    }

    public boolean blackKingCastling() {
        return blackKingCastling;
    }

    public boolean blackQueenCastling() {
        return blackQueenCastling;
    }

    public ChessBoard execute(Move m) {
        Piece toMove = getPiece(m.start());
        // check turn
        if (toMove.isWhite() != isWhiteTurn) {
            return null;
        }
        // check if the move is possible
        if (!toMove.getMoves().contains(m)) {
            return null;
        }
        ChessBoard clone = clone();
        toMove = clone.getPiece(m.start());
        m.execute(clone);
        clone.isWhiteTurn = !isWhiteTurn;
        if (!isWhiteTurn) {
            clone.moves++;
        }

        if (clone.someoneCanCapture(clone.getKing(toMove.isWhite()), toMove.isWhite())) {
            return null;
        }
        return clone;
    }

    public void move(Piece p, Square s) {
        board[s.row()][s.column()] = p;
        board[p.square().row()][p.square().column()] = null;
        p.square(s);
    }

    public void move(Square start, Square dest) {
        move(getPiece(start), dest);
    }

    public Piece getPiece(Square s) {
        return board[s.row()][s.column()];
    }

    public Piece getPiece(int r, int c) {
        return board[r][c];
    }

    public ChessBoard clone() {
        ChessBoard clone = new ChessBoard();
        clone.board = new Piece[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                Piece p = board[row][column];
                if (p != null) {
                    clone.board[row][column] = p.clone(clone);
                }
            }
        }
        clone.isWhiteTurn = isWhiteTurn;
        clone.whiteKingCastling = whiteKingCastling;
        clone.whiteQueenCastling = whiteQueenCastling;
        clone.blackKingCastling = blackKingCastling;
        clone.blackQueenCastling = blackQueenCastling;
        clone.halfMoves = halfMoves;
        clone.moves = moves;
        clone.enPassant = enPassant;
        return clone;
    }

    private Square getKing(boolean white) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j];
                if (p != null && p.getClass() == King.class && p.isWhite() == white) {
                    return p.square();
                }
            }
        }
        return null;
    }

    public boolean someoneCanCapture(Square s, boolean color) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                Piece p = board[row][column];
                if (p != null && p.isWhite() == color && p.canCapture(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean someoneCanCapture(int r, int c, boolean color) {
        return someoneCanCapture(new Square(r, c), color);
    }

    public ChessBoard(String fen) {
        String[] arr = fen.split(" ");
        String position = arr[0],
                turn = arr[1],
                castling = arr[2],
                halfMoves = arr[4],
                moves = arr[5];
        this.board = boardFromString(position);
        this.isWhiteTurn = turn.equals("w");
        whiteKingCastling = castling.contains("K");
        whiteQueenCastling = castling.contains("Q");
        blackKingCastling = castling.contains("k");
        blackQueenCastling = castling.contains("q");
        this.halfMoves = Short.parseShort(halfMoves);
        this.moves = Short.parseShort(moves);
        this.enPassant = Square.fromString(arr[3]);

    }

    public ChessBoard() {

    }

    private Piece[][] boardFromString(String position) {
        String[] rows = position.split("/");
        Piece[][] board = new Piece[8][];
        if (rows.length != 8) {
            throw new IllegalArgumentException("Wrong number of rows");
        }
        for (byte i = 0; i < rows.length; i++) {
            board[i] = rowFromString(rows[i], i);
        }
        return board;
    }

    private Piece[] rowFromString(String rowStr, byte rowIndex) {
        Piece[] row = new Piece[8];
        byte column = 0;
        for (byte i = 0; i < rowStr.length(); i++) {
            char c = rowStr.charAt(i);
            if (Character.isDigit(c)) {
                column += c - '0';
                continue;
            }
            row[column] = Piece.fromChar(c, this, rowIndex, column);
            column++;
        }
        return row;
    }

    @Override
    public String toString() {
        String str = "Turno: ";
        str += isWhiteTurn ? "bianco" : "nero";
        str += "\nenPassant: "+enPassant;
        str += "\n arrocco: ";
        str += whiteKingCastling + "\n";
        str += whiteQueenCastling + "\n";
        str += blackKingCastling + "\n";
        str += blackQueenCastling + "\n";
        str += "semimosse: " + halfMoves;
        str += "\nmosse: " + moves + '\n';
        for (int i = 0; i < 8; i++) {
            Piece[] row = board[i];
            str += i + 1 + " |";
            for (Piece piece : row) {
                str += piece == null ? "  " : " " + piece;
            }
            str += '\n';
        }
        str += "-------------------\n  | a b c d e f g h";
        return str;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blackKingCastling ? 1231 : 1237);
        result = prime * result + (blackQueenCastling ? 1231 : 1237);
        result = prime * result + Arrays.deepHashCode(board);
        result = prime * result + ((enPassant == null) ? 0 : enPassant.hashCode());
        result = prime * result + halfMoves;
        result = prime * result + (isWhiteTurn ? 1231 : 1237);
        result = prime * result + moves;
        result = prime * result + (whiteKingCastling ? 1231 : 1237);
        result = prime * result + (whiteQueenCastling ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChessBoard other = (ChessBoard) obj;
        if (blackKingCastling != other.blackKingCastling)
            return false;
        if (blackQueenCastling != other.blackQueenCastling)
            return false;
        if (!Arrays.deepEquals(board, other.board))
            return false;
        if (enPassant == null) {
            if (other.enPassant != null)
                return false;
        } else if (!enPassant.equals(other.enPassant))
            return false;
        if (halfMoves != other.halfMoves)
            return false;
        if (isWhiteTurn != other.isWhiteTurn)
            return false;
        if (moves != other.moves)
            return false;
        if (whiteKingCastling != other.whiteKingCastling)
            return false;
        if (whiteQueenCastling != other.whiteQueenCastling)
            return false;
        return true;
    }

    public static void main(String[] args) {
        ChessBoard c = new ChessBoard("1k6/8/8/8/3pP3/8/8/2K5 b - e3 0 1");
		c.execute(new EnPassant(4, 3, 5, 4));
        
        boolean b = Objects.equals(new ChessBoard("1k6/8/8/8/8/4p3/8/2K5 w - - 0 2"), c);
        System.out.println(b);
    }

    public void setEnpassant(Square square) {
        this.enPassant = square;
    }
}