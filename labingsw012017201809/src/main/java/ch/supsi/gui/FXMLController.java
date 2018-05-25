package ch.supsi.gui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private boolean mappaCreata = false;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    private CreaUrlBlaBlaCar blabla;
    private InterpretatoreBlablacar jckbla;
    private InterpretatoreGooglePlaces googlePlaces;
    private List<ViaggioBlaBlaCar> viaggi = new ArrayList<>();
    private Set<NodoGooglePlaces> setCity = new HashSet<>();

    @FXML
    protected GoogleMapView mapView;

    @FXML
    protected TextField fromTextField;

    @FXML
    protected TextField toTextField;

    @FXML
    protected ToggleGroup gruppo;

    @FXML
    protected CheckBox check_1;

    @FXML
    protected CheckBox check_2;

    @FXML
    protected CheckBox check_3;

    @FXML
    protected CheckBox check_4;

    @FXML
    protected TextArea TextAreax;

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        if (mappaCreata)
            mapReset();
        DirectionsRequest request;
        if (check_3.isSelected())
            request = new DirectionsRequest(from.get(), to.get(), TravelModes.WALKING);
        else {
            if (check_4.isSelected())
                request = new DirectionsRequest(from.get(), to.get(), TravelModes.TRANSIT);
            else
                request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        }
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        //blablacar
        blabla = new CreaUrlBlaBlaCar(fromTextField.textProperty(), toTextField.textProperty());
        try {
            blabla.createUrl();
        } catch (Exception e) {
            System.out.println("errore nella creazione della URL");
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(blabla.getURL().openStream()));
            String s = reader.readLine();
            jckbla = new InterpretatoreBlablacar(s, viaggi);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        for (ViaggioBlaBlaCar v : viaggi)
            TextAreax.appendText(v.getViaggio());

        //google
        googlePlaces = new InterpretatoreGooglePlaces(new Nodo(new Coordinata(46.005, 8.947)), new Nodo(new Coordinata(45.4373, 9.17)));
        setCity = googlePlaces.listaCittà();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
    }

    @Override
    public void mapInitialized() {
        mappaCreata = false;
        MapOptions options = new MapOptions();

        options.center(new LatLong(46.0233459, 8.9171287))
                .zoomControl(true)
                .zoom(17)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        mappaCreata = true;
    }

    public void mapReset() {
        mappaCreata = false;
        MapOptions options = new MapOptions();
        options.zoomControl(true)
                .zoom(17)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        mappaCreata = true;
    }


}