package org.example.domain.site;

public record SiteId(Long value) {
    public SiteId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Site id must be positive");
        }
    }
}