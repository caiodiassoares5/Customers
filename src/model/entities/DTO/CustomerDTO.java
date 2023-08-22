package model.entities.DTO;

import java.sql.Date;

public class CustomerDTO {
    
    public Integer idInteger;
    public String nameString;
    public String addressString;
    public String marketSegmentString;
    public Integer zipCodeInteger;
    public Integer isActiveInteger;
    public String createdByString;
    public Date createdDate;
    public String modifiedByString;
    public Date modifiedDate;


    public CustomerDTO() {

    }

    public CustomerDTO withIdInteger (int id) {
        this.idInteger = id;
        return this;
    }

    public CustomerDTO withNameString(String nameString) {
        this.nameString = nameString;
        return this;
    }
    public CustomerDTO withAddressString(String addressString) {
        this.addressString = addressString;
        return this;
    }
    public CustomerDTO withMarketSegmentString(String marketSegmentString) {
        this.marketSegmentString = marketSegmentString;
        return this;
    }
    public CustomerDTO withZipCodeInteger(Integer zipCodeInteger) {
        this.zipCodeInteger = zipCodeInteger;
        return this;
    }
    public CustomerDTO withIsActiveInteger(Integer isActiveInteger) {
        this.isActiveInteger = isActiveInteger;
        return this;
    }
    public CustomerDTO withCreatedByString(String createdByString) {
        this.createdByString = createdByString;
        return this;
    }
    public CustomerDTO withCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }
    public CustomerDTO withModifiedByString(String modifiedByString) {
        this.modifiedByString = modifiedByString;
        return this;
    }
    public CustomerDTO withModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }


    

}
