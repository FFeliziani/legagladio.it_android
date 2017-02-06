package com.federicofeliziani.legagladio.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f3l1x on 10/14/2016.
 */

public class Player implements Parcelable, Serializable {
    private int Id;

    private int Ma;
    private int Ag;
    private int Av;
    private int St;
    private float Cost;
    private String Name;
    private int Spp;

    private List<Skill> ListAbility;

    private int Td;
    private int Cas;
    private int Pass;
    private int Cat;

    private int Niggling;

    private Boolean MissNextGame;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public int getAg() {
        return Ag;
    }

    public void setAg(int ag) {
        Ag = ag;
    }

    public int getAv() {
        return Av;
    }

    public void setAv(int av) {
        Av = av;
    }

    public int getSt() {
        return St;
    }

    public void setSt(int st) {
        St = st;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float cost) {
        Cost = cost;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSpp() {
        return Spp;
    }

    public void setSpp(int spp) {
        Spp = spp;
    }

    public List<Skill> getListAbility() {
        return ListAbility;
    }

    public void setListAbility(List<Skill> listAbility) {
        ListAbility = listAbility;
    }

    public int getTd() {
        return Td;
    }

    public void setTd(int td) {
        Td = td;
    }

    public int getCas() {
        return Cas;
    }

    public void setCas(int cas) {
        Cas = cas;
    }

    public int getPass() {
        return Pass;
    }

    public void setPass(int pass) {
        Pass = pass;
    }

    public int getCat() {
        return Cat;
    }

    public void setCat(int cat) {
        Cat = cat;
    }

    public int getNiggling() {
        return Niggling;
    }

    public void setNiggling(int niggling) {
        Niggling = niggling;
    }

    public Boolean getMissNextGame() {
        return MissNextGame;
    }

    public void setMissNextGame(Boolean missNextGame) {
        MissNextGame = missNextGame;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.Ma);
        dest.writeInt(this.Ag);
        dest.writeInt(this.Av);
        dest.writeInt(this.St);
        dest.writeFloat(this.Cost);
        dest.writeString(this.Name);
        dest.writeInt(this.Spp);
        dest.writeTypedList(this.ListAbility);
        dest.writeInt(this.Td);
        dest.writeInt(this.Cas);
        dest.writeInt(this.Pass);
        dest.writeInt(this.Cat);
        dest.writeInt(this.Niggling);
        dest.writeValue(this.MissNextGame);
    }

    public Player() {
    }

    protected Player(Parcel in) {
        this.Id = in.readInt();
        this.Ma = in.readInt();
        this.Ag = in.readInt();
        this.Av = in.readInt();
        this.St = in.readInt();
        this.Cost = in.readFloat();
        this.Name = in.readString();
        this.Spp = in.readInt();
        this.ListAbility = in.createTypedArrayList(Skill.CREATOR);
        this.Td = in.readInt();
        this.Cas = in.readInt();
        this.Pass = in.readInt();
        this.Cat = in.readInt();
        this.Niggling = in.readInt();
        this.MissNextGame = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
