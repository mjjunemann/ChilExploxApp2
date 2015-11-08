/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilexploxapp.chilexploxapp.Backend;

import Utilities.BudgetCalculator;
import com.chilexploxapp.chilexploxapp.Enums.State;
import com.chilexploxapp.chilexploxapp.Enums.Type;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.Observable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import org.parse4j.ParseObject;

/**
 *
 * @author matia
 */
public class Order extends ParseObject
{
    private transient SimpleObjectProperty<ArrayList<Parcel>> parcels;
    private transient ArrayList<Parcel> unsaved_parcels;
    private transient SimpleObjectProperty<Date> sales_date;
    private transient SimpleObjectProperty<Date> delivery_date;
    private boolean calculated;
    private transient SimpleFloatProperty total_price;
    private transient SimpleObjectProperty<Client> client;
    private transient SimpleObjectProperty<State> state;
    private transient SimpleStringProperty order_id;
    
    
    public Order()
    {
        //default constructor is needed
    }
    public Order(String id)
    {
        
    }
    public void setVisualizable()
    {
        
    }
    
    
    //<editor-fold desc="Methods">
    public void saveParcels()
    {
        setParcels((ArrayList<Parcel>) unsaved_parcels.clone());
    }
    
    public Parcel addParcel(Type type,float weight,float volume,int priority,Address origin,Address destination){
        this.calculated = false;
        //String id = getId() + String.valueOf(parcelIdCounter);
        //parcelIdCounter++;
        Parcel parcel = new Parcel(type,weight, volume, priority, origin, destination);
        this.getParcels().add(parcel);
        return parcel;
    }
    
    public boolean deleteParcel(Parcel p)
    {
        this.calculated = false;
        return this.getParcels().remove(p);
    }
    
    public float getTotal()
    {
        if (!this.calculated)
        {
            setTotal(BudgetCalculator.calculateTotal(getParcels()));
            this.calculated = true;
        }
        return getTotalValue();
    }
    
     public void updateStatus()
    {
        /*This method updates the status of the Order. Since an order is the sum
        of many parcels and each one can has a different status, this method 
        will follow the following logic:
        Orden is at Origin if and only if all Parcels are at Origin.
        Orden is Delivered if and only if all Parcels are Delivered.
        Orden is at Destination if and only if 
                            all Parcels are at Destination or Delivered.
        Otherwise, Order is on Transit.
        */
        Boolean origin = true;
        Boolean destination = true;
        Boolean delivered = true;
        for( Parcel p : this.getParcel()){
            State s = p.getState();
            if ( s == State.OnTransit){
                origin = false;
                delivered = false;
                destination = false;
                break;
            }
            else if (s == State.Destination){
                origin = false;
                delivered = false;
            }
            else if (s == State.Delivered){
                origin = false;
            }
            else{
                destination = false;
                delivered = false;
            }
        }
        if( !origin && !delivered && !destination ){
            setState(State.OnTransit);
        }else if (delivered){
            setState(State.Delivered);
        }else if (destination){
            setState(State.Destination);
        }else{
            setState(State.Origin);
        }
    }
     public void cancelSave() {
        //ArrayList<Parcel> tmpParcels = getParcel();
        this.unsaved_parcels = (ArrayList<Parcel>) this.getParcel().clone();
    }
     public int getPriority(){
        int max = this.getParcel().get(0).getPriority();
        int n;
        for( Parcel p : this.getParcel()){
            n = p.getPriority();
            if (n>max){
                max = n;
            }
        }
        return max;
    }
     //</editor-fold>
     
    //<editor-fold desc="Getters&Setters">
    
    public void setParcels(ArrayList<Parcel> parcels)  
    {
        parcelProperty().set(parcels);
    }
    
    public final void setClient(Client c)
    {
        clientProperty().set(c);
        put("client",c);
    }
    public final float getTotalValue()
    {
        return totalProperty().get();
    }
    public String getId(){
         return orderIdProperty().get();
    }
    public final void setTotal(float amount)
    {
        totalProperty().set(amount);
    }
    public void setId(String id)
    {
        orderIdProperty().set(id);
    }
    public final void setState(State s)
    {
        stateProperty().set(s);
    }
    public final State getState(){
        return (State) stateProperty().get();
    }
    public final void setDeliveryDate(Date delivery)
    {
        deliveryDateProperty().set(delivery);
    }
    public final Date getDeliveryDate()
    {
        return (Date) deliveryDateProperty().get();
    }
    public Date getSaleDate(){
        return (Date) saleDateProperty().get();
    }
    public void setSaleDate(Date date){
        saleDateProperty().set(date);
    }
    //</editor-fold>
    //<editor-fold desc="ParseSetter">
    public void setParcelsParse()
    {
        getParcel().stream().forEach((p) -> {
            p.put("order",this);
        });
    }
    //</editor-fold>
    //<editor-fold desc="getters">
    public ArrayList<Parcel> getParcel()
    {
        return (ArrayList<Parcel>) parcelProperty().get();
    }
    public final ArrayList<Parcel> getParcels()
    {
        return this.unsaved_parcels;
    }
    public Client getClient(){
        return (Client) clientProperty().get();
    }
    //</editor-fold>
    //<editor-fold desc="Properties">
    
    public ObjectProperty parcelProperty()
    {
        if (parcels == null)
        {
            parcels = new SimpleObjectProperty<>();
        }
        return parcels;
    }
    
    public ObjectProperty saleDateProperty()
    {
        if (sales_date == null)
        {
            sales_date = new SimpleObjectProperty<>();
        }
        return sales_date;
    }
    
    public ObjectProperty deliveryDateProperty()
    {
        if (delivery_date == null)
        {
            delivery_date = new SimpleObjectProperty<>();
        }
        return delivery_date;
    }
    public FloatProperty totalProperty()
    {
        if (total_price == null)
        {
            total_price = new SimpleFloatProperty();
        }
        return total_price;
    }
    public ObjectProperty clientProperty()
    {
        if (client == null)
        {
            client = new SimpleObjectProperty();
        }
        return client;
    }
    public ObjectProperty stateProperty()
    {
        if (state == null)
        {
            state = new SimpleObjectProperty();
        }
        return state;
    }
    public StringProperty orderIdProperty()
    {
        if (order_id == null)
        {
            order_id = new SimpleStringProperty();
        }
        return order_id;
    }
    //</editor-fold>
    
    
    
    public static  Callback<Order,Observable[]> extractor()
    {
        return (Order o) -> new Observable[]
        {
         o.saleDateProperty(),o.clientProperty(),o.deliveryDateProperty(),o.stateProperty(),
            o.totalProperty(),o.orderIdProperty(),o.parcelProperty()
        };
    }
}
