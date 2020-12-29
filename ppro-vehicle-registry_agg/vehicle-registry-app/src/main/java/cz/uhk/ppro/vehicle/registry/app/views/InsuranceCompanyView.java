package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.uhk.ppro.vehicle.registry.app.components.InsuranceCompanyForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;

@Route(value = "insurance-company-view", layout = InternalLayout.class)
@PageTitle("Pojišťovny")
public class InsuranceCompanyView extends InsuranceCompanyForm {
}
