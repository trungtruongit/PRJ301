/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscar.users;

import java.io.Serializable;

/**
 *
 * @author Oscar
 */
public class UserDTO implements Serializable{
    
    private int customerId;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String country;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int customerId, String email, String password, String fullName, String address, String country, String role) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.country = country;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
