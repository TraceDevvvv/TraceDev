package com.etour.agency;

import com.etour.banner.*;
import com.etour.restpoint.*;
import java.io.File;

/**
 * AgencyOperator - represents an operator who can log in, select rest points,
 * and insert banners.
 */
public class AgencyOperator {
    private String id;
    private String name;

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void login() {
        System.out.println("Operator " + name + " logged in.");
    }

    public void selectRestPoint(RestPoint restPoint) {
        System.out.println("Operator selected rest point: " + restPoint.getName());
    }

    // Calls BannerService to insert a banner for a rest point.
    public void insertBanner(File imageFile, BannerService service, String restPointId) {
        System.out.println("Operator inserting banner for rest point: " + restPointId);
        Banner banner = service.insertBanner(restPointId, imageFile);
        if (banner != null) {
            System.out.println("Banner created with ID: " + banner.getId());
        }
    }

    public void confirmInsertion() {
        System.out.println("Operator confirmed insertion.");
    }

    public void cancelOperation() {
        System.out.println("Operator cancelled the operation.");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}