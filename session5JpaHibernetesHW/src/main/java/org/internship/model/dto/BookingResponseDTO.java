//For responses (includes aggregated data like flight details and user info)

package org.internship.model.dto;

import org.internship.model.enums.RoleEnum;

public class BookingResponseDTO {
    private Long id;
    private String username;
    private RoleEnum role;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long id, String username, RoleEnum role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "BookingResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
