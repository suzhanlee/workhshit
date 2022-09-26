package servlet.frontcontroller.controller;

import servlet.Model;

import java.util.HashMap;

public interface Controller {

    Model process(HashMap<String, String> paramMap);
}
