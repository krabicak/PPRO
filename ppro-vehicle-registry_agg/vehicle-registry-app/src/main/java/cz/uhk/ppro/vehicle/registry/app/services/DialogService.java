package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
//import cz.uhk.ppro.vehicle.registry.app.components.PersonForm;
import com.vaadin.flow.component.notification.Notification;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class DialogService {

    public void showErrorDialog(Exception e) {
        Dialog dialog = new Dialog();
        dialog.add(new Text(e.getLocalizedMessage()));
        dialog.open();
    }

    public void showNotification(String message) {
        Notification notification = new Notification(message, 3000);
        notification.open();
    }
}
