package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.*;
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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.VehicleService;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(VehicleForm.class);

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
    @Id("fieldSearch")
    private TextField fieldSearch;
    @Id("checkBoxNoInsurance")
    private Checkbox checkBoxNoInsurance;


    /**
     * Creates a new VehicleForm.
     */
    public VehicleForm() {
        // You can initialise any data required for the connected UI components here.
    }

    @PostConstruct
    public void init() {
        //grid
        gridVehicles.addColumn(vehicle -> vehicle.getSpz().getSpz()).setHeader("SPZ").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getVin().getVin()).setHeader("VIN").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getFirstName()).setHeader("Jméno").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getLastName()).setHeader("Přijmení").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getOwner().getBornNum()).setHeader("Rodné číslo").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getDocumentNumber()).setHeader("Číslo v. TP").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getbTechnicalCert().getToDate()).setHeader("Splatnost v. TP").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getDocumentNumber()).setHeader("Číslo m. TP").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.getsTechnicalCert().getToDate()).setHeader("Splatnost m. TP").setSortable(true);
        gridVehicles.addColumn(vehicle -> vehicle.isActive()).setHeader("Aktivní").setSortable(true);

        //tlacitka
        buttonAddVehicle.addClickListener(buttonAddVehicleListener());
        buttonEditVehicle.addClickListener(buttonEditVehicleListener());
        buttonReset.addClickListener(buttonResetListener());

        //refresh
        gridVehicles.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //listener na grid
        gridVehicles.addItemClickListener(gridVehiclesListener());

        //vyhledavani
        fieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        fieldSearch.addValueChangeListener(fieldSearchListener());

        //checkbox na nepojisteny
        checkBoxNoInsurance.addClickListener(checkBoxNoInsuranceListener());
    }

    private ComponentEventListener<ClickEvent<Checkbox>> checkBoxNoInsuranceListener() {
        return e -> {
            if (checkBoxNoInsurance.getValue().equals(true)) {
                gridVehicles.setItems(vehicleService.getUnsiredVehicles());
            } else {
                refreshGrid();
            }
        };
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldSearchListener() {
        return e -> {
            gridVehicles.setItems(vehicleService.findVehiclesByKeyWord(fieldSearch.getValue()));
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e -> {
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
            try {
                if (actualVehicle == null) {
                    throw new RuntimeException("Není vybráno žádné vozidlo");
                }
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

                vehicleService.addOrUpdateVehicle(actualVehicle);

                refreshGrid();
                dialogService.showNotification("Vozidlo upraveno");
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };

    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddVehicleListener() {
        return e -> {
            try {
                Vehicle vehicle1 = new Vehicle();

                vehicle1.setActive(checkBoxActive.getValue());

                Spz spz1 = new Spz();
                if (!fieldSpz.isEmpty()) {
                    spz1.setSpz(fieldSpz.getValue());
                    vehicle1.setSpz(spz1);
                }
                vehicle1.setSpz(spz1);

                Vin vin1 = new Vin();
                if (!fieldVin.isEmpty()) {
                    vin1.setVin(fieldVin.getValue());
                    vehicle1.setVin(vin1);
                }
                vehicle1.setVin(vin1);

                Person p1 = new Person();

                if (!fieldName.isEmpty()) {
                    p1.setFirstName(fieldName.getValue());
                }
                if (!fieldSurname.isEmpty()) {
                    p1.setLastName(fieldSurname.getValue());
                }
                if (!fieldBornnum.isEmpty()) {
                    p1.setBornNum(fieldBornnum.getValue());
                }
                vehicle1.setOwner(p1);


                Document docBtech = new Document();
                if (!fieldBigTechnical.getValue().isEmpty()) {
                    docBtech.setToDate(Timestamp.valueOf(dateBigTechnical.getValue().atStartOfDay()));
                    docBtech.setDocumentNumber(fieldBigTechnical.getValue());
                }

                Document docStech = new Document();
                if (!fieldSmallTechnical.getValue().isEmpty()) {
                    docStech.setToDate(Timestamp.valueOf(dateSmallTechnical.getValue().atStartOfDay()));
                    docStech.setDocumentNumber(fieldSmallTechnical.getValue());
                }

                vehicle1.setbTechnicalCert(docBtech);
                vehicle1.setsTechnicalCert(docStech);


                vehicleService.addOrUpdateVehicle(vehicle1);

                refreshGrid();
                dialogService.showNotification("Vozidlo přidáno");
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
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
