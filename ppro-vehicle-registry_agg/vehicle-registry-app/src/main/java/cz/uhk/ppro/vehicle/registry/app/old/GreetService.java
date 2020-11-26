package cz.uhk.ppro.vehicle.registry.app.old;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Deprecated
@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }

}
