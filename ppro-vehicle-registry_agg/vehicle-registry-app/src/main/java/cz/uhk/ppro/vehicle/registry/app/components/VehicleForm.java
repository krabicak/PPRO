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
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Id("buttonEditVehicle")
    private Button buttonEditVehicle;
    private Vehicle actualVehicle;


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
        buttonEditVehicle.addClickListener(buttonEditVehicleListener());
        buttonReset.addClickListener(buttonResetListener());

        //refresh
        gridVehicles.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //listener na grid
        gridVehicles.addItemClickListener(gridVehiclesListener());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e->{
            fieldSpz.setValue("");
            fieldVin.setValue("");
            fieldSmallTechnical.setValue("");
            fieldBigTechnical.setValue("");
            dateBigTechnical.setValue(null);
            dateSmallTechnical.setValue(null);
            fieldName.setValue("");
            fieldSurname.setValue("");
            fieldBornnum.setValue("");

            checkBoxActive.setValue(false);
            actualVehicle = new Vehicle();
            gridVehicles.deselectAll();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditVehicleListener() {
        return e -> {
            actualVehicle.setActive(checkBoxActive.getValue());

            //spz
            Spz tmpSpz = actualVehicle.getSpz();
            tmpSpz.setSpz(fieldSpz.getValue());
            actualVehicle.setSpz(tmpSpz);

            //vin
            Vin tmpVin = actualVehicle.getVin();
            tmpVin.setVin(fieldVin.getValue());
            actualVehicle.setVin(tmpVin);

            //malej technicak
            Document tmpSmallDocument = actualVehicle.getsTechnicalCert();
            tmpSmallDocument.setDocumentNumber(fieldSmallTechnical.getValue());
            //TODO zmenil bych tostring aby se to vypisovalo lip
            tmpSmallDocument.setToDate(Timestamp.valueOf(dateSmallTechnical.getValue().atStartOfDay()));
            actualVehicle.setsTechnicalCert(tmpSmallDocument);


            //velkej technicka
            Document tmpBigDocument = actualVehicle.getbTechnicalCert();
            tmpBigDocument.setDocumentNumber(fieldBigTechnical.getValue());

            tmpBigDocument.setToDate(Timestamp.valueOf(dateBigTechnical.getValue().atStartOfDay()));

            actualVehicle.setbTechnicalCert(tmpBigDocument);

            //majitel
            Person tmpPerson = actualVehicle.getOwner();
            tmpPerson.setFirstName(fieldName.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());
            tmpPerson.setBornNum(fieldBornnum.getValue());
            actualVehicle.setOwner(tmpPerson);

            //aktivni
            actualVehicle.setActive(checkBoxActive.getValue());

            try {
                vehicleService.addOrUpdateVehicle(actualVehicle);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            refreshGrid();
            Notification notification = new Notification("Vozidlo upraveno", 3000);
            notification.open();
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
            docBtech.setToDate(Timestamp.valueOf(dateBigTechnical.getValue().atStartOfDay()));
            docBtech.setDocumentNumber(fieldBigTechnical.getValue());

            Document docStech = new Document();
            docStech.setToDate(Timestamp.valueOf(dateSmallTechnical.getValue().atStartOfDay()));
            docStech.setDocumentNumber(fieldSmallTechnical.getValue());

            v1.setbTechnicalCert(docBtech);
            v1.setsTechnicalCert(docStech);
            try {
                vehicleService.addOrUpdateVehicle(v1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
            fieldBornnum.setValue(e.getItem().getOwner().getBornNum());

            actualVehicle = e.getItem();
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
