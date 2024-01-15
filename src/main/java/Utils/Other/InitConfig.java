package Utils.Other;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Comparator;

//questa classe serve per inserire le liste dello stile,colore e fascie  del tasso alcolico all'intero del applicationContext, le quali vengono
// utilizzate per creare le select nella pagina della ricerca dei prodotti e nella pagina pagina di inserimento di un nuvo prodotto.
@WebServlet(urlPatterns ="/myinit",loadOnStartup = 0)
public class InitConfig extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Comparator comparator= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        ArrayList<String> stili = new ArrayList<>();
        stili.add("IPA");
        stili.add("Lager");
        stili.add("Porter");
        stili.add("Ale");
        stili.add("APA");
        stili.add("Pils");
        stili.add("Lambic");
        stili.add("Stout");
        stili.add("Trappista");
        stili.sort(comparator);
        getServletContext().setAttribute("stili",stili);

        ArrayList<String> formati = new ArrayList<>();
        formati.add("Bottiglia");
        formati.add("Lattina");
        formati.add("Fusto");
        formati.sort(comparator);
        getServletContext().setAttribute("formati", formati);

        ArrayList<String> fermentazioni = new ArrayList<>();
        fermentazioni.add("Alta");
        fermentazioni.add("Bassa");
        fermentazioni.add("Mista");
        fermentazioni.add("Spontanea");
        fermentazioni.sort(comparator);
        getServletContext().setAttribute("fermentazioni", fermentazioni);

        ArrayList<String> colori = new ArrayList<>();
        colori.add("Bionda");
        colori.add("Blanche");
        colori.add("Ambrata");
        colori.add("Scura");
        colori.add("Rossa");
        colori.sort(comparator);
        getServletContext().setAttribute("colori",colori);

        ArrayList<String> tassoAlcolico= new ArrayList<>();
        tassoAlcolico.add("0-6");
        tassoAlcolico.add("7-14");
        tassoAlcolico.add("15-29");
        tassoAlcolico.add("30-49");
        tassoAlcolico.add("50-70");
        getServletContext().setAttribute("fascieTassoAlcolico",tassoAlcolico);

        super.init();
    }
}
