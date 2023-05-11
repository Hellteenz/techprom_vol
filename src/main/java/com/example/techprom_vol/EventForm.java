package com.example.techprom_vol;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventForm {
    private String eventName;
    private int minAge;
    private int firstStaff;
    private int secondStaff;
    private String information;

    private StringProperty t_eventName;
    private IntegerProperty t_minAge;
    private StringProperty t_info;
    private IntegerProperty t_fStaff;
    private IntegerProperty t_sStaff;

    public EventForm() {

    }

    public EventForm(String name, int minAge, String information, int first, int second) {
        this.t_eventName = new SimpleStringProperty(name);
        this.t_minAge = new SimpleIntegerProperty(minAge);
        this.t_info = new SimpleStringProperty(information);
        this.t_fStaff = new SimpleIntegerProperty(first);
        this.t_sStaff = new SimpleIntegerProperty(second);
    }

    public String getT_eventName() {
        return t_eventName.get();
    }

    public StringProperty t_eventNameProperty() {
        return t_eventName;
    }

    public void setT_eventName(String t_eventName) {
        this.t_eventName.set(t_eventName);
    }

    public int getT_minAge() {
        return t_minAge.get();
    }

    public IntegerProperty t_minAgeProperty() {
        return t_minAge;
    }

    public void setT_minAge(int t_minAge) {
        this.t_minAge.set(t_minAge);
    }

    public String getT_info() {
        return t_info.get();
    }

    public StringProperty t_infoProperty() {
        return t_info;
    }

    public void setT_info(String t_info) {
        this.t_info.set(t_info);
    }

    public int getT_fStaff() {
        return t_fStaff.get();
    }

    public IntegerProperty t_fStaffProperty() {
        return t_fStaff;
    }

    public void setT_fStaff(int t_fStaff) {
        this.t_fStaff.set(t_fStaff);
    }

    public int getT_sStaff() {
        return t_sStaff.get();
    }

    public IntegerProperty t_sStaffProperty() {
        return t_sStaff;
    }

    public void setT_sStaff(int t_sStaff) {
        this.t_sStaff.set(t_sStaff);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getFirstStaff() {
        return firstStaff;
    }

    public void setFirstStaff(int firstStaff) {
        this.firstStaff = firstStaff;
    }

    public int getSecondStaff() {
        return secondStaff;
    }

    public void setSecondStaff(int secondStaff) {
        this.secondStaff = secondStaff;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
