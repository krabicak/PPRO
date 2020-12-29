package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.uhk.ppro.vehicle.registry.app.components.VehicleForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;

@Route(value = "vehicle-view", layout = InternalLayout.class)
@PageTitle("Vozidla")
public class VehicleView extends VehicleForm {
}
