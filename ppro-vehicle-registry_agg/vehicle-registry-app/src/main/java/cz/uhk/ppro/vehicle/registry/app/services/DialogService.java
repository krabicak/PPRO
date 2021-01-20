package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
public class DialogService {

    public void showErrorDialog(Exception e) {
        Dialog dialog = new Dialog();
        if (e.getLocalizedMessage() == null) {
            dialog.add(new Text(e.toString()));
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final String utf8 = StandardCharsets.UTF_8.name();
            try (PrintStream ps = new PrintStream(baos, true, utf8)) {
                e.printStackTrace(ps);
                dialog.add(new Text(baos.toString(utf8)));
            } catch (Exception ex) {
                showErrorDialog(ex);
            }
        } else
            dialog.add(new Text(e.getLocalizedMessage()));
        dialog.open();
    }

    public void showNotification(String message) {
        Notification notification = new Notification(message, 3000);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }
}
