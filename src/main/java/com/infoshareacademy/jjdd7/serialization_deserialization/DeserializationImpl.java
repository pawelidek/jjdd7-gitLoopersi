package com.infoshareacademy.jjdd7.serialization_deserialization;

import com.infoshareacademy.jjdd7.teams_support.Teams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;

public class DeserializationImpl implements Deserialization {
    @Override
    public Teams deserialize() {
        Teams teams = new Teams();
        try {
            File file = new File("teams.ser");
            if (file.isFile() && file.canRead()) {
                List<Object> params = openInputStreams(teams);
                FileInputStream fIS = (FileInputStream) params.get(0);
                ObjectInputStream oIS = (ObjectInputStream) params.get(1);
                teams = (Teams) oIS.readObject();
                closeInputStreams(fIS, oIS);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error with deserialization - IOE");
        } catch (IOException e) {
            System.out.println("Error with deserialization - Class not found");
        }
        return teams;
    }

    private List<Object> openInputStreams(Teams teams) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(teams.getFileName());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return Arrays.asList(fileInputStream, objectInputStream);
    }

    private void closeInputStreams(FileInputStream fileInputStream, ObjectInputStream objectInputStream) throws IOException {
        fileInputStream.close();
        objectInputStream.close();
    }

}
