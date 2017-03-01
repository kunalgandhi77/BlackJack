/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */
package com.example.blackjack.rest;

import com.example.blackjack.game.Game;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GameController {

  private final Map<String, Game> games = new HashMap<>();

  // Get the current game
  @RequestMapping(method = RequestMethod.GET, value = "{tableId}")
  @CrossOrigin
  public Table get(@PathVariable String tableId) {
    Game game = getGame(tableId);
    Table table = new Table();
    table.setDealerHand(game.getDealerHand());
    table.setMyHand(game.getPlayerHand());
    table.setOptions(game.getOptions());
    table.setTitle("BlackJack: " + tableId);
    final String winner = game.getWinner();
    if (winner != null) {
      table.setMessage("Winner: " + winner);
    }
    return table;
  }

  // Hit
  @RequestMapping(method = RequestMethod.POST, value = "{tableId}/hit")
  @CrossOrigin
  public ResponseEntity add(@PathVariable String tableId) {
    Game game = getGame(tableId);
    game.hit();
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  // Stand
  @RequestMapping(method = RequestMethod.POST, value = "{tableId}/stand")
  @CrossOrigin
  public ResponseEntity update(@PathVariable String tableId) {
    Game game = getGame(tableId);
    game.stand();
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  // New Game
  @RequestMapping(method = RequestMethod.POST, value = "{tableId}/newgame")
  @CrossOrigin
  public ResponseEntity delete(@PathVariable String tableId) {
    games.put(tableId, new Game());
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  private Game getGame(String tableId) {
    Game game = games.get(tableId);
    if (game == null) {
      game = new Game();
      games.put(tableId, game);
    }
    return game;
  }

}
