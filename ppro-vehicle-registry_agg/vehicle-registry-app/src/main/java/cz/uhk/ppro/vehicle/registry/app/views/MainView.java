package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.components.UsersForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
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
    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;
    @Id("verticalLayout")
    private Element verticalLayout;
    @Id("vaadinTab1")
    private Tab vaadinTab1;
    @Id("vaadinTab2")
    private Tab vaadinTab2;
    @Id("usersForm")
    private UsersForm usersForm;
    @Id("tabUserManagement")
    private Tab tabUserManagement;
    @Id("tabNavigation")
    private Tabs tabNavigation;


    @PostConstruct
    public void init() {
        Tab tab1 = new Tab("Form1");
        //tab1.add(usersForm);
        tabNavigation.add(tab1);



    }



    public interface MainViewModel extends TemplateModel {
    }
}