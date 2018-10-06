/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Suhas
 */
@WebServlet(name = "StartPageServlet", urlPatterns = {"/StartPageServlet"})
public class StartPageServlet extends HttpServlet {

    @Resource(name = "jdbc/HW2DB")
    private DataSource dataSource;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          
          Connection connection = dataSource.getConnection();
          String selectSQL = "select * from votes";
          PreparedStatement selectStatement = connection.prepareStatement(selectSQL);

          ResultSet rs = selectStatement.executeQuery();
          while (rs.next()){
            out.println(rs.getString("musicType")+" "+rs.getInt("numVotes")+"<br/>");
          }
          // look up matching records and display them
          int counter = (int) request.getSession().getAttribute("counter");
          counter++;
          request.getSession().setAttribute("counter", counter);
          out.println("session counter: "+ request.getSession().getAttribute("counter"));
          
        }catch(Exception e){
          out.println(e.getMessage());
          e.printStackTrace();
        }finally {      
          out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
