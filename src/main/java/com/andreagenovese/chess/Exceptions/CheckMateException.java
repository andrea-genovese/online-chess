package com.andreagenovese.chess.Exceptions;

public class CheckMateException extends Exception{
    private boolean winner;
    public CheckMateException(boolean winner) {
        this.winner = winner;
    }
    public boolean getWinner(){
        return winner;
    }

}
