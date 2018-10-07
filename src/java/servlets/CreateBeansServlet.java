/*
Author: Suhas Kodali
What? : This class receives requests from both AV2servlet and AVN2servlet. It uses the MusicVoteBean class
        An array of MusicVoteBean is created and populated with the entries from the database
        Additionally, session and context counter are both incremented here, however, the session counter
        is stored in the session and the context counter in the application context. 
        Lastly, this servlet forwards the list<MusicVoteBean> to the StartPageServlet
Date  : October 6, 2018
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "CreateBeansServlet", urlPatterns = {"/CreateBeansServlet"})
public class CreateBeansServlet extends HttpServlet {

    @Resource(name = "jdbc/HW2DB")
    private DataSource dataSource;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MusicVoteBean> listOfMusicVoteBeans = new ArrayList<>();
        String mT = null;
        int nV = 0;
        try {
          
          Connection connection = dataSource.getConnection();
          String selectSQL = "select * from votes";
          PreparedStatement selectStatement = connection.prepareStatement(selectSQL);

          ResultSet rs = selectStatement.executeQuery();
          while (rs.next()){
            mT = rs.getString("musicType");
            nV = rs.getInt("numVotes");
            MusicVoteBean newBean = new MusicVoteBean();
            newBean.setMusicType(mT);
            newBean.setNumVotes(nV);
            listOfMusicVoteBeans.add(newBean);
            //out.println(rs.getString("musicType")+" has "+rs.getInt("numVotes")+ " votes"+"<br/>");
          }
          // look up matching records and display them
          int counter = (int) request.getSession().getAttribute("counter");
          counter++;
          request.getSession().setAttribute("counter", counter);
          request.getSession().setAttribute("listOfBeans", listOfMusicVoteBeans);
          
          ServletContext sc = getServletContext();
          Integer contextCounter = (Integer) sc.getAttribute("contextCounter");
          contextCounter++;
          sc.setAttribute("contextCounter", contextCounter);

          
        }catch(Exception e){

          e.printStackTrace();
        }finally {      

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
