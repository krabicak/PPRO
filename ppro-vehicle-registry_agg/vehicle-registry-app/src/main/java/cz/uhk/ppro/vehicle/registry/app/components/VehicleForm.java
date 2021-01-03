package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.VehicleService;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
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
    @Id("fieldBornnum")
    private TextField fieldBornnum;
    @Id("buttonReset")
    private Button buttonReset;
    @Id("buttonRemoveVehicle")
    private Button buttonRemoveVehicle;
    @Id("buttonEditVehicle")
    private Button buttonEditVehicle;


    /**
     * Creates a new VehicleForm.
     */
    public VehicleForm() {
        // You can initialise any data required for the connected UI components here.
    }

    @PostConstruct
    public void init() {
        //grid
        gridVehicles.addColumn(vehicle -> vehicle.getSpz().getSpz()).setHeader("SPZ");
        gridVehicles.addColumn(vehicle -> vehicle.getVin().getVin()).setHeader("VIN");
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getFirstName()).setHeader("Jméno");
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getLastName()).setHeader("Přijmení");
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getBornNum()).setHeader("Rodné číslo");
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getDocumentNumber()).setHeader("Číslo v. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getToDate()).setHeader("Splatnost v. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getDocumentNumber()).setHeader("Číslo m. TP");
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getToDate()).setHeader("Splatnost m. TP");
        gridVehicles.addColumn(vehicle -> vehicle.isActive()).setHeader("Aktivní");

        //tlacitka
        buttonAddVehicle.addClickListener(buttonAddVehicleListener());
        buttonRemoveVehicle.addClickListener(buttonRemoveVehicleListener());

        //refresh
        gridVehicles.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //listener na grid
        gridVehicles.addItemClickListener(gridVehiclesListener());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonRemoveVehicleListener() {
        return e->{
          /*try{
              vehicleService.
          }
          catch{}*/
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddVehicleListener() {
        return e -> {
            //TODO k tomuhle dodelat exception

            Vehicle v1 = new Vehicle();

            v1.setActive(checkBoxActive.getValue());

            Spz spz1 = new Spz();
            spz1.setSpz(fieldSpz.getValue());
            v1.setSpz(spz1);

            Vin vin1 = new Vin();
            vin1.setVin(fieldVin.getValue());
            v1.setVin(vin1);

            Person p1 = new Person();
            p1.setFirstName(fieldName.getValue());
            p1.setLastName(fieldSurname.getValue());
            p1.setBornNum(fieldBornnum.getValue());

            v1.setOwner(p1);

            Document docBtech = new Document();
            //TODO zmenit na cas z pole
            docBtech.setToDate(new Timestamp(System.currentTimeMillis()));
            docBtech.setDocumentNumber(fieldBigTechnical.getValue());

            Document docStech = new Document();
            //TODO zmenit na cas z pole
            docStech.setToDate(new Timestamp(System.currentTimeMillis()));
            docStech.setDocumentNumber(fieldSmallTechnical.getValue());

            v1.setbTechnicalCert(docBtech);
            v1.setsTechnicalCert(docStech);
            vehicleService.addOrUpdateVehicle(v1);

            refreshGrid();
            Notification notification = new Notification("Vozidlo přidáno", 3000);
            notification.open();
        };

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

    private void refreshGrid() {
        gridVehicles.setItems(vehicleService.getAllVehicles());
    }

    /**
     * This model binds properties between VehicleForm and vehicle-form
     */
    public interface VehicleFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
