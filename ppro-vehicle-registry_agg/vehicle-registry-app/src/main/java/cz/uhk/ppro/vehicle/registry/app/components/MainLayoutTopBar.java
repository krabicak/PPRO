package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the main-layout-top-bar template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("main-layout-top-bar")
@JsModule("./src/views/main-layout-top-bar.js")
public class MainLayoutTopBar extends PolymerTemplate<MainLayoutTopBar.MainLayoutTopBarModel> {

    /**
     * Creates a new MainLayoutTopBar.
     */
    public MainLayoutTopBar() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between MainLayoutTopBar and main-layout-top-bar
     */
    public interface MainLayoutTopBarModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
