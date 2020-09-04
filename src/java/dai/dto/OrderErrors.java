/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.dto;

import java.io.Serializable;

/**
 *
 * @author AD
 */
public class OrderErrors implements Serializable{
    private String fullnameLenghtErr;
    private String addressLenghtErr;
    private String phoneLenghtErr;
    private String phoneFormatErr;

    public String getFullnameLenghtErr() {
        return fullnameLenghtErr;
    }

    public void setFullnameLenghtErr(String fullnameLenghtErr) {
        this.fullnameLenghtErr = fullnameLenghtErr;
    }

    public String getAddressLenghtErr() {
        return addressLenghtErr;
    }

    public void setAddressLenghtErr(String addressLenghtErr) {
        this.addressLenghtErr = addressLenghtErr;
    }

    public String getPhoneLenghtErr() {
        return phoneLenghtErr;
    }

    public void setPhoneLenghtErr(String phoneLenghtErr) {
        this.phoneLenghtErr = phoneLenghtErr;
    }

    public String getPhoneFormatErr() {
        return phoneFormatErr;
    }

    public void setPhoneFormatErr(String phoneFormatErr) {
        this.phoneFormatErr = phoneFormatErr;
    }
    
}
