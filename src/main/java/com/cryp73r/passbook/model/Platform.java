package com.cryp73r.passbook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DEPOT_PLATFORM")
public class Platform {

    @Id
    @GeneratedValue
    @Column(name = "ROWID_PLATFORM")
    private Long id;

    @Column(name = "PLT_CODE", unique = true, nullable = false)
    private String pltCode;

    @Column(name = "PLT_DESC")
    private String pltDesc;

    public Platform(String pltCode) {
        this.pltCode = pltCode;
    }

    public Platform(String pltCode, String pltDesc) {
        this.pltCode = pltCode;
        this.pltDesc = pltDesc;
    }

    public Platform() {

    }

    public String getPltCode() {
        return pltCode;
    }

    public String getPltDesc() {
        return pltDesc;
    }

    public void setPltCode(String pltCode) {
        this.pltCode = pltCode;
    }

    public void setPltDesc(String pltDesc) {
        this.pltDesc = pltDesc;
    }
}
