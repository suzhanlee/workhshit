package servlet;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Model {

    String viewName;
    HashMap<String, Object> model = new HashMap<>();

    public Model(String viewPath) {
        this.viewName = viewPath;
    }
}
