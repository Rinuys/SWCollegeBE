package com.example.model.constant;

import lombok.Getter;

public enum TeamSearchType {
    NAME("팀 이름"),
    MASCOT("마스코트"),
    LOCATION("장소");

    @Getter
    private final String description;

    TeamSearchType(String description) {
        this.description = description;
    }

}