package com.federicofeliziani.legagladio.entities;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by f3l1x on 10/14/2016.
 */

public enum SkillType {
    GENERAL(0),
    AGILITY(1),
    PASSING(2),
    STRENGTH(3),
    MUTATION(4),
    EXTRAORDINARY(5);

    private static final Map<Integer, SkillType> lookup = new HashMap<>();

    static {
        for(SkillType st : EnumSet.allOf(SkillType.class))
        {
            lookup.put(st.getCode(), st);
        }
    }

    private int code;

    private SkillType(int code)
    {
        this.code = code;
    }

    public int getCode() {return code;}

    public static SkillType get(int code){
        return lookup.get(code);
    }



}
