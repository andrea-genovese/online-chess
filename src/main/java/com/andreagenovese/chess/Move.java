package main.java.com.andreagenovese.chess;

public record Move(
                Piece piece,
                Square dest,
                Class<? extends Piece> promotion) {
        public Move(Piece piece, byte destRow, byte destColumn) {
                this(piece, new Square(destRow, destColumn), null);
        }

        public String toString() {
                char column = (char) (dest.column() + 'a');
                int row = dest.row()+1;
                return piece.toString() + column + row;
        }
}