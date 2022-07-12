package com.andreagenovese.chess;

import java.util.UUID;

import com.andreagenovese.chess.Moves.Move;

public class Game {
    private ChessBoard position;
    private UUID id, white, black;
    private boolean whiteConnected, blackConnected;

    public Game(ChessBoard position, UUID id) {
        this.position = position;
        this.id = id;
    }
    public boolean bothConnected(){
        return whiteConnected && blackConnected;
    }
    public void connect(UUID player) {
        if (player.equals(white)) {
            whiteConnected = true;
            return;
        } else if (player.equals(black)) {
            blackConnected = true;
        } else
            setMissingPlayer(player);
    }

    public boolean execute(Move m) {
        ChessBoard c = position.execute(m);
        if (c == null)
            return false;
        position = c;
        return true;
    }

    public ChessBoard getPosition() {
        return position;
    }

    public void setPosition(ChessBoard position) {
        this.position = position;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWhite() {
        return white;
    }

    public void setWhite(UUID white) {
        this.white = white;
    }

    public UUID getBlack() {
        return black;
    }

    public void setBlack(UUID black) {
        this.black = black;
    }

    public boolean areBothPlayerConnected() {
        return black != null && white != null;
    }

    public void setMissingPlayer(UUID playerId) {
        if (black == null) {
            black = playerId;
            blackConnected = true;
        } else if (white == null) {
            white = playerId;
            whiteConnected = true;
        }
    }
}
