package Utils.Other;

import GestioneProdotto.Service.GestioneProdottoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Prodotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/index.jsp")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(((Date)req.getServletContext().getAttribute("dataProdottiPiuVenduti")).before(new Date())){
            GestioneProdottoService serviceProdotto= new GestioneProdottoService();
            req.getServletContext().setAttribute("dataProdottiPiuVenduti", new Date());
            req.getServletContext().setAttribute("prodottiPiuVenduti", serviceProdotto.getProdottiPiuVenduti());
        }

        RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
