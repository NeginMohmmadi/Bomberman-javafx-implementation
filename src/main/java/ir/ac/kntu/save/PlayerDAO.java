package ir.ac.kntu.save;

import ir.ac.kntu.gameObject.Player;

import java.util.ArrayList;
import java.util.List;

public interface PlayerDAO {
    ArrayList<Player> getAllPlayers();
    void saveAllPlayers(ArrayList<Player> list);
}