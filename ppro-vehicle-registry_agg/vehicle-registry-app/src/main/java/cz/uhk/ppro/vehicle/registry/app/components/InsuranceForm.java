package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the insurance-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("insurance-form")
@JsModule("./src/views/insurance-form.js")
public class InsuranceForm extends PolymerTemplate<InsuranceForm.InsuranceFormModel> {

    @Id("gridInsurancies")
    private Grid<InsuranceCompany> gridInsurancies;
    @Id("fieldInsurance")
    private TextField fieldInsurance;
    @Id("buttonEditInsurance")
    private Button buttonEditInsurance;
    @Id("ButtonDeleteInsurance")
    private Button buttonDeleteInsurance;
    @Id("buttonAddInsurance")
    private Button buttonAddInsurance;

    /**
     * Creates a new InsuranceForm.
     */
    @PostConstruct
    public void init() {
        gridInsurancies.addColumn(vehicle -> vehicle.getCompanyName()).setHeader("Jméno");
    }

    public InsuranceForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between InsuranceForm and insurance-form
     */
    public interface InsuranceFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
