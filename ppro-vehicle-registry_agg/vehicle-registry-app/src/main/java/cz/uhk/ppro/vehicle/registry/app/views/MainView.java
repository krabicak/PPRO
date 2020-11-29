package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.components.LoginForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
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
    private SessionService sessionService;

    @PostConstruct
    public void init() {

    }

    public interface MainViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}