package ir.ac.kntu.map;

import ir.ac.kntu.gameObject.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapParser {
    private Map<Character, GameObjectCreator> mapTranslator;

    public MapParser(){
        mapTranslator=initializeTranslator();
    }

    private Map<Character, GameObjectCreator> initializeTranslator() {
        Map<Character, GameObjectCreator> mapTranslator=new HashMap<>();
        mapTranslator.put(' ',(row,column)->new Ground(row,column));
        mapTranslator.put('1',(row,column)->new Player(1,row,column));
        mapTranslator.put('2',(row,column)->new Player(2,row,column));
        mapTranslator.put('3',(row,column)->new Player(3,row,column));
        mapTranslator.put('4',(row,column)->new Player(4,row,column));
        mapTranslator.put('b',(row,column)->new Block(row,column));
        mapTranslator.put('p',(row,column)->new PowerUp(row,column));
        mapTranslator.put('w',(row,column)->new Wall(row,column));
        mapTranslator.put('d',(row,column)->new OneWay(Direction.DOWN,row,column));
        mapTranslator.put('u',(row,column)->new OneWay(Direction.UP,row,column));
        mapTranslator.put('r',(row,column)->new OneWay(Direction.RIGHT,row,column));
        mapTranslator.put('l',(row,column)->new OneWay(Direction.LEFT,row,column));
        return mapTranslator;
    }

    public List<GameObject> gameObjects(File file){
        List<GameObject> result = new SynchronizedArray<>();
        if (null == file || !file.isFile()) {
            throw new IllegalArgumentException("Supplied argument is not valid file: " + file);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            int rowIndex=-1;
            while ((line = br.readLine()) != null) {
                rowIndex++;
                for (int i = 0; i < line.length(); i++) {
                    result.add(mapTranslator.get(line.charAt(i)).create(rowIndex,i));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error while reading map file.", e);
        }
        return result;
    }
}
