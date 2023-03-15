package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class FacilitiesType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FacTypeID;
    private String FacName;
	private String FacType;
    public Integer getFacTypeID() {
        return FacTypeID;
    }
    public void setFacTypeID(Integer facTypeID) {
        FacTypeID = facTypeID;
    }
    public String getFacName() {
        return FacName;
    }
    public void setFacName(String facName) {
        FacName = facName;
    }
    public String getFacType() {
        return FacType;
    }
    public void setFacType(String facType) {
        FacType = facType;
    }

}
