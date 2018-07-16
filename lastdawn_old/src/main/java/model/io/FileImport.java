/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.map.GameMap;
import model.map.Kingdom;
import model.map.Local;
import model.map.Road;

/**
 *
 * @author jpfr8
 */
public class FileImport {

    public static GameMap readLocalFile(String file) throws FileNotFoundException {

        GameMap list = new GameMap("game");

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = in.readLine()) != null) {

                String[] word = line.split(",");

                list.addLocal(new Local(word[0], Kingdom.KINGDOM1));
            
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static GameMap readRoadFile(String roadFile, String localFile) throws FileNotFoundException {

        GameMap localList = readLocalFile(localFile);
        try (BufferedReader in = new BufferedReader(new FileReader(roadFile))) {

            String line;

            while ((line = in.readLine()) != null) {

                String[] word = line.split(",");

                Local loc1 = null;
                Local loc2 = null;
                
                for (Local local : localList.getGraph().vertexSet()) {

                    if (local.getLocalName().equalsIgnoreCase(word[0])) {
                        loc1 = local;
                    }
                    if (local.getLocalName().equalsIgnoreCase(word[1])) {
                        loc2 = local;
                    }

                }

                Road road = new Road(Double.valueOf(word[2]));
                localList.addRoad(loc1, loc2, road);

            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("io found");
        }

        return localList;
    }
}
