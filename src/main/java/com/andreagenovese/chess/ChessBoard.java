package main.java.com.andreagenovese.chess;

public class ChessBoard {
    private Piece[][] board;
    private boolean isWhiteTurn,
            whiteKingCastling,
            whiteQueenCastling,
            blackKingCastling,
            blackQueenCastling;
    private short halfMoves, moves;

    public ChessBoard(String fen) {
        String[] arr = fen.split(" ");
        String position = arr[0],
                turn = arr[1],
                castling = arr[2],
                enPassant = arr[3],
                halfMoves = arr[4],
                moves = arr[5];
        this.board = boardFromString(position);

    }

    private static Piece[][] boardFromString(String position) {
        String[] rows = position.split("/");
        Piece[][] board = new Piece[8][];
        if (rows.length != 8) {
            throw new IllegalArgumentException("Wrong number of rows");
        }
        for (int i = 0; i < rows.length; i++) {
            board[i] = rowFromString(rows[i]);
        }
        return board;
    }

    private static Piece[] rowFromString(String rowStr) {
        Piece[] row = new Piece[8];
        for (int i = 0; i < rowStr.length(); i++) {
            char c = rowStr.charAt(i);
            if (Character.isDigit(c)) {
                i += c - '0' - 1;
                continue;
            }
            row[i] = Piece.fromChar(c);
        }
        return row;
    }

    @Override
    public String toString() {
        String str = "";
        for (Piece[] row : board) {
            for (Piece piece : row) {
                str += piece + " ";
            }
            str += '\n';
        }
        return str;
    }
}