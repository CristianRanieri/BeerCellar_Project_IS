package GestioneProdotto.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;
import model.entity.Prodotto;

import java.io.IOException;

@WebServlet(name = "modificaProdotto", value = "/modificaProdotto")
public class ModificaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
