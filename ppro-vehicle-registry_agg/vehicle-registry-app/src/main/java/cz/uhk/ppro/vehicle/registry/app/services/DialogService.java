package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

@Service
public class DialogService {

    public void showErrorDialog(Exception e) {
        Dialog dialog = new Dialog();
        if (e.getLocalizedMessage() == null)
            dialog.add(new Text(e.toString()));
        else
            dialog.add(new Text(e.getLocalizedMessage()));
        dialog.open();
    }

    public void showNotification(String message) {
        Notification notification = new Notification(message, 3000);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }
}
