package com.example.annuaire.model;
public class SpinnerItem {
    private int ID;
    private String displayName;

    public SpinnerItem(int ID, String displayName) {
        this.ID = ID;
        this.displayName = displayName;
    }

    public int getID() {
        return ID;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
