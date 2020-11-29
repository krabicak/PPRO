package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import org.springframework.stereotype.Service;

@Service
public class DialogService {

    public void showErrorDialog(Exception e){
        Dialog dialog = new Dialog();
        dialog.add(new Text(e.getLocalizedMessage()));
        dialog.open();
    }
}
