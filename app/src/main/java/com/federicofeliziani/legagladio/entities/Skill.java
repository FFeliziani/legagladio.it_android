package com.federicofeliziani.legagladio.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by f3l1x on 10/14/2016.
 */

public class Skill implements Parcelable, Serializable {
    private int Id;
    private String Name;
    private SkillType SkillType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public com.federicofeliziani.legagladio.entities.SkillType getSkillType() {
        return SkillType;
    }

    public void setSkillType(com.federicofeliziani.legagladio.entities.SkillType skillType) {
        SkillType = skillType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.Name);
        dest.writeInt(this.SkillType == null ? -1 : this.SkillType.ordinal());
    }

    public Skill() {
    }

    protected Skill(Parcel in) {
        this.Id = in.readInt();
        this.Name = in.readString();
        int tmpSkillType = in.readInt();
        this.SkillType = tmpSkillType == -1 ? null : com.federicofeliziani.legagladio.entities.SkillType.values()[tmpSkillType];
    }

    public static final Parcelable.Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel source) {
            return new Skill(source);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };
}
