package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.components.LoginForm;
import cz.uhk.ppro.vehicle.registry.app.components.UsersPage;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "main", layout = MainLayout.class)
@RouteAlias(value = "main-page", layout = MainLayout.class)
@Tag("main-view")
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

    @Id("buttonLogout")
    private Button buttonLogout;
    @Id("vaadinTabs")
    private Tabs vaadinTabs;
    @Id("verticalLayout")
    private Element verticalLayout;

    @PostConstruct
    public void init() {
        buttonLogout.addClickListener(getOnClickLogoutListener());
    }

    private ComponentEventListener<ClickEvent<Button>> getOnClickLogoutListener() {
        return e -> {
            loginService.logout();
            navigatorService.navigateToLogin();
        };
    }

    public interface MainViewModel extends TemplateModel {
    }
}