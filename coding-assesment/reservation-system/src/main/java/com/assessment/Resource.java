package com.assessment;

import java.util.UUID;

public class Resource {
    private final UUID id;
    private final String name;
    private final ResourceType type;

    public Resource(UUID id, String name, ResourceType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ResourceType getType() {
        return type;
    }   
}
