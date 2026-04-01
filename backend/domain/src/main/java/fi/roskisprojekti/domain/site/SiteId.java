package fi.roskisprojekti.domain.site;

public record SiteId(Long value) {
    public SiteId{
        if (value == null || value <=0){ //??
            throw new IllegalArgumentException("Invalid site id");
        }
    }
}
