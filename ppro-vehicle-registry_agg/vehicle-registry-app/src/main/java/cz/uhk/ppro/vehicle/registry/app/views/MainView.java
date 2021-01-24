package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;
import cz.uhk.ppro.vehicle.registry.app.services.*;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Route(value = "", layout = InternalLayout.class)
@RouteAlias(value = "main", layout = InternalLayout.class)
@RouteAlias(value = "main-page", layout = InternalLayout.class)
@Tag("main-view")
@PageTitle("Vítejte")
@JsModule("./src/views/main-view.js")
public class MainView extends PolymerTemplate<MainView.MainViewModel> {

    @Autowired
    private LoginService loginService;
    @Autowired
    private DialogService dialogService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private NavigatorService navigatorService;
    @Autowired
    private UserService userService;

    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;
    @Id("verticalLayout")
    private Element verticalLayout;
    @Id("usersManagement")
    private Div usersManagement;
    @Id("insuranceCompanyManagement")
    private Div insuranceCompanyManagement;
    @Id("vehiclesManagement")
    private Div vehiclesManagement;
    @Id("insuranceManagement")
    private Div insuranceManagement;
    @Id("buttonPassword")
    private Button buttonPassword;
    @Id("fieldPassword1")
    private PasswordField fieldPassword1;
    @Id("fieldPassword2")
    private PasswordField fieldPassword2;
    @Id("h2Greeting")
    private H2 h2Greeting;


    @PostConstruct
    public void init() {
        if (loginService.isLoggedUserClerk()) {
            insuranceManagement.setVisible(false);
            insuranceCompanyManagement.setVisible(false);
            usersManagement.setVisible(false);
        } else if (loginService.isLoggedUserInsurer()) {
            vehiclesManagement.setVisible(false);
            insuranceCompanyManagement.setVisible(false);
            usersManagement.setVisible(false);
        }

        //tlacitka
        buttonPassword.addClickListener(buttonPasswordListener());

        //pozdrav
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);

        if(hour >= 0 && hour < 12){
            h2Greeting.setText("Dobré ráno, vítejte v Registru vozidel!");
        }else if(hour >= 12 && hour < 16){
            h2Greeting.setText("Dobré odpoledne, vítejte v Registru vozidel!");
        }else if(hour >= 16 && hour < 21){
            h2Greeting.setText("Dobrý večer, vítejte v Registru vozidel!");
        }else if(hour >= 21 && hour < 24){
            h2Greeting.setText("Dobrou noc, vítejte v Registru vozidel!");
        }
    }

    private ComponentEventListener<ClickEvent<Button>> buttonPasswordListener() {
        return e -> {
            try {
                if (fieldPassword1.getValue().equals(fieldPassword2.getValue())) {
                    User loggedUser = userService.getUserByLogin(sessionService.getLogin());
                    loggedUser.setPassword(DigestUtils.sha256Hex(fieldPassword1.getValue()));
                    userService.addOrUpdateUser(loggedUser);
                    dialogService.showNotification("Heslo úspěšně změněno");
                    fieldPassword1.setValue("");
                    fieldPassword2.setValue("");
                } else if (fieldPassword1.isEmpty() || fieldPassword2.isEmpty()) {
                    throw new RuntimeException("Heslo není vyplněno");
                } else {
                    throw new RuntimeException("Zadaná hesla se neshodují");
                }
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        };
    }


    public interface MainViewModel extends TemplateModel {
    }
}