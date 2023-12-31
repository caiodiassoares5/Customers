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

    public Customer setNameString(String nameString) {
        this.nameString = nameString;
        return this;
    }

    public String getAddressString() {
        return addressString;
    }

    public Customer setAddressString(String addressString) {
        this.addressString = addressString;
        return this;
    }

    public String getMarketSegmentString() {
        return marketSegmentString;
    }

    public Customer setMarketSegmentString(String marketSegmentString) {
        this.marketSegmentString = marketSegmentString;
        return this;
    }

    public Integer getZipCodeInteger() {
        return zipCodeInteger;
    }

    public Customer setZipCodeInteger(Integer zipCodeInteger) {
        this.zipCodeInteger = zipCodeInteger;
        return this;
    }

    public int getIsActiveInteger() {
        return isActiveInteger;
    }

    public Customer setIsActiveChar(int isActiveInteger) {
        this.isActiveInteger = isActiveInteger;
        return this;
    }

    public String getModifiedByString() {
        return modifiedByString;
    }

    public Customer setModifiedByString(String modifiedByString) {
        this.modifiedByString = modifiedByString;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Customer setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    @Override
    public String toString() {
        return "Customer [idInteger=" + idInteger + ", nameString=" + nameString + ", addressString=" + addressString
                + ", marketSegmentString=" + marketSegmentString + ", zipCodeInteger=" + zipCodeInteger
                + ", isActiveInteger=" + isActiveInteger + ", createdByString=" + createdByString + ", createdDate="
                + createdDate + ", modifiedByString=" + modifiedByString + ", modifiedDate=" + modifiedDate + "]\n";
    }

    


}
