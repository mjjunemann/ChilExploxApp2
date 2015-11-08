/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilexploxapp.chilexploxapp;

import com.chilexploxapp.chilexploxapp.Backend.Address;
import java.util.ArrayList;
import org.parse4j.ParseException;
import org.parse4j.ParseQuery;

/**
 *
 * @author matia
 */
public class RetrieveDataBase 
{
    public static ArrayList<Address> retrieveAddress() throws ParseException
    {
        ParseQuery<Address> query = ParseQuery.getQuery(Address.class);
        ArrayList<Address> addrs = (ArrayList<Address>) query.find();
        addrs.stream().forEach((addr) -> {
            addr.setVisualizable();
        });
        return addrs;
        
    }
}
