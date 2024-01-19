package Utils.Other;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidazioneInterceptor implements Filter {
    private ServletContext servletContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext=filterConfig.getServletContext();
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Operazioni da eseguire prima di passare alla servlet
        if (servletContext.getAttribute("errore-validazione") != null) {
            this.redirectToErrorPage((HttpServletRequest) request, (HttpServletResponse) response);
        } else{
            // Chiamare la servlet successiva nella catena
            chain.doFilter(request, response);
        }
    }

    private void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {// Recupera l'URL della pagina di errore dalla configurazione o utilizza un URL fisso
        // Esegui la ridirezione
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/erroreValidazione.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    public void destroy() {
        // Operazioni di cleanup (può essere lasciato vuoto se non necessario)
    }
}
