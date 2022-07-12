package com.andreagenovese.chess;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.andreagenovese.chess.Moves.Move;

@Controller
public class WSGameManager {
    @MessageMapping("/showUp/{gameId}")
    @SendTo("/game/{gameId}")
    public boolean showUp(@DestinationVariable String gameId, String userId){
        Game g = ChessApplication.games.get(UUID.fromString(gameId));
        g.connect(UUID.fromString(userId));
        
        System.out.println(userId+" showed up at game "+gameId);
        return true;
    }

    @MessageMapping("/playMove/{gameId}")
    @SendTo("/game/{gameId}")
    public boolean play(@DestinationVariable String gameId) {
        Game g = ChessApplication.games.get(UUID.fromString(gameId));
        System.out.println("New move on game "+gameId);
        return g.execute(new Move("e2", "e4"));
    }
}
