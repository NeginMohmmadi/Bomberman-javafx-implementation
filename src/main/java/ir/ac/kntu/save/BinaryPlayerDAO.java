package ir.ac.kntu.save;

import ir.ac.kntu.gameObject.Player;
import ir.ac.kntu.gameObject.PlayerInfo;

import java.io.*;
import java.util.ArrayList;

public class BinaryPlayerDAO implements PlayerDAO{
    @Override
    public ArrayList<PlayerInfo> getAllPlayers(){
        File file=new File("src/main/resources/Players.txt");
        ArrayList<PlayerInfo> players=new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
            while(true) {
                try {
                    players.add((PlayerInfo) input.readObject());
                } catch(EOFException e){
                    break;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("No Such File found!!!!!");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Nothing exists in file!!!!!");
        }
        return players;
    }

    @Override
    public void saveAllPlayers(ArrayList<PlayerInfo> list) {
        ArrayList<PlayerInfo> players=getAllPlayers();
        for (int i=0;i<players.size();i++){
            if(list.contains(players.get(i))){
                players.remove(i);
            }
        }
        players.addAll(list);
        File file=new File("src/main/resources/Players.txt");
        try(FileOutputStream fileOutputStream=new FileOutputStream(file,false);
            ObjectOutputStream output=new ObjectOutputStream(fileOutputStream)){
            for(PlayerInfo player:players){
                output.writeObject(player);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
