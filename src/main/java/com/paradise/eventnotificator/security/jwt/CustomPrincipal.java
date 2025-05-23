package com.paradise.eventnotificator.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CustomPrincipal implements Serializable {

    private final Long id;

    private final String login;

    @Override
    public String toString() {
        return "CustomPrincipal{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
