package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.common.entities.Spz;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.entities.Vin;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the vehicle-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("vehicle-form")
@JsModule("./src/views/vehicle-form.js")
public class VehicleForm extends PolymerTemplate<VehicleForm.VehicleFormModel> {

    @Autowired
    private DialogService dialogService;
    @Id("gridVehicles")
    private Grid<Vehicle> gridVehicles;
    @Id("fieldSpz")
    private TextField fieldSpz;
    @Id("fieldVin")
    private TextField fieldVin;
    @Id("buttonAddVehicle")
    private Button buttonAddVehicle;

    @PostConstruct
    public void init() {
        //testovaci, potom smazat
        Vehicle v1 = new Vehicle();

        Spz spz1 = new Spz();
        spz1.setSpz("6S51346");
        v1.setSpz(spz1);

        Vin vin1 = new Vin();
        vin1.setVin("TMBHA61Z8A8011105");
        v1.setVin(vin1);
        gridVehicles.setItems(v1);
//konec testovaciho
        gridVehicles.addColumn(Vehicle::getVin).setHeader("VIN");
        gridVehicles.addColumn(Vehicle::getSpz).setHeader("SPZ");

        //tlacitka
        buttonAddVehicle.addClickListener(buttonAddVehicleListener());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddVehicleListener() {
        return e -> {
            try {
                userService.removeUser(actualUser);
            } catch (PersonException personException) {
                personException.printStackTrace();
            }
            refreshGrid();
        };
    }

    /**
     * Creates a new VehicleForm.
     */
    public VehicleForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between VehicleForm and vehicle-form
     */
    public interface VehicleFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
