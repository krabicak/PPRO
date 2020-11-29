package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the admin-page template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("admin-page")
@JsModule("./src/views/admin-page.js")
public class AdminPage extends PolymerTemplate<AdminPage.AdminPageModel> {

    /**
     * Creates a new AdminPage.
     */
    public AdminPage() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between AdminPage and admin-page
     */
    public interface AdminPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
