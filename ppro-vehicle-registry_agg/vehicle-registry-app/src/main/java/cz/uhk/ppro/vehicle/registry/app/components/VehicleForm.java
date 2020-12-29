package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.app.services.VehicleService;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDate;

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
    @Autowired
    private VehicleService vehicleService;
    @Id("gridVehicles")
    private Grid<Vehicle> gridVehicles;
    @Id("fieldSpz")
    private TextField fieldSpz;
    @Id("fieldVin")
    private TextField fieldVin;
    @Id("buttonAddVehicle")
    private Button buttonAddVehicle;
    @Id("dateSmallTechnical")
    private DatePicker dateSmallTechnical;
    @Id("dateBigTechnical")
    private DatePicker dateBigTechnical;
    @Id("fieldSurname")
    private TextField fieldSurname;
    @Id("checkBoxActive")
    private Checkbox checkBoxActive;
    @Id("fieldSmallTechnical")
    private TextField fieldSmallTechnical;
    @Id("fieldBigTechnical")
    private TextField fieldBigTechnical;
    @Id("fieldName")
    private TextField fieldName;


    @PostConstruct
    public void init() {
        //konec testovaciho
        gridVehicles.addColumn(vehicle -> vehicle.getSpz().getSpz()).setHeader("SPZ");
        gridVehicles.addColumn(vehicle -> vehicle.getVin().getVin()).setHeader("VIN");
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getFirstName()).setHeader("Jméno");
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getLastName()).setHeader("Přijmení");
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getDocumentNumber()).setHeader("Číslo v. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getToDate()).setHeader("Splatnost v. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getDocumentNumber()).setHeader("Číslo m. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getToDate()).setHeader("Splatnost m. TP");

/*        //tlacitka
        buttonAddVehicle.addClickListener(buttonAddVehicleListener());*/

        //refresh
        gridVehicles.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //listener na grid
        gridVehicles.addItemClickListener(gridVehiclesListener());
    }

    private ComponentEventListener<ItemClickEvent<Vehicle>> gridVehiclesListener() {
        return e -> {
            fieldSpz.setValue(e.getItem().getSpz().getSpz());
            fieldVin.setValue(e.getItem().getVin().getVin());

            //TP
            LocalDate localDateBigTechnical = e.getItem().getbTechnicalCert().getToDate().toLocalDateTime().toLocalDate();
            LocalDate localDateSmallTechnical = e.getItem().getsTechnicalCert().getToDate().toLocalDateTime().toLocalDate();
            dateBigTechnical.setValue(localDateBigTechnical);
            dateSmallTechnical.setValue(localDateSmallTechnical);
            fieldBigTechnical.setValue(e.getItem().getbTechnicalCert().getDocumentNumber());
            fieldSmallTechnical.setValue(e.getItem().getsTechnicalCert().getDocumentNumber());

            //Osoba
            fieldName.setValue(e.getItem().getOwner().getFirstName());
            fieldSurname.setValue(e.getItem().getOwner().getLastName());
        };
    }

/*    private ComponentEventListener<ClickEvent<Button>> buttonAddVehicleListener() {
        return e -> {

            Vehicle v1 = new Vehicle();

            v1.setActive(false);


            Spz spz1 = new Spz();
            spz1.setSpz("6L51346");
            v1.setSpz(spz1);

            Vin vin1 = new Vin();
            vin1.setVin("TMBHA61Z8A8011106");
            v1.setVin(vin1);

            Person p1 = new Person();
            p1.setFirstName("Jan");
            p1.setLastName("Novak");

            v1.setOwner(p1);

            Document d1 = new Document();
            d1.setToDate(new Timestamp(System.currentTimeMillis()));
            d1.setDocumentNumber("999");

            Document d2 = new Document();
            d2.setToDate(new Timestamp(System.currentTimeMillis()));
            d2.setDocumentNumber("888");

            v1.setbTechnicalCert(d1);
            v1.setsTechnicalCert(d2);
            vehicleService.addOrUpdateVehicle(v1);

            refreshGrid();
        };
    }*/

    private void refreshGrid() {
        gridVehicles.setItems(vehicleService.getAllVehicles());
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
