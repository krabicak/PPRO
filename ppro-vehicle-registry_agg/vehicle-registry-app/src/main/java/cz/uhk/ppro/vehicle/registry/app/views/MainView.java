package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "", layout = InternalLayout.class)
@RouteAlias(value = "main", layout = InternalLayout.class)
@RouteAlias(value = "main-page", layout = InternalLayout.class)
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
    }



    public interface MainViewModel extends TemplateModel {
    }
}