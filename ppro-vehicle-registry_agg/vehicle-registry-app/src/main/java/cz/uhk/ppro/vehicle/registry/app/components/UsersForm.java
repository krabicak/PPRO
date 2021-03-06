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
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Tag("users-form")
@JsModule("./src/views/users-form.js")
public class UsersForm extends PolymerTemplate<UsersForm.UsersFormModel> {

    private static final Logger logger = LoggerFactory.getLogger(UsersForm.class);

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
    @Id("buttonReset")
    private Button buttonReset;

    @PostConstruct
    public void init() {

        //grid
        gridUsers.addColumn(User::getRole).setHeader("Role").setSortable(true);
        gridUsers.addColumn(User::getLogin).setHeader("Login").setSortable(true);
        gridUsers.addColumn(user -> user.getPerson().getFirstName()).setHeader("Jméno").setSortable(true);
        gridUsers.addColumn(user -> user.getPerson().getLastName()).setHeader("Příjmení").setSortable(true);
        gridUsers.addColumn(User::isEnable).setHeader("Aktivní").setSortable(true);
        gridUsers.addColumn(user -> user.getPerson().getBornNum()).setHeader("Rodné číslo").setSortable(true);
        gridUsers.addColumn(user -> {
            InsuranceCompany company = insuranceCompanyService.getInsuranceCompany(user);
            if (company == null) return null;
            return company.getCompanyName();
        }).setHeader("Pojišťovna").setSortable(true);

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
        buttonDeleteUser.addClickListener(buttonDeleteUserListener());
        buttonReset.addClickListener(buttonResetListener());

        //pole
        fieldName.setRequiredIndicatorVisible(true);
        fieldSurname.setRequiredIndicatorVisible(true);
        fieldLogin.setRequiredIndicatorVisible(true);
        fieldBornnum.setRequiredIndicatorVisible(true);

        //vyhledavani
        fieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        fieldSearch.addValueChangeListener(fieldSearchListener());
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldSearchListener() {
        return e -> {

            gridUsers.setItems(userService.findUsersByKeyWord(fieldSearch.getValue()));

        };
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> fieldListener() {
        return e -> {
            if (fieldBornnum.isEmpty() || fieldName.isEmpty() || fieldLogin.isEmpty() || fieldSurname.isEmpty()) {
                buttonEditUser.setEnabled(false);
                buttonAddUser.setEnabled(false);
            } else {
                buttonEditUser.setEnabled(true);
                buttonAddUser.setEnabled(true);
            }

        };
    }


    private ComponentEventListener<ClickEvent<Button>> buttonResetListener() {
        return e -> {
            resetForm();
        };
    }

    private void resetForm() {
        gridUsers.select(null);
        fieldSurname.setValue("");
        fieldName.setValue("");
        fieldLogin.setValue("");
        fieldPassword.setValue("");
        fieldBornnum.setValue("");
        selectInsuranceCompany.setValue(null);

        actualUser = null;
        gridUsers.deselectAll();
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
            if (e.getItem().getRole().equals(User.UserRole.INSURER)) {
                selectInsuranceCompany.setValue(insuranceCompanyService.getInsuranceCompany(e.getItem()));
            } else {
                selectInsuranceCompany.setValue(null);
            }
            fieldBornnum.setValue(e.getItem().getPerson().getBornNum());
            checkBoxActive.setValue(e.getItem().isEnable());
        };
    }

    private HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<RadioButtonGroup<User.UserRole>, User.UserRole>> radioRoleListener() {
        return e -> {
            selectInsuranceCompany.setEnabled(radioRole.getValue().equals(User.UserRole.INSURER));
        };
    }


    private ComponentEventListener<ClickEvent<Button>> buttonDeleteUserListener() {
        return e -> {
            try {
                if (actualUser == null) {
                    throw new RuntimeException("Není vybrán žádný uživatel");
                }
                if (actualUser.getRole() == User.UserRole.INSURER) {
                    insuranceEmployeeService.removeInsuranceEmployee(actualUser);
                }
                userService.removeUser(actualUser);
                dialogService.showNotification("Uživatel smazán");
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
            refreshGrid();
            resetForm();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> buttonEditUserListener() {
        return e -> {
            try {
                if (actualUser == null) {
                    throw new RuntimeException("Není vybrán žádný uživatel");
                }
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

                if (radioRole.getValue().equals(User.UserRole.INSURER)) {
                    InsuranceEmployee employee = new InsuranceEmployee();
                    employee.setUser(actualUser);
                    employee.setInsuranceCompany(selectInsuranceCompany.getValue());
                    insuranceEmployeeService.addOrUpdateInsuranceEmployee(employee);
                } else {
                    userService.addOrUpdateUser(actualUser);
                }
                refreshGrid();
                resetForm();
                dialogService.showNotification("Uživatel upraven");


            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    private void refreshGrid() {
        gridUsers.setItems(userService.getAllUsers());
    }

    private ComponentEventListener<ClickEvent<Button>> buttonAddUserListener() {
        return e -> {
            try {
                User tmpUser = new User();
                tmpUser.setLogin(fieldLogin.getValue());
                tmpUser.setPassword(DigestUtils.sha256Hex(fieldPassword.getValue()));
                tmpUser.setRole(radioRole.getValue());

                Person tmpPerson = new Person();
                tmpPerson.setFirstName(fieldName.getValue());
                tmpPerson.setLastName(fieldSurname.getValue());
                tmpPerson.setBornNum(fieldBornnum.getValue());

                tmpUser.setPerson(tmpPerson);
                tmpUser.setEnable(checkBoxActive.getValue());

                if (radioRole.getValue().equals(User.UserRole.INSURER)) {

                    InsuranceEmployee ie = new InsuranceEmployee();
                    ie.setInsuranceCompany(selectInsuranceCompany.getValue());
                    ie.setUser(tmpUser);
                    insuranceEmployeeService.addOrUpdateInsuranceEmployee(ie);
                } else {
                    userService.addOrUpdateUser(tmpUser);
                }
                refreshGrid();
                resetForm();
                dialogService.showNotification("Uživatel přidán");
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }

    public interface UsersFormModel extends TemplateModel {

    }
}
