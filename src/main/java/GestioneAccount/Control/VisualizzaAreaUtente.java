package GestioneAccount.Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import java.io.IOException;

@WebServlet("/visualizzaAreaUtente")
public class VisualizzaAreaUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(((Account)req.getSession().getAttribute("account")).getId() != -1){
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/areaUtente.jsp");
            dispatcher.forward(req,resp);
        }else {
            resp.sendRedirect("visualizzaLogin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
