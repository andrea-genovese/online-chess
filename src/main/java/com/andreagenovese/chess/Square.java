package com.andreagenovese.chess;

public record Square(int row, int column) {
    public static Square fromString(String s) {
        if (s.length() != 2)
            return null;

        int newCol = s.charAt(0) - 'a';
        int newRow = 7 - (s.charAt(1) - '0' - 1);
        return new Square(newRow, newCol);
    }
    public boolean doesExists(){
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            return false;
        }
        return true;
    }
    public String toString() {
        String str = "";
        str += (char) ('a' + column);
        return str + (7 - row + 1);
    }
}
