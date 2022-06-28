package main.java.com.andreagenovese.chess;

public class ChessBoard {
    private Piece[][] board;
    private boolean isWhiteTurn,
            whiteKingCastling,
            whiteQueenCastling,
            blackKingCastling,
            blackQueenCastling;
    private short halfMoves, moves;

    public boolean execute(Mossa m) {
        if (m == null)
            return false;

        return false;
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
        for (Piece[] row : board) {
            for (Piece piece : row) {
                str += piece == null ? "  " : piece + " ";
            }
            str += '\n';
        }
        return str;
    }

    public static void main(String[] args) {
        ChessBoard c = new ChessBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        System.out.println(c);
    }
}