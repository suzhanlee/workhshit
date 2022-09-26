package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class View {

    private final String viewPath;

    public View(String viewPath) {
        this.viewPath = "/WEB-INF/" + viewPath + ".jsp";
    }


    public void render(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> modelMap = model.getModel();
        modelMap.forEach((key, value) -> request.setAttribute(key, value));
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
