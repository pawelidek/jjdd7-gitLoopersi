package com.infoshareacademy.jjdd7.serialization_deserialization;

import com.infoshareacademy.jjdd7.teams_support.Teams;

import java.io.*;

public class SerializationImpl implements Serialization {
    @Override
    public void serialize(Teams teams) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(teams.getFileName());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(teams);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with serialization - FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Error with serialization - IOException");
        }
    }
}