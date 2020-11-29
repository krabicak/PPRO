package cz.uhk.ppro.vehicle.registry.app.old;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the contact-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Deprecated
@Tag("contact-form")
@JsModule("./src/views/contact-form.js")
public class ContactForm extends PolymerTemplate<ContactForm.ContactFormModel> {

    /**
     * Creates a new ContactForm.
     */
    public ContactForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between ContactForm and contact-form
     */
    public interface ContactFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
