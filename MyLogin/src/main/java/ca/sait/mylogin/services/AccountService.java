/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.mylogin.services;

import ca.sait.mylogin.models.User;
/**
 *
 * @author Scott
 */
public class AccountService {
    public User login(String username, String password){
        if(username.equals("abe") && password.equals("password")){
            return new User("abe",null);
        } else if(username.equals("barb") && password.equals("password")){
            return new User("barb",null);
        } else{
            return new User(null, null);
        }
    }
}
