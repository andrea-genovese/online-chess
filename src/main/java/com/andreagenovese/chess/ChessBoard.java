package main.java.com.andreagenovese.chess;

import java.util.Set;

public class ChessBoard {
    private Piece[][] board;
    private boolean isWhiteTurn,
            whiteKingCastling,
            whiteQueenCastling,
            blackKingCastling,
            blackQueenCastling;
    private short halfMoves, moves;

    public Piece[][] getBoard() {
        return board;
    }

    public ChessBoard(String fen) {
        String[] arr = fen.split(" ");
        String position = arr[0],
                turn = arr[1],
                castling = arr[2],
                enPassant = arr[3],
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

    }

    public ChessBoard() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
        str+="-------------------\n  | a b c d e f g h";
        return str;

    }

    public static void main(String[] args) {
        ChessBoard c = new ChessBoard("r1bq1b1r/ppp4p/4k1p1/3np3/1nB5/2N2Q2/PPPP1PPP/R1BK3R w - - 0 10");
        Set<Move> mosse = c.board[7][7].getRookMoves();
        System.out.println(c);
        System.out.println(mosse);
    }
}