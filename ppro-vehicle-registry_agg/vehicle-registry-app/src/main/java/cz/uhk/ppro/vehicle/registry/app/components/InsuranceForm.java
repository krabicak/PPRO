package cz.uhk.ppro.vehicle.registry.app.components;

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
import cz.uhk.ppro.vehicle.registry.app.services.VehicleService;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
    private ComboBox<String> selectInsurancerEmployee;
    @Id("buttonEditInsurance")
    private Button buttonEditInsurance;
    @Id("buttonAddInsurance")
    private Button buttonAddInsurance;
    @Id("buttonDeleteInsurance")
    private Button buttonDeleteInsurance;
    @Id("gridInsurancies")
    private Grid gridInsurancies;
    @Id("fieldBornnum")
    private TextField fieldBornnum;
    @Autowired
    private InsuranceCompanyService insuranceCompanyService;
    @Autowired
    private VehicleService vehicleService;

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
        selectVehicle.setItemLabelGenerator(vehicle -> vehicle.getVin().getVin() + "\r\n" + vehicle.getSpz().getSpz()  + "\r\n" + vehicle.getbTechnicalCert().getDocumentNumber() + "\r\n"
                + vehicle.getsTechnicalCert().getDocumentNumber());
    }

    /**
     * This model binds properties between InsuranceForm and insurance-form
     */
    public interface InsuranceFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
