package com.infoshareacademy.jjdd7.serialization_deserialization;

import com.infoshareacademy.jjdd7.teams_support.Teams;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SerializationImpl implements Serialization {
    @Override
    public void serialize(Teams teams) {
        try {
            List<Object> params = openOutputStreams(teams);
            FileOutputStream fOS = (FileOutputStream) params.get(0);
            ObjectOutputStream oOS = (ObjectOutputStream) params.get(1);
            oOS.writeObject(teams);
            closeOutputStreams(fOS, oOS);
        } catch (FileNotFoundException e) {
            System.out.println("Error with serialization - FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Error with serialization - IOException");
        }
    }

    private List<Object> openOutputStreams(Teams teams) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(teams.getFileName());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        return Arrays.asList(fileOutputStream, objectOutputStream);
    }

    private void closeOutputStreams(FileOutputStream fileOutputStream, ObjectOutputStream objectOutputStream) throws IOException {
        fileOutputStream.close();
        objectOutputStream.close();
    }
}
