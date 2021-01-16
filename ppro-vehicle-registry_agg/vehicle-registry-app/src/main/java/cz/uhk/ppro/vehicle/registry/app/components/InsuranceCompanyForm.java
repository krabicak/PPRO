package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
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
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceCompanyService;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.exceptions.InsuranceCompanyException;
import cz.uhk.ppro.vehicle.registry.core.VehicleRegistryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(InsuranceCompanyForm.class);

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
    @Autowired
    private DialogService dialogService;

    private InsuranceCompany actualInsuranceCompany;
    @Id("buttonReset")
    private Button buttonReset;
    @Id("fieldSearch")
    private TextField fieldSearch;

    public InsuranceCompanyForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * Creates a new InsuranceForm.
     */
    @PostConstruct
    public void init() {

        //grid
        gridInsurancies.addColumn(InsuranceCompany::getCompanyName).setHeader("Jméno").setSortable(true);
        gridInsurancies.addItemClickListener(gridInsuranciesListener());
        refreshGrid();

        //tlacitko
        buttonAddInsurance.addClickListener(buttonAddInsuranceListener());
        buttonDeleteInsurance.addClickListener(buttonDeleteInsuranceListener());
        buttonEditInsurance.addClickListener(buttonEditInsuranceListener());
        buttonReset.addClickListener(buttonResetListener());

        //vyhledavani
        fieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        fieldSearch.addValueChangeListener(fieldSearchListener());
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldSearchListener() {
        return event -> {
            gridInsurancies.setItems(insuranceService.findInsuranceCompaniesByKeyWord(fieldSearch.getValue()));
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e -> {
            fieldInsurance.setValue("");
            actualInsuranceCompany = new InsuranceCompany();
            gridInsurancies.deselectAll();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditInsuranceListener() {
        return e -> {
            try {
                if (actualInsuranceCompany==null) throw new RuntimeException("Není vybrána žádná pojišťovna");
                actualInsuranceCompany.setCompanyName(fieldInsurance.getValue());
                insuranceService.addOrUpdateInsuranceCompany(actualInsuranceCompany);
                refreshGrid();
                dialogService.showNotification("Pojišťovna upravena");
            } catch (InsuranceCompanyException ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonDeleteInsuranceListener() {
        return e -> {
            insuranceService.removeInsuranceCompany(actualInsuranceCompany);
            refreshGrid();
            dialogService.showNotification("Pojišťovna smazána");
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddInsuranceListener() {
        return e -> {
            InsuranceCompany tmp = new InsuranceCompany();
            tmp.setCompanyName(fieldInsurance.getValue());
            try {
                insuranceService.addOrUpdateInsuranceCompany(tmp);
                refreshGrid();
                dialogService.showNotification("Pojišťovna přidána");
            } catch (InsuranceCompanyException ex) {
                dialogService.showErrorDialog(ex);
            }
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
