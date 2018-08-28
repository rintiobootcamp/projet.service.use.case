package com.bootcamp.output;

public class SondagePrime {
    private int id;
    private String entityType;
    private int entityId;
    private String sujet;
    private String cover;
    private boolean une;
    private String entityTypelabel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isUne() {
        return une;
    }

    public void setUne(boolean une) {
        this.une = une;
    }

    public String getEntityTypelabel() {
        return entityTypelabel;
    }

    public void setEntityTypelabel(String entityTypelabel) {
        this.entityTypelabel = entityTypelabel;
    }
}
