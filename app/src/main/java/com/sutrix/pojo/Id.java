package com.sutrix.pojo;

import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
