/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilexploxapp.chilexploxapp.Backend;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author matia
 */
@ParseClassName("Address")
public class Address extends ParseObject
{
    private transient StringProperty city;
    private transient StringProperty neighborhood;
    private transient StringProperty street;
    private transient IntegerProperty number;
    
    public Address()
    {
        //Default constructor is needed 
    }
    
    public Address(String street, Integer number, String neighborhood, String city)
    {
        super();
        setCity(city);
        setNeighborhood(neighborhood);
        setStreet(street);
        setNumber(number);
        putAddress();
        this.saveInBackground();

    }
    
    public void setVisualizable()
    {
        setCity(getCityParse());
        setNeighborhood(getNeighborhoodParse());
        setStreet(getStreetParse());
        setNumber(getNumberParse());
    }
    public String getMainStreet()
    {
        return getStreet()+getNumber();
    }
    public String getAddress()
    {
        return String.format("%1$s %2$s,%3$s,%4$s",getStreet(),getNumber(),getNeighborhood(),getCity());
    }
    public String stringValue()
    {
        return String.format("%1$s %2$s,%3$s,%4$s",getStreet(),getNumber(),getNeighborhood(),getCity());
    }
    
    @Override
    public String toString()
    {
        return getMainStreet();
    }
    
    public void putAddress()
    {
        setCityParse();
        setNeighborhoodParse();
        setStreetParse();
        setNumberParse();
    }
    //<editor-fold desc="setterParse">
    public void setCityParse()
    {
        put("city",city);
    }
    public void setNeighborhoodParse()
    {
        put("neighborhood",neighborhood);
    }
    public void setStreetParse()
    {
        put("street",street);
    }
    public void setNumberParse()
    {
       put("number",number);
    }
    //</editor-fold> 
    
    //<editor-fold desc="setters">
    public void setCity(String city)
    {
        cityProperty().set(city);
    }
    public void setNeighborhood(String neighborhood)
    {
        neighborhoodProperty().set(neighborhood);
    }
    public void setStreet(String street)
    {
        streetProperty().set(street);   
    }
    public void setNumber(int number)
    {
        numberProperty().set(number);
    }
    //</editor-fold>
    
    //<editor-fold desc="getters">
    public String getCity()
    {
        return cityProperty().get();
    }
    public String getNeighborhood()
    {
        return neighborhoodProperty().get();
    }
    public String getStreet()
    {
        return streetProperty().get();
    }
    public int getNumber()
    {
        return numberProperty().get();
    }
    //</editor-fold>
    
    //<editor-fold desc="getters parse">
    
    public String getCityParse()
    {
        return getString("city");
    }
    public String getNeighborhoodParse()
    {
        return getString("neighborhood");
    }
    public String getStreetParse()
    {
        return getString("street");
    }
    public int getNumberParse()
    {
        return getInt("number");
    }
    
    //</editor-fold>
    
    //<editor-fold desc="properties">
    public final StringProperty cityProperty()
    {
        if (city == null)
        {
            city = new SimpleStringProperty();
        }
        return city;
    }
    public final StringProperty neighborhoodProperty()
    {
        if (neighborhood == null)
        {
            neighborhood = new SimpleStringProperty();
        }
        return neighborhood;
    }
    public final StringProperty streetProperty()
    {
        if (street == null)
        {
            street = new SimpleStringProperty();
        }
        return street;
    }
    public final IntegerProperty numberProperty()
    {
        if (number == null)
        {
            number = new SimpleIntegerProperty();
        }
        return number;
    }
    

    //</editor-fold>
    
    public static Callback<Address,Observable[]> extractor()
    {
        return (Address addr) -> new Observable[]{addr.cityProperty(),addr.neighborhoodProperty(),addr.numberProperty(),addr.streetProperty()};
    }
}
