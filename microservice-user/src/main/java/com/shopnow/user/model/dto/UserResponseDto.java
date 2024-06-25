package com.shopnow.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "created", "modified", "last_login", "token", "isactive"})
public class UserResponseDto {

    @JsonProperty("id")
    @NotBlank
    private UUID id;

    @JsonProperty("created")
    @NotBlank
    private LocalDate created;

    @JsonProperty("modified")
    @NotBlank
    private LocalDate modified;

    @JsonProperty("last_login")
    @NotBlank
    private LocalDate lastLogin;

    @JsonProperty("token")
    @NotBlank
    private String token;

    @JsonProperty("isactive")
    @NotBlank
    private boolean isActive;
}
