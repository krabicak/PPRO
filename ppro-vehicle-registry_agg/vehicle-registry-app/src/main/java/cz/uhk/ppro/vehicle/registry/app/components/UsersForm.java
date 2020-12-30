package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceCompanyService;
import cz.uhk.ppro.vehicle.registry.app.services.InsuranceEmployeeService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
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
    @Autowired
    private InsuranceCompanyService insuranceCompanyService;
    @Autowired
    private InsuranceEmployeeService insuranceEmployeeService;
    @Id("vaadinHorizontalLayoutUsers")
    private HorizontalLayout vaadinHorizontalLayoutUsers;
    @Id("vaadinVerticalLayoutUsers")
    private Element vaadinVerticalLayoutUsers;
    @Id("gridUsers")
    private Grid<User> gridUsers;
    @Id("ButtonDeleteUser")
    private Button buttonDeleteUser;
    @Id("buttonEditUser")
    private Button buttonEditUser;

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
    @Id("fieldSearch")
    private TextField fieldSearch;
    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;
    @Id("buttonAddUser")
    private Button buttonAddUser;
    @Id("selectInsuranceCompany")
    private ComboBox<InsuranceCompany> selectInsuranceCompany;
    @Id("fieldPassword")
    private PasswordField fieldPassword;
    @Id("fieldBornnum")
    private TextField fieldBornnum;

    //private CustomerService service = CustomerService.getInstance();

    /**
     * Creates a new UsersForm.
     */
    @PostConstruct
    public void init() {
        //hledat
        fieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        fieldSearch.addValueChangeListener(e -> updateList());

        //grid
        gridUsers.addColumn(User::getRole).setHeader("Role");
        gridUsers.addColumn(User::getLogin).setHeader("Login");
        gridUsers.addColumn(user -> user.getPerson().getFirstName()).setHeader("Jméno");
        gridUsers.addColumn(user -> user.getPerson().getLastName()).setHeader("Příjmení");
        gridUsers.addColumn(User::isEnable).setHeader("Aktivní");
        gridUsers.addColumn(user -> user.getPerson().getBornNum()).setHeader("Rodné číslo");

        gridUsers.getColumns().forEach(col -> col.setAutoWidth(true));
        refreshGrid();

        //form vpravo
        fieldIdUser.setVisible(false);
        fieldIdPerson.setVisible(false);

        gridUsers.addItemClickListener(gridCLickListener());

        //volic role
        radioRole.setItems(User.UserRole.ADMIN, User.UserRole.CLERK, User.UserRole.INSURER);
        radioRole.addValueChangeListener(radioRoleListener());
        radioRole.setValue(User.UserRole.ADMIN);

        //select pojistovny
        selectInsuranceCompany.setItems(insuranceCompanyService.getAllInsuranceCompanies());
        selectInsuranceCompany.setItemLabelGenerator(InsuranceCompany::getCompanyName);

        //tlacitka
        buttonAddUser.addClickListener(buttonAddUserListener());
        buttonEditUser.addClickListener(buttonEditUserListener());
        buttonDeleteUser.addClickListener(buttonDeletUserListener());
    }

    private ComponentEventListener<ItemClickEvent<User>> gridCLickListener() {
        return e -> {
            actualUser = e.getItem();

            fieldIdUser.setValue(e.getItem().getIdUser().toString());
            fieldLogin.setValue(e.getItem().getLogin());

            fieldIdPerson.setValue(e.getItem().getPerson().getIdPerson().toString());
            fieldName.setValue(e.getItem().getPerson().getFirstName());
            fieldSurname.setValue(e.getItem().getPerson().getLastName());
            radioRole.setValue(e.getItem().getRole());
            fieldBornnum.setValue(e.getItem().getPerson().getBornNum());
            checkBoxActive.setValue(e.getItem().isEnable());
        };
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<RadioButtonGroup<User.UserRole>, User.UserRole>> radioRoleListener() {
        return e -> {
            selectInsuranceCompany.setEnabled(radioRole.getValue().equals(User.UserRole.INSURER));
        };
    }


    private void updateList() {

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
            if (!fieldPassword.getValue().isEmpty()) {
                actualUser.setPassword(DigestUtils.sha256Hex(fieldPassword.getValue()));
            }

            actualUser.setRole(radioRole.getValue());

            actualUser.getPerson().setFirstName(fieldName.getValue());
            actualUser.getPerson().setLastName(fieldSurname.getValue());
            actualUser.setEnable(checkBoxActive.getValue());
            actualUser.getPerson().setBornNum(fieldBornnum.getValue());

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
        return e -> {
            User tmpUser = new User();
            tmpUser.setLogin(fieldLogin.getValue());
            tmpUser.setPassword(DigestUtils.sha256Hex(fieldPassword.getValue()));
            tmpUser.setRole(radioRole.getValue());

            Person tmpPerson = new Person();
            tmpPerson.setFirstName(fieldName.getValue());
            tmpPerson.setLastName(fieldSurname.getValue());
            tmpPerson.setBornNum(fieldBornnum.getValue());

            tmpUser.setPerson(tmpPerson);

            if (radioRole.getValue().equals(User.UserRole.INSURER)) {
                InsuranceEmployee ie = new InsuranceEmployee();
                ie.setInsuranceCompany(selectInsuranceCompany.getValue());
                //ie.setUser(tmpUser);
                try {
                    insuranceEmployeeService.addOrUpdateInsuranceEmployee(ie);
                } catch (PersonException personException) {
                    dialogService.showErrorDialog(personException);
                }
            }

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
