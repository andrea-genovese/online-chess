package com.andreagenovese.chess;

import java.util.Arrays;
import java.util.Set;

import com.andreagenovese.chess.Exceptions.CheckMateException;
import com.andreagenovese.chess.Exceptions.HalfMovesException;
import com.andreagenovese.chess.Exceptions.InsufficientMaterialException;
import com.andreagenovese.chess.Exceptions.StaleMateException;
import com.andreagenovese.chess.Moves.Move;
import com.andreagenovese.chess.Pieces.Bishop;
import com.andreagenovese.chess.Pieces.King;
import com.andreagenovese.chess.Pieces.Knight;
import com.andreagenovese.chess.Pieces.Pawn;
import com.andreagenovese.chess.Pieces.Piece;
import com.andreagenovese.chess.Pieces.Queen;
import com.andreagenovese.chess.Pieces.Rook;

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

    public void setHalfMoves(short halfMoves) {
        this.halfMoves = halfMoves;
    }

    public void removeCastling(boolean color, boolean kingSide) {
        if (color) {
            if (kingSide) {
                whiteKingCastling = false;
            } else {
                whiteQueenCastling = false;
            }
        } else {
            if (kingSide) {
                blackKingCastling = false;
            } else {
                blackQueenCastling = false;
            }
        }
    }

    public void removeCastling(boolean color) {
        if (color) {
            whiteKingCastling = false;
            whiteQueenCastling = false;
        } else {
            blackKingCastling = false;
            blackQueenCastling = false;
        }
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

    private boolean isMaterialSufficient(boolean color) {
        byte lightPieces = 0;
        for (Piece[] row : board) {
            for (Piece p : row) {
                if (p == null || p.isWhite() != color) {
                    continue;
                }
                if (p instanceof Queen || p instanceof Rook || p instanceof Pawn) {
                    return true;
                }
                if (p instanceof Bishop || p instanceof Knight) {
                    lightPieces++;
                }
            }
        }
        return lightPieces >= 2;
            
    }
    private boolean isMaterialSufficient() {
        return isMaterialSufficient(true) || isMaterialSufficient(false);
            
    }
    boolean isGameOver() {
        if (halfMoves >= 50) {
            throw new HalfMovesException();
        }
        if (!isMaterialSufficient()) {
            throw new InsufficientMaterialException();
        }
        for (Piece[] row : board) {
            for (Piece p : row) {
                if (p == null || p.isWhite() != isWhiteTurn) {
                    continue;
                }
                Set<Move> moves = p.getMoves();
                for (Move m : moves) {
                    ChessBoard clone = clone();
                    if (clone.execute(m) != null) {
                        return false;
                    }
                }
            }
        }
        if (someoneCanCapture(getKing(isWhiteTurn), !isWhiteTurn)) {
            throw new CheckMateException(!isWhiteTurn);
        } else {
            throw new StaleMateException();
        }
    }

    public ChessBoard execute(Move m) throws StaleMateException, CheckMateException, HalfMovesException {
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

        if (clone.someoneCanCapture(clone.getKing(toMove.isWhite()), !toMove.isWhite())) {
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

    public boolean someoneCanCaptureNoKing(int r, int c, boolean color) {
        Square s = new Square(r, c);
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                Piece p = board[row][column];
                if (p != null && p.isWhite() == color && !(p instanceof King) && p.canCapture(s)) {
                    return true;
                }
            }
        }
        return false;
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
        str += "\nenPassant: " + enPassant;
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
    
    public void setEnpassant(Square square) {
        this.enPassant = square;
    }

    public short getHalfMoves() {
        return halfMoves;
    }

}