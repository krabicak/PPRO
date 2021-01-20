package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import cz.uhk.ppro.vehicle.registry.app.components.MainLayoutTopBar;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CssImport("./styles/moje.css")
public class MainLayout
        extends VerticalLayout implements PageConfigurator, RouterLayout, BeforeEnterObserver {
    // Import a style sheet into the global scope

    @Autowired
    private SessionService sessionService;
    @Autowired
    private NavigatorService navigatorService;

    private MainLayoutTopBar mainLayoutTopBar;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!sessionService.isLogged()) {
            navigatorService.rerouteToLogin(event);
        }
    }

    @PostConstruct
    public void init() {
        this.setWidthFull();
        this.setHeightFull();
        this.setPadding(false);
        HorizontalLayout topbar = new HorizontalLayout();
        topbar.setWidthFull();
        mainLayoutTopBar = new MainLayoutTopBar();
        topbar.add(mainLayoutTopBar);
        add(topbar);

    }

    public MainLayoutTopBar getMainLayoutTopBar() {
        return mainLayoutTopBar;
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        LoadingIndicatorConfiguration conf = initialPageSettings.getLoadingIndicatorConfiguration();

        conf.setApplyDefaultTheme(false);

    }
}