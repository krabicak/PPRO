package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
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

    private Person actualPerson;
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

    /**
     * Creates a new UsersForm.
     */
    @PostConstruct
    public void init() {
        //grid
        //gridUsers.addColumn(User::getIdUser).setHeader("ID User");
        gridUsers.addColumn(User::getRole).setHeader("Role");
        gridUsers.addColumn(User::getLogin).setHeader("Login");
        //gridUsers.addColumn(User::getPassword).setHeader("Heslo");
        gridUsers.addColumn(user -> user.getPerson().getFirstName()).setHeader("Jméno");
        gridUsers.addColumn(user -> user.getPerson().getLastName()).setHeader("Příjmení");

        gridUsers.getColumns().forEach(col -> col.setAutoWidth(true));
        gridUsers.setItems(userService.getAllUsers());

        //form vpravo
        fieldIdUser.setVisible(false);
        fieldIdPerson.setVisible(false);

        gridUsers.addItemClickListener(event -> {
            actualPerson = event.getItem().getPerson();
            actualUser = event.getItem();
            //TODO actual user x person duplikatni
            fieldIdUser.setValue(event.getItem().getIdUser().toString());
            fieldLogin.setValue(event.getItem().getLogin());
            fieldPassword.setValue(event.getItem().getPassword());

            fieldIdPerson.setValue(event.getItem().getPerson().getIdPerson().toString());
            fieldName.setValue(event.getItem().getPerson().getFirstName());
            fieldSurname.setValue(event.getItem().getPerson().getLastName());
            radioRole.setValue(event.getItem().getRole());
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
            User tmpUser = actualUser;
            String login = fieldLogin.getValue();
            if (!tmpUser.getLogin().equals(login))
                tmpUser.setLogin(fieldLogin.getValue());

            tmpUser.setPassword(DigestUtils.sha256Hex(fieldPassword.getValue()));
            tmpUser.setIdUser(Long.valueOf(fieldIdUser.getValue()));
            tmpUser.setRole(radioRole.getValue());

            Person tmpPerson = new Person();
            tmpPerson.setFirstName(fieldName.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());

            tmpUser.setPerson(tmpPerson);

            try {
                userService.addOrUpdateUser(tmpUser);
            } catch (PersonException f) {
                f.printStackTrace();
                //TODO pridat dialog
            }
            refreshGrid();
        };
    }

    private void refreshGrid() {
        gridUsers.setItems(userService.getAllUsers());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddUserListener() {
        //TODO CELY
        return e -> {
           /* User tmp = new User();
            tmp.setLogin(fieldLogin.getValue());
            tmp.setPassword(fieldPassword.getValue());
            tmp.setIdUser(Long.valueOf(fieldId.getValue()));
            tmp.setRole(User.UserRole.ADMIN);

            try {
                userService.addOrUpdateUser(tmp);
            } catch (PersonException f) {
                f.printStackTrace();
                //TODO pridat dialog
            }
            gridUsers.setItems(userService.getAllUsers());*/
        };
    }

    /**
     * This model binds properties between UsersForm and users-form
     */
    public interface UsersFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
