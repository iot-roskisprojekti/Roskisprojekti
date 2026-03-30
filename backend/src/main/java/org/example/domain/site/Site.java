package org.example.domain.site;

public class Site {
    private final SiteId siteId;
    private final String name;
    private final Location location;


    public Site(SiteId siteId, String name, Location location){
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }


    //GETTERIT

      public SiteId getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
    
}
