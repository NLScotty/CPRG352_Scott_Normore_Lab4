/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.mylogin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.sait.mylogin.models.User;
import ca.sait.mylogin.services.AccountService;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Scott
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if(session.getAttribute("username") != null){
            String query = request.getQueryString();
            if(query != null && query.contains("logout")){
                session.invalidate();
                request.setAttribute("message", "Logout Successful");
            }else{
                response.sendRedirect("home");
                return;
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("message", "Username or Password is missing!");
        } else{
            AccountService account = new AccountService();
            User user = account.login(username, password);
            
            if(user.getUsername() != null){
                request.getSession().setAttribute("username",username);
                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("username",username);
                request.setAttribute("message","Username or password is invalid");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

}
