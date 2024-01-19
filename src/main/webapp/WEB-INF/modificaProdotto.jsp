<%@ page import="model.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Modifica prodotto</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_modifica_prodotto.css" type="text/css">
    </head>

    <body>
        <%Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");%>

        <jsp:include page="header.jsp">
            <jsp:param name="header" value=""/>
        </jsp:include>

        <!--ERRORE:-->
        <% if(request.getAttribute("errore-parametri-invalidi")!=null){%>
            <div id="blocco-messaggio-cambiamenti">
                <h3>Alcuni dati selezionati o inseriti non sono stati inviati o non rispettano il formato indicato, la esortiamo a riprovare.</h3>
            </div>
        <% }%>
        <!--ERRORE:-->

        <form action="modificaProdotto" enctype="multipart/form-data" method="post">
            <input type="hidden" name="id_prodotto" value="<%=prodotto.getId()%>">
            <h1>Modifica Prodotto</h1>

            <div id="div_prodotto">
                <div id="div_dati_prodotto">

                <h3 class="titolo">DATI PRODOTTO</h3>

                <h3><label for="nomeBirra">Nome Birra</label></h3>

                <input type="text" value="<%=prodotto.getNome()%>" id="nomeBirra" name="nomeBirra" pattern="^[a-zA-Z0-9 ]{3,30}$" title="Nome formato da lettere minuscole, maiuscole, numeri e spazzi da 3 a 30 caratteri" required>

                <h3><label for="formato">Formato</label></h3>
                <select id="formato" name="formato" required>

                <%if (prodotto.getFormato().equalsIgnoreCase("Bottiglia")){%>
                <option value="Bottiglia" selected>Bottiglia</option>
                <option value="Lattina">Lattina</option>
                <option value="Fusto">Fusto</option>
                <%}
                else if (prodotto.getFormato().equalsIgnoreCase("Lattina")){%>
                <option value="Bottiglia">Bottiglia</option>
                <option value="Lattina" selected>Lattina</option>
                <option value="Fusto">Fusto</option>
                <%}
                else if (prodotto.getFormato().equalsIgnoreCase("Fusto")){%>
                <option value="Bottiglia">Bottiglia</option>
                <option value="Lattina">Lattina</option>
                <option value="Fusto" selected>Fusto</option>
                <%}%>
                </select>

                <h3><label for="prezzo">Prezzo</label></h3>
                <input type="text" value="<%=prodotto.getPrezzo()%>" id="prezzo" name="prezzo" title="Prezzo formato da 1 a 5 cifre separate da un punto, dopo il punto massimo 2 cifre. es. 14.45  " pattern="^\d{1,3}\.\d{1,2}$" required>

                <h3><label for="fermentazione">Fermentazione</label></h3>
                <select id="fermentazione" name="fermentazione" required>
                <%
                ArrayList<String> fermentazioni = (ArrayList<String>) application.getAttribute("fermentazioni");
                for (String f : fermentazioni){
                if (f.equalsIgnoreCase(prodotto.getFermentazione())) {%>
                <option value="<%=f%>" selected><%=f%></option>
                <%}
                else {%>
                <option value="<%=f%>"><%=f%></option>
                <%}%>
                <%}%>
                </select>

                <h3><label for="stile">Stile</label></h3>
                <select id="stile" name="stile" required>
                <%
                ArrayList<String> stili = (ArrayList<String>) application.getAttribute("stili");
                for (String s : stili){
                if (s.equalsIgnoreCase(prodotto.getStile())) {%>
                <option value="<%=s%>" selected><%=s%></option>
                <%}
                else {%>
                <option value="<%=s%>"><%=s%></option>
                <%}%>
                <%}%>
                </select>

                <h3><label for="colore">Colore</label></h3>
                <select id="colore" name="colore" required>
                <%
                ArrayList<String> colori = (ArrayList<String>) application.getAttribute("colori");
                for (String colore : colori){
                if (colore.equalsIgnoreCase(prodotto.getColore())) {%>
                <option value="<%=colore%>" selected><%=colore%></option>
                <%}
                else {%>
                <option value="<%=colore%>"><%=colore%></option>
                <%}%>
                <%}%>
                </select>

                <h3><label for="tassoAlcolico">Tasso Alcolico(%)</label></h3>
                <input type="text" value="<%=prodotto.getTassoAlcolico()%>" id="tassoAlcolico" name="tassoAlcolico" title="Numero formato da 2 a 4 cifre separate da un punto, massimo 2 cifre dopo il punto" pattern="^\d{1,2}\.\d{1,2}$" required>
                </div>

                <div id="div_descrizioneProdotto">
                    <h3 class="titolo" for="descrizioneProdotto">DESCRIZIONE PRODOTTO</h3>
                    <div id="div_descrizioneProdottoTextArea">
                        <textarea  id="descrizioneProdotto" name="descrizione" minlength="8" maxlength="255" required><%=prodotto.getDescrizione()%></textarea>
                    </div>
                    <br>
                    <div class="div_glutine">
                        <label for="gluten">
                        <h3>Con Glutine</h3>
                        <%if (prodotto.isGlutine()){%>
                            <input type="checkbox" id="gluten" name="glutine" checked>
                        <%} else {%>
                            <input type="checkbox" id="gluten" name="glutine">
                        <%}%>
                        </label>
                    </div>
                    <div class="div_glutine">
                        <label for="inCatalogo">
                        <h3>In Catalogo</h3>
                        <%if (prodotto.isInCatalogo()){%>
                            <input type="checkbox" id="inCatalogo" name="inCatalogo" checked>
                        <%} else {%>
                            <input type="checkbox" id="inCatalogo" name="inCatalogo">
                        <%}%>
                        </label>
                    </div>

                    <h3><label for="immagineBirra">Immagine Birra</label></h3><!-- DA IMPLEMENTARE -->
                    <input type="file" value="Scegli un'immagine" id="immagineBirra" name="immagineBirra" accept="image/*"> <!-- per togliere choose file e mettere Scegli un'immagine si deve fare una personalizzazione e quindi sostituire lo style normale-->

                    <br>
                    <h3><label for="nomeBirrificio">Nome Birrificio</label></h3>
                    <input type="text" id="nomeBirrificio" name="nomeBirrificio" value="<%=prodotto.getBirrificio()%>" pattern="^[a-zA-Z0-9 ]{3,30}$" title="Nome formato da lettere minuscole, maiuscole, numeri e spazzi da 3 a 30 caratteri" required>
                </div>
            </div>
            <div id="div_bottone_modifica">
                <input type="submit" value="MODIFICA PRODOTTO" id="bottone_modifica_prodotto">
            </div>
        </form>

        <jsp:include page="footer.html">
        <jsp:param name="footer" value=""/>
        </jsp:include>
    </body>
</html>
