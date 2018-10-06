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
public class ANV2servlet extends HttpServlet {

    @Resource(name = "jdbc/HW2DB")
    private DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String newMusicType = request.getParameter("newmusic");
            Connection connection = dataSource.getConnection();
            boolean check = false;
            int currVotes = -1;
            int otherVotes = -1;
            
            /* Checks if the new music type already exists*/
            
            String sqlCheck = "select * from votes";
            PreparedStatement sqlCheckStatement = connection.prepareStatement(sqlCheck);
            ResultSet resultSet = sqlCheckStatement.executeQuery();
            while (resultSet.next()) {
                    String mt = resultSet.getString("MUSICTYPE");
                    if (mt.equals(newMusicType)) {
                        check=true;
                        currVotes = resultSet.getInt("NUMVOTES");
                    }
                    otherVotes = resultSet.getInt("NUMVOTES");
            }
            resultSet.close();    
            
            /* Inserts new music type only if it doesn't already exist */
            
            
            if (check==false) {
                String sqlInsert = "insert into votes (musictype, numvotes) values(?,?)";
                PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
                insertStatement.setString(1, newMusicType);
                insertStatement.setInt(2, 1);
                insertStatement.executeUpdate();
            }
            
            else {
                currVotes++;
                String sqlUpdate = "update votes set numvotes=? where musictype=?";
                PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
                updateStatement.setInt(1, currVotes);
                updateStatement.setString(2, newMusicType);
                updateStatement.executeUpdate();
                
            }
        }   
        
        catch (Exception e) {

            e.printStackTrace();
        } finally {

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
