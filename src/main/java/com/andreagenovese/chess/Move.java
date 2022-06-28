package main.java.com.andreagenovese.chess;

public record Move(
                Piece piece,
                Square dest,
                Class<? extends Piece> promotion) {
        public Move(Piece piece, int destRow, int destColumn) {
                this(piece, new Square(destRow, destColumn), null);
        }

        public String toString() {
                char column = (char) (dest.column() + 'a');
                int row = 8-dest.row();
                return piece.toString() + column + row;
        }
}