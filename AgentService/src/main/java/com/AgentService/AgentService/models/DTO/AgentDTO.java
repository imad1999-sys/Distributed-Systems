package com.AgentService.AgentService.models.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AgentDTO {
    @NotNull(message = "name is required")
    private String name;
}
