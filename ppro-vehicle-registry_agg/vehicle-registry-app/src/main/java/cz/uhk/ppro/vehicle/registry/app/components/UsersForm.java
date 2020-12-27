package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.app.views.MainView;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the users-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("users-form")
@JsModule("./src/views/users-form.js")
public class UsersForm extends PolymerTemplate<UsersForm.UsersFormModel> {

    @Autowired
    private DialogService dialogService;
    @Autowired
    private UserService userService;
    @Id("vaadinHorizontalLayoutUsers")
    private HorizontalLayout vaadinHorizontalLayoutUsers;
    @Id("vaadinVerticalLayoutUsers")
    private Element vaadinVerticalLayoutUsers;
    @Id("gridUsers")
    private Grid<User> gridUsers;
    @Id("buttonAddUser")
    private Button buttonAddUser;
    @Id("ButtonDeleteUser")
    private Button buttonDeleteUser;
    @Id("buttonEditUser")
    private Button buttonEditUser;
    @Id("fieldPassword")
    private TextField fieldPassword;

    private User actualUser;
    @Id("fieldIdUser")
    private TextField fieldIdUser;
    @Id("fieldSurname")
    private TextField fieldSurname;
    @Id("fieldName")
    private TextField fieldName;
    @Id("fieldIdPerson")
    private TextField fieldIdPerson;
    @Id("fieldLogin")
    private TextField fieldLogin;
    @Id("radioRole")
    private RadioButtonGroup<User.UserRole> radioRole;
    @Id("checkBoxActive")
    private Checkbox checkBoxActive;

    /**
     * Creates a new UsersForm.
     */
    @PostConstruct
    public void init() {
        //grid
        gridUsers.addColumn(User::getRole).setHeader("Role");
        gridUsers.addColumn(User::getLogin).setHeader("Login");
        gridUsers.addColumn(user -> user.getPerson().getFirstName()).setHeader("Jméno");
        gridUsers.addColumn(user -> user.getPerson().getLastName()).setHeader("Příjmení");
        gridUsers.addColumn(user -> user.isEnable()).setHeader("Aktivní");

        gridUsers.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //form vpravo
        fieldIdUser.setVisible(false);
        fieldIdPerson.setVisible(false);

        gridUsers.addItemClickListener(event -> {
            actualUser = event.getItem();

            fieldIdUser.setValue(event.getItem().getIdUser().toString());
            fieldLogin.setValue(event.getItem().getLogin());
            fieldPassword.setValue(event.getItem().getPassword());

            fieldIdPerson.setValue(event.getItem().getPerson().getIdPerson().toString());
            fieldName.setValue(event.getItem().getPerson().getFirstName());
            fieldSurname.setValue(event.getItem().getPerson().getLastName());
            radioRole.setValue(event.getItem().getRole());
            if(event.getItem().isEnable()){
                checkBoxActive.setValue(true);
            }
            else{
                checkBoxActive.setValue(false);
            }
        });

        //volic role
        radioRole.setItems(User.UserRole.ADMIN, User.UserRole.CLERK, User.UserRole.INSURER);

        //tlacitka
        buttonAddUser.addClickListener(buttonAddUserListener());
        buttonEditUser.addClickListener(buttonEditUserListener());
        buttonDeleteUser.addClickListener(buttonDeletUserListener());
    }


    private ComponentEventListener<ClickEvent<Button>> buttonDeletUserListener() {
        return e -> {
            try {
                userService.removeUser(actualUser);
            } catch (PersonException personException) {
                personException.printStackTrace();
            }
            refreshGrid();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditUserListener() {
        return e -> {
            String login = fieldLogin.getValue();
            if (!actualUser.getLogin().equals(login))
                actualUser.setLogin(fieldLogin.getValue());

            actualUser.setPassword(DigestUtils.sha256Hex(fieldPassword.getValue()));
            actualUser.setRole(radioRole.getValue());

            actualUser.getPerson().setFirstName(fieldName.getValue());
            actualUser.getPerson().setLastName(fieldSurname.getValue());
            actualUser.setEnable(checkBoxActive.getValue());

            try {
                userService.addOrUpdateUser(actualUser);
            } catch (PersonException f) {
                dialogService.showErrorDialog(f);
            }
            refreshGrid();
        };
    }

    private void refreshGrid() {
        gridUsers.setItems(userService.getAllUsers());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddUserListener() {
        //dodelat!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return e -> {
           User tmpUser = new User();
            tmpUser.setLogin(fieldLogin.getValue());
            tmpUser.setPassword(fieldPassword.getValue());
            tmpUser.setRole(radioRole.getValue());

            Person tmpPerson = new Person();
            tmpPerson.setFirstName(fieldName.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());

            tmpUser.setPerson(tmpPerson);

            try {
                userService.addOrUpdateUser(tmpUser);
            } catch (PersonException f) {
                f.printStackTrace();
                dialogService.showErrorDialog(f);
            }
            refreshGrid();
        };
    }

    /**
     * This model binds properties between UsersForm and users-form
     */
    public interface UsersFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
