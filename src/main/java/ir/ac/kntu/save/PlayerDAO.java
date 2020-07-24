package ir.ac.kntu.save;

import ir.ac.kntu.gameObject.Player;
import ir.ac.kntu.gameObject.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public interface PlayerDAO {
    ArrayList<PlayerInfo> getAllPlayers();
    void saveAllPlayers(ArrayList<PlayerInfo> list);
}