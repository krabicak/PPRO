package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import cz.uhk.ppro.vehicle.registry.app.components.UserPanel;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.app.views.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class InternalLayout extends MainLayout {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private NavigatorService navigatorService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        super.init();
        if (sessionService.isLogged()) {
            prepare();
        }
    }

    private void prepare() {
        UserPanel userPanel = new UserPanel(loginService, navigatorService, sessionService, userService);
        super.getMainLayoutTopBar().addComponent(userPanel);

        Tabs tabs = new Tabs();

        Tab userTab = new Tab("Správa uživatelů");
        Tab mainTab = new Tab("Úvod");
        Tab vehicleTab = new Tab("Vozidla");
        Tab insuranceCompanyTab = new Tab("Pojišťovny");
        Tab insuranceTab = new Tab("Pojištění");

        Map<Tab, Class> map = new HashMap<>();

        map.put(userTab, UsersView.class);
        map.put(mainTab, MainView.class);
        map.put(vehicleTab, VehicleView.class);
        map.put(insuranceCompanyTab, InsuranceCompanyView.class);
        map.put(insuranceTab, InsuranceView.class);

        if (loginService.isLoggedUserAdmin()) {
            tabs.add(mainTab, userTab, vehicleTab, insuranceCompanyTab, insuranceTab);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });
        } else if (loginService.isLoggedUserClerk()) {
            tabs.add(mainTab, vehicleTab);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });

        } else if (loginService.isLoggedUserInsurer()) {
            tabs.add(mainTab, insuranceTab);

            tabs.addSelectedChangeListener(e -> {
                navigatorService.navigateToClass(map.get(e.getSource().getSelectedTab()));
            });
        }
        add(tabs);
    }
}
