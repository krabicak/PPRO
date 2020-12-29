package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.uhk.ppro.vehicle.registry.app.components.InsuranceForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;

@Route(value = "insurance-view", layout = InternalLayout.class)
@PageTitle("Pojišťovny")
public class InsuranceView extends InsuranceForm {
}
