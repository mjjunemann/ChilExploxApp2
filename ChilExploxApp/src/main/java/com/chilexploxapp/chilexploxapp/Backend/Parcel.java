/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chilexploxapp.chilexploxapp.Backend;

import com.chilexploxapp.chilexploxapp.Enums.State;
import com.chilexploxapp.chilexploxapp.Enums.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import org.parse4j.ParseClassName;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.callback.SaveCallback;

/**
 *
 * @author matia
 */
@ParseClassName("Parcel")
public class Parcel extends ParseObject
{
    private transient SimpleFloatProperty weight;
    private transient SimpleFloatProperty volume;
    private transient SimpleIntegerProperty priority;
    private transient SimpleStringProperty parcelId;
    public transient SimpleObjectProperty<Address> origin;
    public transient SimpleObjectProperty<Address> destination;
    private transient SimpleObjectProperty<State> state;
    private transient SimpleObjectProperty<Type> type;
    private transient SimpleObjectProperty<Order> order;


    public Parcel()
    {
        
    }

    
    public Parcel(Type type,float weight,float volume,int priority,Address origin,Address destination)//,Order order, String id)
    {
        super();
        setType(type);
        setState(State.Origin);
        setWeight(weight);
        setVolume(volume);
        setPriority(priority);
        setOrigin(origin);
        setDestination(destination);
        putParcel();
        this.saveInBackground();
    }

    public void putParcel()
    {
        setDestinationParse();
        setOriginParse();
        setWeightParse();
        setVolumeParse();
        setStateParse();
        setTypeParse();
        
    }
    //<editor-fold desc="SetterParse">
    public void setDestinationParse()
    {
        put("destination",destination.get());
    }
    public void setOriginParse()
    {
        put("origin",origin.get());
    }
    public void setWeightParse()
    {
        put("weight",weight.get());
    }
    public void setVolumeParse()
    {
        put("volume",volume.get());
    }
    public void setStateParse()
    {
        put("state",state.get().toString());
    }
    public void setTypeParse()
    {
        put("type",type.get().toString());
    }
    public void setOrderParse()
    {
        put("order",order.get());
    }
    //</editor-fold>
    //<editor-fold desc="Setter&Getters">
     public final Address getDestination()
     {
         return destinationProperty().get();
     }
     public final void setDestination(Address subsidiary)
     {
         destinationProperty().set(subsidiary);
     }
     public Order getOrder()
     {
         return orderProperty().get();
     }
     public void setOrder(Order o)
     {
         orderProperty().set(o);
         put("order",o);
     }
     public final Address getOrigin()
     {
         return originProperty().get();
     }
     public final void setOrigin(Address subsidiary)
     {
        originProperty().set(subsidiary);

     }
     public final void setPriority(int priority)
     {
         priorityProperty().set(priority);
     }
     public final int getPriority()
     {
         return priorityProperty().get();
     }
     public final void setWeight(float weight)
     {
         weightProperty().set(weight);
     }
     public final float getWeight()
     {
         return weightProperty().get();
     }
     public final void setVolume(float volume)
     {
         volumeProperty().set(volume);
     }
     public final float getVolume()
     {
         return this.volumeProperty().get();
     }
     
     public final String getId()
     {
         return this.idProperty().get();
     }
     
     public final void setId(String id)
     {
         this.idProperty().set(id);
     }
     public final void setState(State s)
     {
         this.stateProperty().set(s);
     }
     public State getState()
     {
         return this.stateProperty().get();
     }
     public final void setType(Type t)
     {
         this.typeProperty().set(t);
     }
     public Type getType()
     {
         return this.typeProperty().get();
     }
    //</editor-fold>
    
    //<editor-fold desc="Parse Getters">
    
    //</editor-fold>
     //<editor-fold desc="Properties">
      public final FloatProperty weightProperty()
      {
          if (weight == null)
          {
              weight = new SimpleFloatProperty();
          }
          return weight;
      }
      public final ObjectProperty<Address> originProperty()
      {
          if (origin == null)
          {
              origin = new SimpleObjectProperty<Address>();
          }
          return origin;
      }
      public final ObjectProperty<Address> destinationProperty()
      {
          if (destination == null)
          {
              destination = new SimpleObjectProperty<Address>();
          }
          return destination;
      }
      public final FloatProperty volumeProperty()
      {
          if(volume == null)
          {
              volume = new SimpleFloatProperty();
          }
          return volume;
      }
      public final IntegerProperty priorityProperty()
      {
          if(priority == null)
          {
              priority = new SimpleIntegerProperty();
          }
          return priority;
      }
      public final StringProperty idProperty()
      {
          if (parcelId == null)
          {
              parcelId = new SimpleStringProperty();
          }
          return parcelId;
      }
      
      public final ObjectProperty<Type> typeProperty()
      {
          if (type == null)
          {
              type = new SimpleObjectProperty();
          }
          return type;
      }
      public final ObjectProperty<State> stateProperty()
      {
          if (state == null)
          {
              state = new SimpleObjectProperty();
          }
          return state;
      }
      public final ObjectProperty<Order> orderProperty()
      {
          if (order == null)
          {
              order = new SimpleObjectProperty();
          }
          return order;
      }
     //</editor-fold>
     
    public static  Callback<Parcel,Observable[]> extractor()
    {
        return (Parcel p) -> new Observable[]{p.weightProperty(),p.volumeProperty(),
            p.priorityProperty(),p.originProperty(),
            p.destinationProperty(),p.idProperty(),
            p.stateProperty(),p.typeProperty(),p.orderProperty()
        };
    }

}
