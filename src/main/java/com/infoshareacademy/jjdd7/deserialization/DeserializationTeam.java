package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Team;

import java.util.List;

public interface DeserializationTeam {
    List<Team> deserialize(String fileName);
}
