package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Team;

import java.io.*;
import java.util.List;

public class TeamSerializatorImpl implements TeamSerializator {
    @Override
    public void serialize(List<Team> teamList, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(teamList);
        } catch (Exception ex) {
            System.out.println("Serialization has failed, please check output data");
        }
    }
}