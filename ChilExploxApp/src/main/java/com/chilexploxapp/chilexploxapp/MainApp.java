package com.chilexploxapp.chilexploxapp;

import com.chilexploxapp.chilexploxapp.Backend.Address;
import com.chilexploxapp.chilexploxapp.Backend.Parcel;
import com.chilexploxapp.chilexploxapp.Enums.Type;
import static com.chilexploxapp.chilexploxapp.RetrieveDataBase.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;
import org.parse4j.util.ParseRegistry;


public class MainApp extends Application {

    public void onCreate()
    {
        ParseRegistry.registerSubclass(Address.class);
        ParseRegistry.registerSubclass(Parcel.class);
        String APP_ID = "uSW2i9FEdgbWy5LuIQg9BxLfQquCQx46fcxVSkpM";
        String REST_API_KEY ="5eWp28VrP6qAf0rv33c0sDFFKLN3YHjaF8mPDQwp";
        Parse.initialize(APP_ID,REST_API_KEY);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        onCreate();
        /*
        ParseQuery<Address> query = ParseQuery.getQuery(Address.class);
        query.findInBackground(new FindCallback<Address>() {

            @Override
            public void done(List<Address> list, ParseException pe) {
                if (pe == null)
                {
                    for (Address obj :list)
                    {
                        obj.setVisualizable();
                        System.out.println(obj.getNumberParse());
                        System.out.println(obj.getNumber());
                        //Address a = (Address) ParseObject.createWithoutData(Address.class.getName(),obj.getObjectId());
                    }
                }
                else 
                {
                    System.out.println(pe.toString());
                }
            }
        });*/
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        ArrayList<Address> a = retrieveAddress();
        a.stream().forEach((addr) -> {
            System.out.println(addr.getMainStreet());
        });
        
        Parcel p1 = new Parcel(Type.Normal,170,170,10,a.get(0),a.get(1));
        Parcel p2 = new Parcel(Type.Fragile,200,200,10,a.get(1),a.get(2));
        
        //System.out.println(p.getObjectId());
    }

    
    
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
