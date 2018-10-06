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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Suhas
 */
public class AV2servlet extends HttpServlet {
    
    @Resource(name = "jdbc/HW2DB")
    private DataSource dataSource;




    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

        //PrintWriter out = response.getWriter();
        try {
            int currVotes = -2;
            String musicType = request.getParameter("music");
            Connection connection = dataSource.getConnection();
            
            /* Check the current number of votes for musictype */
            String sql = "select numvotes from votes where musictype=?";
            PreparedStatement selectSql = connection.prepareStatement(sql);
            selectSql.setString(1, musicType);
            ResultSet resultSet = selectSql.executeQuery();
            while (resultSet.next()) {
                currVotes = resultSet.getInt("NUMVOTES");
            }
            currVotes++;
            
            /* Add one new votes, i.e. currVotes, to appropriate musictype */
            
            String sqlUpdate = "update votes set numvotes=? where musictype=?";
            PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
            updateStatement.setInt(1, currVotes);
            updateStatement.setString(2, musicType);
            updateStatement.executeUpdate();
            
        }
        
        catch (Exception e) {
            //out.println("Error Occurred "+e.getMessage());
            e.printStackTrace();
        } finally {
            //out.close();
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StartPageServlet");
        dispatcher.forward(request, response);

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
