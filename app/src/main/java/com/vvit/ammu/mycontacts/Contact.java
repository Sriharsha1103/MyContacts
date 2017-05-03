package com.vvit.ammu.mycontacts;

/**
 * Created by Ammu on 03-05-2017.
 */

public class Contact {

    private String contactName;
    private String contactNumber;

    public Contact(String contactName, String contactNumber){

        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
