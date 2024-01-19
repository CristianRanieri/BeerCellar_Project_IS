<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Inserimento prodotto</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_modifica_prodotto.css" type="text/css">
    </head>

    <body>
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

        <form action="creaProdotto" enctype="multipart/form-data" method="post">

            <h1>Inserimento Prodotto</h1>

            <div id="div_prodotto">

                <div id="div_dati_prodotto">

                    <h3 class="titolo">DATI PRODOTTO</h3>

                    <h3><label for="nomeBirra">Nome Birra</label></h3>

                    <input type="text" id="nomeBirra" name="nomeBirra" title="Nome formato da lettere minuscole, maiuscole, numeri e spazzi da 3 a 30 caratteri" pattern="^[a-zA-Z0-9 ]{3,30}$" required>

                    <h3><label for="formato">Formato</label></h3>
                    <select id="formato" name="formato" required>
                        <option value="" disabled selected></option>
                        <option value="Bottiglia">Bottiglia</option>
                        <option value="Lattina">Lattina</option>
                        <option value="Fusto">Fusto</option>
                    </select>

                    <h3><label for="prezzo">Prezzo</label></h3>
                    <input type="text" id="prezzo" name="prezzo" pattern="^\d{1,3}\.\d{1,2}$" title="Prezzo formato da 1 a 5 cifre separate da un punto, dopo il punto massimo 2 cifre. es. 14.45" required>

                    <h3><label for="fermentazione">Fermentazione</label></h3>
                    <select id="fermentazione" name="fermentazione" required>
                        <option value="" disabled selected></option>
                        <%
                        ArrayList<String> fermentazioni = (ArrayList<String>) application.getAttribute("fermentazioni");
                        for (String fermentazione : fermentazioni){%>
                            <option value="<%=fermentazione%>"><%=fermentazione%></option>
                        <%}%>
                    </select>

                    <h3><label for="stile">Stile</label></h3>
                    <select id="stile" name="stile" required>
                        <option value="" disabled selected></option>
                        <% ArrayList<String> stili = (ArrayList<String>) application.getAttribute("stili");
                        for (String stile : stili){%>
                            <option value="<%=stile%>"><%=stile%></option>
                        <%}%>
                    </select>

                    <h3><label for="colore">Colore</label></h3>
                    <select id="colore" name="colore" required>
                        <option value="" disabled selected></option>
                        <% ArrayList<String> colori = (ArrayList<String>) application.getAttribute("colori");
                        for (String colore : colori){%>
                            <option value="<%=colore%>"><%=colore%></option>
                        <%}%>
                    </select>

                    <h3><label for="tassoAlcolico">Tasso Alcolico(%)</label></h3>
                    <input type="text" id="tassoAlcolico" name="tassoAlcolico" pattern="^\d{1,2}\.\d{1,2}$" title="Numero formato da 2 a 4 cifre separate da un punto, massimo 2 cifre dopo il punto" required>
                </div>

                <div id="div_descrizioneProdotto">
                    <h3 class="titolo" for="descrizioneProdotto">DESCRIZIONE PRODOTTO</h3>
                    <div id="div_descrizioneProdottoTextArea">
                        <textarea id="descrizioneProdotto" name="descrizione" minlength="8" maxlength="255" required></textarea>
                    </div>
                    <br>
                    <div class="div_glutine">
                        <label for="gluten">
                            <h3>Con Glutine</h3>
                            <input type="checkbox" id="gluten" name="glutine">
                        </label>
                    </div>

                    <h3><label for="immagineBirra">Immagine Birra</label></h3>
                    <input type="file" id="immagineBirra" name="immagineBirra" accept="image/*" required>

                    <br>
                    <h3><label for="nomeBirrificio">Nome Birrificio</label></h3>
                    <input type="text" id="nomeBirrificio" name="nomeBirrificio" pattern="^[a-zA-Z0-9 ]{3,30}$" title="Nome formato da lettere minuscole, maiuscole, numeri e spazzi da 3 a 30 caratteri" required>
                </div>
            </div>

            <div id="div_bottone_modifica">
                <input type="submit" value="INSERISCI PRODOTTO" id="bottone_modifica_prodotto">
            </div>

        </form>

        <jsp:include page="footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>
    </body>
</html>
