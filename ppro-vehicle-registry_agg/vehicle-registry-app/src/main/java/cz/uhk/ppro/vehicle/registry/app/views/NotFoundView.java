package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.*;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;

import javax.servlet.http.HttpServletResponse;

public class NotFoundView extends Div implements HasErrorParameter<NotFoundException> {

    private final Label error = new Label();

    public NotFoundView() {
        add(error);
    }

    @Override
    public int setErrorParameter(
            BeforeEnterEvent event,
            ErrorParameter<NotFoundException> parameter) {

        error.setText("Cannot find URL: " + event.getLocation().getPath());
        return HttpServletResponse.SC_NOT_FOUND;
    }
}