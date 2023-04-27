package com.example.myapplication;

import com.example.game.GameMainActivity;
import com.example.game.LocalGame;
import com.example.game.config.GameConfig;
import com.example.game.config.GamePlayerType;
import com.example.game.GamePlayer;

import java.util.ArrayList;

public class MainActivity extends GameMainActivity {

    final public int PORT_NUMBER = 98898;

    @Override
    public GameConfig createDefaultConfig() {
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentHumanPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Dumb AI Player") {
            @Override
            public GamePlayer createPlayer(String name) {


                return new PresidentComputerPlayer(name);

            }
        });

        playerTypes.add(new GamePlayerType("Dumb AI Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentComputerPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Smart Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentComputerPlayer(name);
            }
        });

        GameConfig defaultConfig = new GameConfig(playerTypes,4,4,"President",PORT_NUMBER);
        defaultConfig.addPlayer("HumanPlayer",0);
        defaultConfig.addPlayer("DumbComputer",1);
        defaultConfig.addPlayer("SmartComputer",2);
        defaultConfig.addPlayer("SmartComputer",3);



        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {

        return new PresidentLocalGame();
    }
}