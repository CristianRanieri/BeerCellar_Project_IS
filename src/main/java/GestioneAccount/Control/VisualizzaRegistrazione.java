package GestioneAccount.Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import java.io.IOException;

@WebServlet("/visualizzaRegistrazione")
public class VisualizzaRegistrazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(((Account)req.getSession().getAttribute("account")).getId() == -1){
            req.setAttribute("reg",true);
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/registrazione.jsp");
            dispatcher.forward(req,resp);
        }else {
            resp.sendRedirect("visualizzaAreaUtente");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
