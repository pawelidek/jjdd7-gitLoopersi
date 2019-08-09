package com.infoshareacademy.jjdd7.serialization_deserialization;

import com.infoshareacademy.jjdd7.teams_support.Teams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationImpl implements Deserialization {
    @Override
    public Teams deserialize() {
        Teams teams = new Teams();
        try {
            File file = new File("teams.ser");
            FileInputStream fileInputStream = new FileInputStream(teams.getFileName());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            teams = (Teams) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error with deserialization - IOE");
        } catch (IOException e) {
            System.out.println("Error with deserialization - Class not found");
        }
        return teams;
    }
}
