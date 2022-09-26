package servlet.frontcontroller.controller;

import servlet.Model;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;

public class HomeController implements Controller {


    @Override
    public Model process(HashMap<String, String> paramMap) {

        return new Model("index");

    }
}
