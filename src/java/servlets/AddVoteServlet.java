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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Suhas
 */
@WebServlet(name = "AddVoteServlet", urlPatterns = {"/AddVoteServlet"})
public class AddVoteServlet extends HttpServlet {
    
    @Resource(name = "jdbc/HW2DB")
    private DataSource dataSource;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession currSession = request.getSession();
        MusicVoteBean currBean = (MusicVoteBean) currSession.getAttribute("votedMusicType");
        
        
        PrintWriter out = response.getWriter();
        int currVotes=-1;

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddVoteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            
            /*Testing beans*/
            out.println(currBean.getMusicType());
            out.println(currBean.getNewMusicType());
            
            out.println("</br> Below is from vote.html </br>");
            
            
            /* Testing out if the database returns my musictype and numvotes */
            String musicType = currBean.getMusicType();
            String newMusicType = currBean.getNewMusicType();
            Connection connection = dataSource.getConnection();
            /* Here I want to check the current number of votes in either Pop or Rock, as long as it is not null */
            if (musicType!=null) {
                String sqlSelect = "select * from votes where musictype=?";
                PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
                selectStatement.setString(1, musicType);
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    String mt = resultSet.getString("MUSICTYPE");
                    currVotes = resultSet.getInt("NUMVOTES");
                    //out.println(mt+": "+currVotes + "<br/>");
                }
                resultSet.close();                     
                selectStatement.close();
            
            /* Inserting into the database the vote without bean*/
            /**currVotes++;
            String sqlUpdate = "update votes set numvotes=? where musictype=?";
            PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
            updateStatement.setInt(1, currVotes);
            updateStatement.setString(2, musicType);
            updateStatement.executeUpdate();
            connection.close();
            * */
            
            /*Inserting into database the vote with bean*/
                currVotes++;
                String sqlUpdate = "update votes set numvotes=? where musictype=?";
                PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
                updateStatement.setInt(1, currVotes);
                updateStatement.setString(2, musicType);
                updateStatement.executeUpdate();
            }
            
            if (newMusicType!=null) {
                String sqlInsert = "insert into votes (musictype, numvotes) values(?,?)";
                PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
                insertStatement.setString(1, newMusicType);
                insertStatement.setInt(2, 1);
                insertStatement.executeUpdate();
            }      
            
            
            
                   
            
            connection.close();  
            
            
            
            
            
            out.println("</body>");
            out.println("</html>");
            

            
            
            
        }
        
        catch (Exception e) {
            out.println("Error Occurred "+e.getMessage());
            e.printStackTrace();
        } finally {
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
