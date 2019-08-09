package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Team;

import java.io.*;
import java.util.List;

public class SerializationTeamImpl implements SerializationTeam {
    @Override
    public void serialize(List<Team> teamList, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(teamList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with serialization - FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Error with serialization - IOException");
        }
    }
}