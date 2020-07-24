package ir.ac.kntu.save;

import ir.ac.kntu.gameObject.Player;

import java.io.*;
import java.util.ArrayList;

public class BinaryPlayerDAO implements PlayerDAO{
    @Override
    public ArrayList<Player> getAllPlayers(){
        File file=new File("src/main/resources/Players.txt");
        ArrayList<Player> players=new ArrayList<>();
        try(FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream input=new ObjectInputStream(fileInputStream)){
            players=(ArrayList<Player>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No Such File found!!!!!");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Nothing exists in file!!!!!");
        }
        return players;
    }

    @Override
    public void saveAllPlayers(ArrayList<Player> list) {
        File file=new File("src/main/resources/Players.txt");
        try(FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream output=new ObjectOutputStream(fileOutputStream)){
            output.writeObject(list);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
