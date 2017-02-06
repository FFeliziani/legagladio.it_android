package com.federicofeliziani.legagladio.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f3l1x on 10/14/2016.
 */

public class Team implements Serializable, Parcelable {
    private int Id;

    private List<Player> ListPlayer;
    private Race Race;
    private int Value;
    private String Name;

    private Boolean Active;
    private int FunFactor;
    private int Reroll;
    private boolean HasMedic;
    private int Cheerleader;
    private int AssistantCoach;
    private String coachName;



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public com.federicofeliziani.legagladio.entities.Race getRace() {
        return Race;
    }

    public void setRace(com.federicofeliziani.legagladio.entities.Race race) {
        Race = race;
    }

    public List<Player> getListPlayer() {
        return ListPlayer;
    }

    public void setListPlayer(List<Player> listPlayer) {
        ListPlayer = listPlayer;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        this.Active = active;
    }

    public int getFunFactor() {
        return FunFactor;
    }

    public void setFunFactor(int funFactor) {
        FunFactor = funFactor;
    }

    public int getReroll() {
        return Reroll;
    }

    public void setReroll(int reroll) {
        Reroll = reroll;
    }

    public boolean isHasMedic() {
        return HasMedic;
    }

    public void setHasMedic(boolean hasMedic) {
        HasMedic = hasMedic;
    }

    public int getCheerleader() {
        return Cheerleader;
    }

    public void setCheerleader(int cheerleader) {
        Cheerleader = cheerleader;
    }

    public int getAssistantCoach() {
        return AssistantCoach;
    }

    public void setAssistantCoach(int assistantCoach) {
        AssistantCoach = assistantCoach;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeTypedList(this.ListPlayer);
        dest.writeParcelable(this.Race, flags);
        dest.writeInt(this.Value);
        dest.writeString(this.Name);
        dest.writeValue(this.Active);
        dest.writeInt(this.FunFactor);
        dest.writeInt(this.Reroll);
        dest.writeByte(this.HasMedic ? (byte) 1 : (byte) 0);
        dest.writeInt(this.Cheerleader);
        dest.writeInt(this.AssistantCoach);
        dest.writeString(this.coachName);
    }

    public Team() {
    }

    protected Team(Parcel in) {
        this.Id = in.readInt();
        this.ListPlayer = in.createTypedArrayList(Player.CREATOR);
        this.Race = in.readParcelable(com.federicofeliziani.legagladio.entities.Race.class.getClassLoader());
        this.Value = in.readInt();
        this.Name = in.readString();
        this.Active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.FunFactor = in.readInt();
        this.Reroll = in.readInt();
        this.HasMedic = in.readByte() != 0;
        this.Cheerleader = in.readInt();
        this.AssistantCoach = in.readInt();
        this.coachName = in.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
