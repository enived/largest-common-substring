package com.devine.lcs.servlets;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "main",
        urlPatterns = "/"
)
public class HomeWebServlet extends HttpServlet {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
