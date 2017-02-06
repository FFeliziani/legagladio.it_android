package com.federicofeliziani.legagladio.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f3l1x on 10/14/2016.
 */

public class Coach implements Serializable, Parcelable {
    private int id;

    private List<Team> ListTeam;
    private String Name;
    private int Value;
    private String NafID;

    private Boolean Active;
    private String NafNick;

    private String Notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Team> getListTeam() {
        return ListTeam;
    }

    public void setListTeam(List<Team> listTeam) {
        ListTeam = listTeam;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getNafID() {
        return NafID;
    }

    public void setNafID(String nafID) {
        NafID = nafID;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public String getNafNick() {
        return NafNick;
    }

    public void setNafNick(String nafNick) {
        NafNick = nafNick;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedList(this.ListTeam);
        dest.writeString(this.Name);
        dest.writeInt(this.Value);
        dest.writeString(this.NafID);
        dest.writeValue(this.Active);
        dest.writeString(this.NafNick);
        dest.writeString(this.Notes);
    }

    public Coach() {
    }

    protected Coach(Parcel in) {
        this.id = in.readInt();
        this.ListTeam = in.createTypedArrayList(Team.CREATOR);
        this.Name = in.readString();
        this.Value = in.readInt();
        this.NafID = in.readString();
        this.Active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.NafNick = in.readString();
        this.Notes = in.readString();
    }

    public static final Creator<Coach> CREATOR = new Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel source) {
            return new Coach(source);
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };
}
