package servlet.frontcontroller;
import servlet.Model;
import servlet.View;
import servlet.frontcontroller.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet("/")
public class FrontControllerV1 extends HttpServlet {
    HashMap<String, Controller> controllerMap = new HashMap<>();
    public FrontControllerV1() {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/historySelect.do", new HistorySelectController());
        controllerMap.put("/wifiNear.do", new WifiNearController());
        controllerMap.put("/wifiLoad.do", new WifiApiLoadController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        Controller controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        HashMap<String,String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            String parameter = request.getParameter(parameterName);
            paramMap.put(parameterName , parameter);
        }

        Model model = controller.process(paramMap);
        View view = new View(model.getViewName());
        view.render(model,request,response);
    }
}
