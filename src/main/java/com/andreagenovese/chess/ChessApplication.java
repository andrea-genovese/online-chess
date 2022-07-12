package com.andreagenovese.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplication {
	public static final Map<UUID,Game> games = new HashMap<>();
	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

}
