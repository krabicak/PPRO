package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;

/**
 * A Designer generated component for the users-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("users-form")
@JsModule("./src/views/users-form.js")
public class UsersForm extends PolymerTemplate<UsersForm.UsersFormModel> {


    @Id("vaadinHorizontalLayoutUsers")
    private HorizontalLayout vaadinHorizontalLayoutUsers;
    @Id("vaadinVerticalLayoutUsers")
    private Element vaadinVerticalLayoutUsers;
    @Id("fieldName")
    private TextField fieldName;
    @Id("fieldSurname")
    private TextField fieldSurname;
    @Id("gridUsers")
    private Grid<Person> gridUsers;
    @Id("buttonAddUser")
    private Button buttonAddUser;
    @Id("ButtonDeleteUser")
    private Button buttonDeleteUser;
    @Id("buttonEditUser")
    private Button buttonEditUser;

    /**
     * Creates a new UsersForm.
     */
    public UsersForm() {
        gridUsers.addColumn(Person::getFirstName).setHeader("Jméno");
        gridUsers.addColumn(Person::getLastName).setHeader("Přijmení");

        Person p1 = new Person();
        p1.setFirstName("Franta");
        p1.setLastName("Kokotu");

        Person p2 = new Person();
        p2.setFirstName("Laco");
        p2.setLastName("Demeter");

        gridUsers.setItems(p1,p2);
        gridUsers.addItemClickListener(event -> {
            fieldName.setValue(event.getItem().getFirstName());
            fieldSurname.setValue(event.getItem().getLastName());
        });
    }

    /**
     * This model binds properties between UsersForm and users-form
     */
    public interface UsersFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
