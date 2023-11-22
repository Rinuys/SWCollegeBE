package com.example.model.constant;

import lombok.Getter;

public enum MemberSearchType {
    FIRSTNAME("이름"),
    LASTNAME("성"),
    ADDRESS("주소"),
    TEAM("팀 ID");

    @Getter
    private final String description;

    MemberSearchType(String description) {
        this.description = description;
    }

}