package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("main-layout-top-bar")
@JsModule("./src/views/main-layout-top-bar.js")
public class MainLayoutTopBar extends PolymerTemplate<MainLayoutTopBar.MainLayoutTopBarModel> {

    @Id("vaadinHorizontalLayout")
    private HorizontalLayout vaadinHorizontalLayout;

    /**
     * Creates a new MainLayoutTopBar.
     */
    public MainLayoutTopBar() {
        // You can initialise any data required for the connected UI components here.
    }

    public void addComponent(Component component) {
        vaadinHorizontalLayout.add(component);
    }

    /**
     * This model binds properties between MainLayoutTopBar and main-layout-top-bar
     */
    public interface MainLayoutTopBarModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
