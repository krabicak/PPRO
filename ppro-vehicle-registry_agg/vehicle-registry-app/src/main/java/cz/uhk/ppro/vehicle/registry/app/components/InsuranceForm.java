package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;
import cz.uhk.ppro.vehicle.registry.app.services.*;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Tag("insurance-form")
@JsModule("./src/views/insurance-form.js")
public class InsuranceForm extends PolymerTemplate<InsuranceForm.InsuranceFormModel> {

    private static final Logger logger = LoggerFactory.getLogger(InsuranceForm.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionService sessionService;
    @Id("dateTo")
    private DatePicker dateTo;
    @Id("dateFrom")
    private DatePicker dateFrom;
    @Autowired
    private DialogService dialogService;
    @Id("selectVehicle")
    private ComboBox<Vehicle> selectVehicle;
    @Id("buttonReset")
    private Button buttonReset;
    @Id("fieldSurname")
    private TextField fieldSurname;
    @Id("fieldName")
    private TextField fieldName;
    @Id("selectInsurancerEmployee")
    private ComboBox<InsuranceEmployee> selectInsurancerEmployee;
    @Id("buttonEditInsurance")
    private Button buttonEditInsurance;
    @Id("buttonAddInsurance")
    private Button buttonAddInsurance;
    @Id("buttonDeleteInsurance")
    private Button buttonDeleteInsurance;
    @Id("gridInsurancies")
    private Grid<Insurance> gridInsurancies;
    @Id("fieldBornnum")
    private TextField fieldBornnum;
    @Autowired
    private InsuranceEmployeeService insuranceEmployeeService;
    @Autowired
    private InsuranceCompanyService insuranceCompanyService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private InsuranceService insuranceService;
    private Insurance actualInsurance;

    public InsuranceForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * Creates a new InsuranceForm.
     */
    @PostConstruct
    public void init() {

        //select vozidla
        selectVehicle.setItems(vehicleService.getAllVehicles());
        selectVehicle.setItemLabelGenerator(vehicle -> vehicle.getVin().getVin() + "\r\n" + vehicle.getSpz().getSpz() + "\r\n" + vehicle.getbTechnicalCert().getDocumentNumber() + "\r\n"
                + vehicle.getsTechnicalCert().getDocumentNumber());

        //select pojistovak
        //TODO predelat na normalni vypis
        Set<Integer> strings = new HashSet<>();
        selectInsurancerEmployee.setItemLabelGenerator(new ItemLabelGenerator<InsuranceEmployee>() {
            @Override
            public String apply(InsuranceEmployee insuranceEmployee) {
                Person person = insuranceEmployee.getUser().getPerson();
                InsuranceCompany company = insuranceEmployee.getInsuranceCompany();
                String str = company.getCompanyName() + " " + person.getFirstName() + " " + person.getLastName();
                if (strings.contains(str.hashCode())) {
                    str += " " + insuranceEmployee.getUser().getLogin();
                }
                strings.add(str.hashCode());
                return str;
            }
        });
        selectInsurancerEmployee.setItems(insuranceEmployeeService.getAllInsuranceEmployee());

        if (loginService.isLoggedUserInsurer()) {
            //select
            for (InsuranceEmployee ie : insuranceEmployeeService.getAllInsuranceEmployee()) {
                if (sessionService.getLogin().equals(ie.getUser().getLogin())) {
                    selectInsurancerEmployee.setValue(ie);
                    break;
                }
            }
            selectInsurancerEmployee.setEnabled(false);
        }

        //tlacitka
        buttonAddInsurance.addClickListener(buttonAddInsuranceListener());
        buttonDeleteInsurance.addClickListener(buttonDeleteInsuranceListener());
        buttonEditInsurance.addClickListener(buttonEditInsuranceListener());
        buttonReset.addClickListener(buttonResetListener());

        //grid
        gridInsurancies.addColumn(insurance -> dateBeautify(insurance.getFromDate())).setHeader("Od").setSortable(true);
        gridInsurancies.addColumn(insurance -> dateBeautify(insurance.getToDate())).setHeader("Do").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getVehicle().getVin().getVin()).setHeader("VIN").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getVehicle().getSpz().getSpz()).setHeader("SPZ").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getInsuranceCompany().getCompanyName()).setHeader("Pojišťovna").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getInsurancer().getPerson().getFirstName() + " "
                + insurance.getInsurancer().getPerson().getLastName()).setHeader("Pojišťovák").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getPerson().getFirstName() + " "
                + insurance.getPerson().getLastName()).setHeader("Pojistitel").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getPerson().getBornNum()).setHeader("Rodné číslo").setSortable(true);

        gridInsurancies.getColumns().forEach(col -> col.setAutoWidth(true));
        gridInsurancies.addItemClickListener(gridCLickListener());

        refreshGrid();
    }

    private ComponentEventListener<ItemClickEvent<Insurance>> gridCLickListener() {
        return e -> {
            actualInsurance = e.getItem();

            fieldBornnum.setValue(e.getItem().getPerson().getBornNum());
            fieldName.setValue(e.getItem().getPerson().getFirstName());
            fieldSurname.setValue(e.getItem().getPerson().getLastName());

            dateTo.setValue(e.getItem().getFromDate().toLocalDateTime().toLocalDate());
            dateFrom.setValue(e.getItem().getFromDate().toLocalDateTime().toLocalDate());

            selectVehicle.setValue(e.getItem().getVehicle());

            for (InsuranceEmployee ie : insuranceEmployeeService.getAllInsuranceEmployee()) {
                if (e.getItem().getInsurancer().getLogin().equals(ie.getUser().getLogin())) {
                    selectInsurancerEmployee.setValue(ie);
                    break;
                }
            }
            if (loginService.isLoggedUserInsurer()) {
                for (InsuranceEmployee ie : insuranceEmployeeService.getAllInsuranceEmployee()) {
                    if (sessionService.getLogin().equals(ie.getUser().getLogin())) {
                        selectInsurancerEmployee.setValue(ie);
                        break;
                    }
                }
                selectInsurancerEmployee.setEnabled(false);
            }

        };
    }

    private String dateBeautify(Timestamp tms) {
        String day = String.valueOf(tms.toLocalDateTime().getDayOfMonth());
        String month = String.valueOf(tms.toLocalDateTime().getMonthValue());
        String year = String.valueOf(tms.toLocalDateTime().getYear());
        return day + ". " + month + ". " + year;
    }

    private void refreshGrid() {
        gridInsurancies.setItems(insuranceService.getAllInsurance());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e -> {
            dateFrom.setValue(null);
            dateTo.setValue(null);
            selectVehicle.setValue(null);

            if (loginService.isLoggedUserInsurer()) {
                //select
                for (InsuranceEmployee ie : insuranceEmployeeService.getAllInsuranceEmployee()) {
                    if (sessionService.getLogin().equals(ie.getUser().getLogin())) {
                        selectInsurancerEmployee.setValue(ie);
                        break;
                    }
                }
                selectInsurancerEmployee.setEnabled(false);

            } else {
                selectInsurancerEmployee.setValue(null);
            }

            fieldName.setValue("");
            fieldSurname.setValue("");
            fieldBornnum.setValue("");

            gridInsurancies.deselectAll();
            actualInsurance = new Insurance();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditInsuranceListener() {
        return e -> {
            //Person
            Person tmpPerson = actualInsurance.getPerson();
            tmpPerson.setBornNum(fieldBornnum.getValue());
            tmpPerson.setFirstName(fieldName.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());
            actualInsurance.setPerson(tmpPerson);

            //data
            actualInsurance.setToDate(Timestamp.valueOf(dateTo.getValue().atStartOfDay()));
            actualInsurance.setFromDate(Timestamp.valueOf(dateFrom.getValue().atStartOfDay()));

            //vozidlo
            actualInsurance.setVehicle(selectVehicle.getValue());

            //pojistovak
            actualInsurance.setInsurancer(selectInsurancerEmployee.getValue().getUser());

            //pojistovna
            actualInsurance.setInsuranceCompany(selectInsurancerEmployee.getValue().getInsuranceCompany());

            try {
                insuranceService.addOrUpdateInsurance(actualInsurance);
            } catch (Exception ex) {
                logger.error("Chyba", ex);
                dialogService.showErrorDialog(ex);
            }
            dialogService.showNotification("Pojištění upraveno");
            refreshGrid();
            //TODO
            try {
                insuranceService.addOrUpdateInsurance(actualInsurance);
            } catch (Exception ex) {
                logger.error("Chyba", ex);
                dialogService.showErrorDialog(ex);
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonDeleteInsuranceListener() {
        return e -> {
            insuranceService.deleteInsurance(actualInsurance);
            dialogService.showNotification("Pojištění smazáno");
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddInsuranceListener() {
        return e -> {
            Insurance tmpInsurance = new Insurance();

            //data
            tmpInsurance.setFromDate(Timestamp.valueOf(dateFrom.getValue().atStartOfDay()));
            tmpInsurance.setToDate(Timestamp.valueOf(dateTo.getValue().atStartOfDay()));

            //vozidlo
            tmpInsurance.setVehicle(selectVehicle.getValue());

            //pojistovna
            tmpInsurance.setInsuranceCompany(selectInsurancerEmployee.getValue().getInsuranceCompany());

            //pojistovak
            tmpInsurance.setInsurancer(selectInsurancerEmployee.getValue().getUser());

            //nova osoba
            Person tmpPerson = new Person();
            tmpPerson.setBornNum(fieldBornnum.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());
            tmpPerson.setFirstName(fieldName.getValue());
            tmpInsurance.setPerson(tmpPerson);

            try {
                insuranceService.addOrUpdateInsurance(tmpInsurance);
            } catch (Exception ex) {
                logger.error("Chyba", ex);
                dialogService.showErrorDialog(ex);
            }
            refreshGrid();
            dialogService.showNotification("Pojištění přidáno");
            try {
                insuranceService.addOrUpdateInsurance(tmpInsurance);
            } catch (Exception ex) {
                logger.error("Chyba", ex);
                dialogService.showErrorDialog(ex);
            }
        };
    }

    /**
     * This model binds properties between InsuranceForm and insurance-form
     */
    public interface InsuranceFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
