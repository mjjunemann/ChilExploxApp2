/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilexploxapp.chilexploxapp.Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.parse4j.ParseObject;

/**
 *
 * @author matia
 */
public class Subsidiary extends ParseObject{
    
    public Address subsidiary_address;
    private Map<String,Order> orders;
    //private Mailbox mailbox;
    //private Map<String,ITransport> transport;
    //private ArrayList<ITransport> arrived;
    //private Map<String,Client> clients;
    //private NotificationCenter notification_center;
    private String subsidiaryId;
    private int orderIdCounter = 1000;
    
    public Subsidiary()
    {
        //
    }
    public Subsidiary(Address addr, String id)
    {
        this.subsidiaryId = id;
        this.subsidiary_address = addr;
//        this.mailbox = new Mailbox();
//        this.mailbox.setSubsidaryAddress(addr);
        this.orders = new HashMap();
//        this.clients = new HashMap();
//        this.transport = new HashMap();
//        this.arrived = new ArrayList<>(); 
        //this.notification_center = new NotificationCenter();
    }
    
    
    //<editor-fold desc="getters">
    //</editor-fold>
    
    //<editor-fold desc="parseGetters">
    
    
    //</editor-fold> 
}
