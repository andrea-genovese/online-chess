package main.java.com.andreagenovese.chess;

public record Mossa(
                Square start,
                Square dest,
                Class<? extends Piece> promotion) {
        public Mossa(byte startRow, byte startColumn, byte destRow, byte destColumn) {
                this(new Square(startRow, startColumn), new Square(destRow, destColumn), null);
        }
}