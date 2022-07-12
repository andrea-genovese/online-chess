package com.andreagenovese.chess;

import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameManager {
    @PostMapping("/create")
    public UUID createGame(@RequestBody Map<String, String> body) {
        
        String black = body.get("black");
        String white = body.get("white");
        UUID gameId = UUID.randomUUID();
        Game g = new Game(new ChessBoard(ChessBoard.INITIAL_POSITION), gameId);
        if (black == null ^ white == null) {
            if (black == null) {
                g.setBlack(UUID.fromString(black));
                System.out.println("Game created by " + black);
            } else {
                g.setWhite(UUID.fromString(white));
                System.out.println("Game created by " + white);
            }
        } else {
            throw new RuntimeException();
        }
        ChessApplication.games.put(gameId, g);
        
        return gameId;
    }

}
