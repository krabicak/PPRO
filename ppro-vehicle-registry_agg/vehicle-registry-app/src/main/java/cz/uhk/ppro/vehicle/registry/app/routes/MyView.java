package cz.uhk.ppro.vehicle.registry.app.routes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the my-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("my-view")
@JsModule("./src/views/my-view.js")
@Route("")
public class MyView extends PolymerTemplate<MyView.MyViewModel> {

    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;
    @Id("filterText")
    private TextField filterText;
    @Id("addContactButton")
    private Button addContactButton;
    @Id("grid")
    private Grid grid;


    /**
     * Creates a new MyView.
     */
    public MyView() {
    }


    /**
     * This model binds properties between MyView and my-view
     */
    public interface MyViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
