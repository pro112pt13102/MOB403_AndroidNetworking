package com.sutrix.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Id implements Serializable{

    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
