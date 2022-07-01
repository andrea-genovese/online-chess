package com.andreagenovese.chess;

import java.util.Set;

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

    public boolean isExecutable(Move m) {
        Piece toMove = getPiece(m.start());
        // check turn
        if (toMove.isWhite() != isWhiteTurn) {
            return false;
        }
        // check if the move is possible
        if (!toMove.getMoves().contains(m)) {
            return false;
        }
        // check if after the move the king is capturable
        ChessBoard clone = clone();
        clone.execute(m);
        
        return false;
    }
    

    private void execute(Move m) {
        Piece p = getPiece(m.start());
    }
    public Piece getPiece(Square s){
        return board[s.row()][s.column()];
    }
    public Piece getPiece(int r, int c){
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

    private Piece getKing(boolean white) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j];
                if (p.getClass() == King.class && p.isWhite() == white) {
                    return p;
                }
            }
        }
        return null;
    }

    public boolean someoneCanCapture(Square s, boolean color) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                Piece p = board[row][column];
                if (p.isWhite() == color && p.canCapture(s)) {
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

    public static void main(String[] args) {
        ChessBoard c = new ChessBoard("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3");
        Set<Move> mosse = c.board[3][4].getMoves();

        System.out.println(c);
        System.out.println(mosse);
    }
}