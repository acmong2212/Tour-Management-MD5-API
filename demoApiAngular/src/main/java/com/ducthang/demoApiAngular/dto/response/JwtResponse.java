package com.ducthang.demoApiAngular.dto.response;

import com.ducthang.demoApiAngular.model.Users;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Users users;

    public JwtResponse() {
    }

    public JwtResponse(String token, Users users) {
        this.token = token;
        this.users = users;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
