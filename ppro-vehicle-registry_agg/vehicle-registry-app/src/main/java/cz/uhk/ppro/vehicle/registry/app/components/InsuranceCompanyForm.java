package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceCompanyService;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the insurance-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("insurance-company-form")
@JsModule("./src/views/insurance-company-form.js")
public class InsuranceCompanyForm extends PolymerTemplate<InsuranceCompanyForm.InsuranceCompanyFormModel> {

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
    @Autowired
    private InsuranceCompanyService insuranceService;

    private InsuranceCompany actualInsuranceCompany;

    public InsuranceCompanyForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * Creates a new InsuranceForm.
     */
    @PostConstruct
    public void init() {

        //grid
        gridInsurancies.addColumn(InsuranceCompany::getCompanyName).setHeader("Jméno");
        gridInsurancies.addItemClickListener(gridInsuranciesListener());
        refreshGrid();

        //tlacitko
        buttonAddInsurance.addClickListener(buttonAddInsuranceListener());
        buttonDeleteInsurance.addClickListener(buttonDeleteInsuranceListener());
        buttonEditInsurance.addClickListener(buttonEditInsuranceListener());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditInsuranceListener() {
        return e -> {
            actualInsuranceCompany.setCompanyName(fieldInsurance.getValue());
            insuranceService.addOrUpdateInsuranceCompany(actualInsuranceCompany);
            refreshGrid();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonDeleteInsuranceListener() {
        return e -> {
            insuranceService.removeInsuranceCompany(actualInsuranceCompany);
            refreshGrid();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddInsuranceListener() {
        return e -> {
            InsuranceCompany tmp = new InsuranceCompany();
            tmp.setCompanyName(fieldInsurance.getValue());
            insuranceService.addOrUpdateInsuranceCompany(tmp);
            refreshGrid();
        };
    }

    private ComponentEventListener<ItemClickEvent<InsuranceCompany>> gridInsuranciesListener() {
        return e -> {
            fieldInsurance.setValue(e.getItem().getCompanyName());
            actualInsuranceCompany = e.getItem();
        };
    }

    private void refreshGrid() {
        gridInsurancies.setItems(insuranceService.getAllInsuranceCompanies());
    }

    /**
     * This model binds properties between InsuranceForm and insurance-form
     */
    public interface InsuranceCompanyFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
