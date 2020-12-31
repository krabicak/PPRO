package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the person-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("person-form")
@JsModule("./src/views/person-form.js")
public class PersonForm extends PolymerTemplate<PersonForm.PersonFormModel> {

    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;
    @Id("cancelButton")
    private Button cancelButton;
    @Id("saveButton")
    private Button saveButton;
    @Id("lastNameField")
    private TextField lastNameField;
    @Id("firstNameField")
    private TextField firstNameField;
    @Id("bornNumField")
    private TextField bornNumField;

    private Person person;
    private ComponentEventListener cancelListener;
    private ComponentEventListener saveListener;

    public PersonForm(Person person, ComponentEventListener saveListener, ComponentEventListener cancelListener) {
        this.person = person;
        this.cancelListener = cancelListener;
        this.saveListener = saveListener;
    }

    /**
     * Creates a new PersonForm.
     */
    @PostConstruct
    public void init() {
        if (person != null) {
            bornNumField.setValue(person.getBornNum());
            firstNameField.setValue(person.getFirstName());
            lastNameField.setValue(person.getLastName());
        }
        cancelButton.addClickListener(cancelListener);
        saveButton.addClickListener(saveListener);
    }

    /**
     * This model binds properties between PersonForm and person-form
     */
    public interface PersonFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
