package ir.ac.kntu.save;

import ir.ac.kntu.gameObject.PlayerInfo;

import java.util.ArrayList;

public interface PlayerDAO {
    ArrayList<PlayerInfo> getAllPlayers();
    void saveAllPlayers(ArrayList<PlayerInfo> list);
}