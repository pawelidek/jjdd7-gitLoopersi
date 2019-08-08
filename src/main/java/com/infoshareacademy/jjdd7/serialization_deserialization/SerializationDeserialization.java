package com.infoshareacademy.jjdd7.serialization_deserialization;

import com.infoshareacademy.jjdd7.teams_support.Teams;
import java.io.*;

public interface SerializationDeserialization {
    static void serialize(Teams teams) {
        FileOutputStream file = null;
        ObjectOutputStream out = null;
        try {
            file = new FileOutputStream(teams.getFileName());
            out = new ObjectOutputStream(file);
            out.writeObject(teams);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with serialization - FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Error with serialization - IOException");
        }
    }
    static Teams deserialize() {
        Teams teams = new Teams();
        try {
            File file = new File("teams.ser");
            if (file.isFile()&& file.canRead()) {
                FileInputStream fileInputStream = new FileInputStream(teams.getFileName());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                teams = (Teams) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error with deserialization - IOE");
        } catch (IOException e) {
            System.out.println("Error with deserialization - Class not found");
        }
        return teams;
    }

}
