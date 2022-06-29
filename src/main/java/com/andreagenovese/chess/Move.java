package main.java.com.andreagenovese.chess;

public record Move(
                Piece piece,
                Square dest,
                Class<? extends Piece> promotion) {
        public Move(Piece piece, int destRow, int destColumn) {
                this(piece, new Square(destRow, destColumn), null);
        }

        public Move(Piece piece, int destRow, int destColumn, Class<? extends Piece> promotion) {
                this(piece, new Square(destRow, destColumn), promotion);
        }

        public String toString() {
                char column = (char) (dest.column() + 'a');
                int row = 8 - dest.row();
                String str = piece.toString() + column + row;
                if (promotion != null) {
                        str += "=" + promotion.getSimpleName();
                }
                return str;
        }
}