package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A Designer generated component for the users-page template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("users-page")
@JsModule("./src/views/users-page.js")
public class UsersPage extends PolymerTemplate<UsersPage.UsersPageModel> {

    @Id("gridUsers")
    private Grid<Person> gridUsers;
    @Id("verticalGrid")
    private Element verticalGrid;

    /**
     * Creates a new UsersPage.
     */
    public UsersPage() {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setFirstName("Pepa");
        p1.setLastName("Jahoda");
        personList.add(p1);

        gridUsers.addColumn(Person::getFirstName).setHeader("Jméno");
        gridUsers.addColumn(Person::getLastName).setHeader("Přijmení");
        gridUsers.setItems(p1);
        //gridUsers.setColumns(na);
    }

    /**
     * This model binds properties between UsersPage and users-page
     */
    public interface UsersPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
