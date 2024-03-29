<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.entity.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.AcquistoProdotto" %>
<%@ page import="model.entity.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Ordini</title>
        <link rel="icon" href="${pageContext.request.contextPath}/static/image/favicon_BeerCellar.ico" type="image/x-icon">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon_BeerCellar.ico" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_ordini_utente.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_ordini_admin.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
        <% int numeroOrdini = (int) request.getAttribute("numeroOrdini");%>
    </head>

    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="header" value=""/>
        </jsp:include>

        <c:if test="${requestScope.error1}">
            <div id="blocco-messaggio-cambiamenti">
                <h2>ERRORE!</h2>
                <h3>Alcuni dati selezionati o inseriti non sono stati inviati o non rispettano il formato indicato, la esortiamo a riprovare.</h3>
            </div>
        </c:if>

        <div>
            <h1 id="ordini"><span>Ordini</span></h1>

            <% Account account= (Account) session.getAttribute("account");
                if(account.isGestore()){ %>
            <form action="ricercaOrdini">
                <div id="selezione">
                    <select name="tipoID" id="selezionaId">
                        <%if(request.getParameter("tipoID")!=null && request.getParameter("tipoID").equals("Utente")){%>
                            <option value="Utente" selected> ID Utente </option>
                            <option value="Ordine"> ID Ordine </option>
                        <%}else{%>
                            <option value="Ordine" selected> ID Ordine </option>
                            <option value="Utente"> ID Utente </option>
                        <%}%>
                    </select>

                    <%if(request.getParameter("numero")!=null){%>
                        <input type="text" name="numero" value="<%= request.getParameter("numero")%>" pattern="^\d{1,4}$" title="Numero da 1 a 4 cifre" required>
                    <%}else{%>
                        <input type="text" name="numero" pattern="^\d{1,4}$" title="Numero da 1 a 4 cifre" required>
                    <%}%>

                    <button type="submit" value="Ricerca">RICERCA</button>
                </div>
            </form>
            <%}%>

            <!--SUCCESSO:-->
            <% if(request.getAttribute("Successo")!=null){%>
                <div id="blocco-messaggio-successo">
                    <h2>Ordine Effettuato!</h2>
                    <h3>L'ordine è stato effettuato con successo.</h3>
                </div>
            <% }%>

            <%  ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
             if(ordini.isEmpty()){%>
                <% if(account.isGestore()){%>
                    <h2>Nessun ordine trovato.</h2>
                <%}else {%>
                    <h2>Non hai ancora effettuato nessun ordine, affrettati ad aggiungere dei prodotti al carrello ed effttuare un acquisto!</h2>
                <%}%>
            <%}%>

            <%for(Ordine ordine : ordini){%>

            <div class="divStoricoOrdini">

                <div class="divDatiOrdine">
                    <div>
                        <label>NUMERO ORDINE</label>
                        <h5>ORDINE-<%= ordine.getId()%></h5>
                    </div>

                    <div>
                        <label>IMPORTO</label>
                        <h5><%= ordine.getPrezzoTotale()%>€</h5>
                    </div>

                    <div>
                        <label>DATA</label>
                        <h5><%= ordine.getData()%></h5>
                    </div>

                    <div>
                        <label>INDIRIZZO SPEDIZIONE</label>
                        <h5> indirizzo: <%= ordine.getIndirizzo()%> </h5>
                        <h5> cap: <%= ordine.getCAP()%> </h5>
                    </div>
                </div>

                <%for(AcquistoProdotto acquisto : ordine.getProdotti()){%>
                        <div class="divProdottiAcquistati">
                            <div class="pack-foto">
                                <a href="visualizzaProdotto?id_prodotto=<%=acquisto.getProdotto().getId()%>"><img class="pack-image" src="${pageContext.request.contextPath}<%="/upload/ID_"+acquisto.getProdotto().getId()+".png"%>"></a>
                            </div>

                            <div class="pack">

                                <div class="pack-nomeProdotto">
                                    <h3><%= acquisto.getProdotto().getNome()%></h3>
                                </div>

                                <div class="pack-quantità">
                                    <h3><%= acquisto.getQuantita()%></h3>
                                </div>

                                <div class="pack-prezzo">
                                    <h3><%= acquisto.getPrezzoAcquisto()%>€</h3>
                                </div>
                            </div>
                        </div>
                    <%}%>
                </div>
            <%}%>

            <%if(request.getAttribute("ricerca")!=null){ %>
                <div style="margin: auto;display: block;width: 242px;">
                    <div style="display: inline-block">
                        <form action="ricercaOrdini">
                            <input name="tipoID" type="hidden" value="<%= request.getParameter("tipoID")%>">
                            <input name="numero" type="hidden" value="<%= request.getParameter("numero")%>">
                            <input name="offset" id="offset2" type="hidden" value="">
                            <button class="buttonMostraProdotti" id="precedente" > Precedente </button>
                        </form>
                    </div>

                    <div style="display: inline-block">
                        <form action="ricercaOrdini">
                            <input name="tipoID" type="hidden" value="<%= request.getParameter("tipoID")%>">
                            <input name="numero" type="hidden" value="<%= request.getParameter("numero")%>">
                            <input name="offset" id="offset" type="hidden" value="<%= numeroOrdini%>">
                            <button class="buttonMostraProdotti" id="successivo" > Successivo </button>
                        </form>
                    </div>
                </div>
            <%}else{%>
                <div style="margin: auto;display: block;width: 242px;">
                    <div style="display: inline-block">
                        <form action="visualizzaOrdini">
                            <input name="offset" id="offset2" type="hidden" value="">
                            <button class="buttonMostraProdotti" id="precedente" > Precedente </button>
                        </form>
                    </div>

                    <div style="display: inline-block">
                        <form action="visualizzaOrdini">
                            <input name="offset" id="offset" type="hidden" value="<%= numeroOrdini%>">
                            <button class="buttonMostraProdotti" id="successivo" > Successivo </button>
                        </form>
                    </div>
                </div>
            <%}%>
        </div>

        <script>
            let no= <%= request.getAttribute("nuoviOrdini")%>;
            let ot= <%= request.getAttribute("numeroOrdini")%>;
            document.getElementById("offset2").value= ot-no-10;

            if(no <= 9)
                document.getElementById("successivo").style.display= "none";
            else
                document.getElementById("successivo").style.display = "true";

            if(ot >= 11)
                document.getElementById("precedente").style.display= "true";
            else
                document.getElementById("precedente").style.display= "none";
        </script>

        <jsp:include page="footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>

    </body>
</html>
