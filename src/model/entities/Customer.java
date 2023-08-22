package model.entities;

import java.sql.Date;

public class Customer {
    
    private Integer idInteger;
    private String nameString;
    private String addressString;
    private String marketSegmentString;
    private Integer zipCodeInteger;
    private Integer isActiveInteger;
    private String createdByString;
    private Date createdDate;
    private String modifiedByString;
    private Date modifiedDate;

    public Customer() {

    }

    public Customer(int id, 
        String name, 
        String address, 
        String markedSegment, 
        int zipCode, 
        int isActive,
        String createdBy,
        Date createdDate,
        String modifiedBy,
        Date modifiedDate  )
         {
            idInteger = id;
            nameString = name;
            marketSegmentString = markedSegment;
            addressString = address;
            zipCodeInteger = zipCode;
            isActiveInteger = isActive;
            createdByString = createdBy;
            this.createdDate = createdDate;
            modifiedByString = modifiedBy;
            this.modifiedDate = modifiedDate;
    }



    
    public Integer getIdInteger() {
        return idInteger;
    }

    public String getCreatedByString() {
        return createdByString;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getMarketSegmentString() {
        return marketSegmentString;
    }

    public void setMarketSegmentString(String marketSegmentString) {
        this.marketSegmentString = marketSegmentString;
    }

    public Integer getZipCodeInteger() {
        return zipCodeInteger;
    }

    public void setZipCodeInteger(Integer zipCodeInteger) {
        this.zipCodeInteger = zipCodeInteger;
    }

    public int getIsActiveInteger() {
        return isActiveInteger;
    }

    public void setIsActiveChar(int isActiveInteger) {
        this.isActiveInteger = isActiveInteger;
    }

    public String getModifiedByString() {
        return modifiedByString;
    }

    public void setModifiedByString(String modifiedByString) {
        this.modifiedByString = modifiedByString;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    


}
