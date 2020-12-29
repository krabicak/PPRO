package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.app.views.InsuranceCompanyView;
import cz.uhk.ppro.vehicle.registry.app.views.MainView;
import cz.uhk.ppro.vehicle.registry.app.views.UsersView;
import cz.uhk.ppro.vehicle.registry.app.views.VehicleView;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class InternalLayout extends MainLayout {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private NavigatorService navigatorService;

    private Paragraph userName;
    private Button buttonLogout;
    private Div divUserComplete;

    @PostConstruct
    public void init() {
        super.init();
        if (sessionService.isLogged()) {
            prepare();
        }
    }

    private void prepare() {
        Div divUser = new Div();

        Paragraph userLabel = new Paragraph("Uživatel: ");
        divUser.add(userLabel);

        userName = new Paragraph();
        divUser.add(userName);

        buttonLogout = new Button();
        buttonLogout.setText("Odhlásit se");
        buttonLogout.addClickListener(getOnClickLogoutListener());


        divUserComplete = new Div();
        divUserComplete.setClassName("divUserComplete");
        divUserComplete.add(divUser);
        divUserComplete.add(buttonLogout);
        add(divUserComplete);

        String login = sessionService.getLogin();
        Person person = userService.getUserByLogin(login).getPerson();
        userName.setText(person.getFirstName() + " " + person.getLastName() + " (" + login + ")");

        Tabs tabs = new Tabs();

        if (loginService.isLoggedUserAdmin()) {
            Tab userTab = new Tab("Správa uživatelů");
            Tab mainTab = new Tab("Úvod");
            Tab vehicleTab = new Tab("Vozidla");
            Tab insuranceTab = new Tab("Pojišťovny");

            tabs.add(mainTab, userTab, vehicleTab,insuranceTab);

            Map<Tab, Class> map = new HashMap<>();

            map.put(userTab, UsersView.class);
            map.put(mainTab, MainView.class);
            map.put(vehicleTab, VehicleView.class);
            map.put(insuranceTab, InsuranceCompanyView.class);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });
        }
        else if(loginService.isLoggedUserClerk()){
            Tab mainTab = new Tab("Úvod");
            Tab vehicleTab = new Tab("Vozidla");

            tabs.add(mainTab, vehicleTab);

            Map<Tab, Class> map = new HashMap<>();

            map.put(mainTab, MainView.class);
            map.put(vehicleTab, VehicleView.class);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });

        }
        else if(loginService.isLoggedUserInsurer()){
            Tab mainTab = new Tab("Úvod");
            Tab insuranceTab = new Tab("Pojišťovny");

            tabs.add(mainTab,insuranceTab);

            Map<Tab, Class> map = new HashMap<>();

            map.put(mainTab, MainView.class);
            map.put(insuranceTab, InsuranceCompanyView.class);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });
        }
        add(tabs);
    }

    private ComponentEventListener<ClickEvent<Button>> getOnClickLogoutListener() {
        return e -> {
            loginService.logout();
            navigatorService.navigateToLogin();
        };
    }
}
