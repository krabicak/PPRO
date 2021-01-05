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
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceCompanyService;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceEmployeeService;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceService;
import cz.uhk.ppro.vehicle.registry.app.services.VehicleService;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * A Designer generated component for the insurance-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("insurance-form")
@JsModule("./src/views/insurance-form.js")
public class InsuranceForm extends PolymerTemplate<InsuranceForm.InsuranceFormModel> {

    @Id("dateTo")
    private DatePicker dateTo;
    @Id("dateFrom")
    private DatePicker dateFrom;

    @Id("selectVehicle")
    private ComboBox<Vehicle> selectVehicle;
    @Id("selectInsuranceCompany")
    private ComboBox<InsuranceCompany> selectInsuranceCompany;
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
        //select pojistovny
        selectInsuranceCompany.setItems(insuranceCompanyService.getAllInsuranceCompanies());
        selectInsuranceCompany.setItemLabelGenerator(InsuranceCompany::getCompanyName);

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

        //tlacitka
        buttonAddInsurance.addClickListener(buttonAddInsuranceListener());
        buttonDeleteInsurance.addClickListener(buttonDeleteInsuranceListener());
        buttonEditInsurance.addClickListener(buttonEditInsuranceListener());
        buttonReset.addClickListener(buttonResetListener());

        //grid
        gridInsurancies.addColumn(insurance -> insurance.getInsurancer()).setHeader("Pojišťovák");

        refreshGrid();
    }

    private void refreshGrid() {
        // gridInsurancies.setItems(insuranceService.);
    }

    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e -> {
            dateFrom.setValue(null);
            dateTo.setValue(null);
            selectInsuranceCompany.setValue(null);
            selectVehicle.setValue(null);
            selectInsurancerEmployee.setValue(null);
            fieldName.setValue("");
            fieldSurname.setValue("");
            fieldBornnum.setValue("");

            gridInsurancies.deselectAll();
            actualInsurance = new Insurance();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditInsuranceListener() {
        return e -> {
            //TODO
            try {
                insuranceService.addOrUpdateInsurance(actualInsurance);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonDeleteInsuranceListener() {
        return e -> {
            insuranceService.deleteInsurance(actualInsurance);
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddInsuranceListener() {
        return e -> {
            Insurance tmpInsurance = new Insurance();

            //data
            tmpInsurance.setFromDate(Timestamp.valueOf(dateFrom.getValue().atStartOfDay()));
            tmpInsurance.setToDate(Timestamp.valueOf(dateTo.getValue().atStartOfDay()));

            //pojistovna
            tmpInsurance.setInsuranceCompany(selectInsuranceCompany.getValue());

            //vozidlo
            tmpInsurance.setVehicle(selectVehicle.getValue());

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
                ex.printStackTrace();
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
