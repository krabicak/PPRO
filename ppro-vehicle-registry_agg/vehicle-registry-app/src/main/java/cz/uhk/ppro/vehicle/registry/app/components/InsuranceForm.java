package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.*;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Tag("insurance-form")
@JsModule("./src/views/insurance-form.js")
public class InsuranceForm extends PolymerTemplate<InsuranceForm.InsuranceFormModel> {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
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
    @Id("fieldSearch")
    private TextField fieldSearch;

    public InsuranceForm() {
        // You can initialise any data required for the connected UI components here.
    }

    @PostConstruct
    public void init() {

        //default hodnoty
        dateTo.setValue(LocalDate.now());
        dateFrom.setValue(LocalDate.now());

        //select vozidla
        selectVehicle.setItems(vehicleService.getAllVehicles());
        selectVehicle.setItemLabelGenerator(vehicle -> vehicle.getVin().getVin() + "\r\n" + vehicle.getSpz().getSpz() + "\r\n" + vehicle.getbTechnicalCert().getDocumentNumber() + "\r\n"
                + vehicle.getsTechnicalCert().getDocumentNumber());

        //select pojistovak
        Set<Integer> strings = new HashSet<>();
        selectInsurancerEmployee.setItemLabelGenerator((ItemLabelGenerator<InsuranceEmployee>) insuranceEmployee -> {
            Person person = insuranceEmployee.getUser().getPerson();
            InsuranceCompany company = insuranceEmployee.getInsuranceCompany();
            String str = company.getCompanyName() + " " + person.getFirstName() + " " + person.getLastName();
            if (strings.contains(str.hashCode())) {
                str += " " + insuranceEmployee.getUser().getLogin();
            }
            strings.add(str.hashCode());
            return str;
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
                + insurance.getInsurancer().getPerson().getLastName()).setHeader("Pojistitel").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getPerson().getFirstName() + " "
                + insurance.getPerson().getLastName()).setHeader("Zákazník").setSortable(true);
        gridInsurancies.addColumn(insurance -> insurance.getPerson().getBornNum()).setHeader("Rodné číslo").setSortable(true);

        gridInsurancies.getColumns().forEach(col -> col.setAutoWidth(true));
        gridInsurancies.addItemClickListener(gridCLickListener());

        //listener na rc
        fieldBornnum.setValueChangeMode(ValueChangeMode.EAGER);
        fieldBornnum.addValueChangeListener(fieldBornnumListener());

        //vyhledavani
        fieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        fieldSearch.addValueChangeListener(fieldSearchListener());

        refreshGrid();
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldSearchListener() {
        return event -> {
            gridInsurancies.setItems(insuranceService.findInsurancisByKeyWord(fieldSearch.getValue()));
        };
    }

    private ComponentEventListener<ItemClickEvent<Insurance>> gridCLickListener() {
        return e -> {
            actualInsurance = e.getItem();

            fieldBornnum.setValue(e.getItem().getPerson().getBornNum());
            fieldName.setValue(e.getItem().getPerson().getFirstName());
            fieldSurname.setValue(e.getItem().getPerson().getLastName());

            dateTo.setValue(e.getItem().getToDate().toLocalDateTime().toLocalDate());
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
            resetForm();
        };
    }

    private void resetForm() {
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
        actualInsurance = null;
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldBornnumListener() {
        return e -> {
            Person tmpPerson = userService.findPersonByBornNum(fieldBornnum.getValue());
            if (tmpPerson != null) {
                fieldSurname.setValue(tmpPerson.getLastName());
                fieldName.setValue(tmpPerson.getFirstName());
            } else {
                fieldSurname.setValue("");
                fieldName.setValue("");
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditInsuranceListener() {
        return e -> {
            try {
                if (actualInsurance == null) throw new RuntimeException("Není vybráno žádné pojištění");
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

                insuranceService.addOrUpdateInsurance(actualInsurance);
                dialogService.showNotification("Pojištění upraveno");
                refreshGrid();
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonDeleteInsuranceListener() {
        return e -> {
            try {
                if (actualInsurance == null) throw new RuntimeException("Není vybráno žádné pojištění");
                insuranceService.deleteInsurance(actualInsurance);
                dialogService.showNotification("Pojištění smazáno");
                refreshGrid();
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddInsuranceListener() {
        return e -> {
            try {
                Insurance tmpInsurance = new Insurance();

                //data
                tmpInsurance.setFromDate(Timestamp.valueOf(dateFrom.getValue().atStartOfDay()));
                tmpInsurance.setToDate(Timestamp.valueOf(dateTo.getValue().atStartOfDay()));

                //vozidlo
                tmpInsurance.setVehicle(selectVehicle.getValue());

                //pojistovna
                if (selectInsurancerEmployee.getValue() != null)
                    tmpInsurance.setInsuranceCompany(selectInsurancerEmployee.getValue().getInsuranceCompany());
                else tmpInsurance.setInsuranceCompany(new InsuranceCompany());
                //pojistovak
                if (selectInsurancerEmployee.getValue() != null)
                    tmpInsurance.setInsurancer(selectInsurancerEmployee.getValue().getUser());
                else tmpInsurance.setInsurancer(new User());

                //nova osoba
                Person tmpPerson = new Person();
                tmpPerson.setBornNum(fieldBornnum.getValue());
                tmpPerson.setLastName(fieldSurname.getValue());
                tmpPerson.setFirstName(fieldName.getValue());
                tmpInsurance.setPerson(tmpPerson);

                insuranceService.addOrUpdateInsurance(tmpInsurance);
                refreshGrid();
                dialogService.showNotification("Pojištění přidáno");
                dialogService.showNotification("Pojištění přidáno");
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    public interface InsuranceFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
