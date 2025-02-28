//For registration (includes username, password, role)

package org.internship.model.dto;

import org.internship.model.enums.RoleEnum;

public class UserCreateDTO {
    private String username;
    private String password;
    private RoleEnum role;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
