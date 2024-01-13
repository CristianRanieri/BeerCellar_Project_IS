<%@ page import="model.entity.Carrello" %>
<%@ page import="model.entity.Account" %>
<%@ page import="model.entity.ContenutoCarrello" %>
<%@ page import="model.entity.Prodotto" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
      <meta charset="UTF-8">
      <title>Carrello</title>
      <link rel="stylesheet" href="./static/css/css_errore.css" type="text/css">
      <link rel="stylesheet" href="./static/css/css_pagina_carrello.css" type="text/css">
  </head>
  <body>
  <jsp:include page="header.jsp">
      <jsp:param name="header" value=""/>
  </jsp:include>

  <%Carrello carrello= ((Account)session.getAttribute("account")).getCarrello();
    double prezzoTotale= (double) request.getAttribute("prezzoTotale");%>

  <!--ERRORE:-->
  <% if(request.getAttribute("cambiamenti")!=null){%>
  <div id="blocco-messaggio-cambiamenti">
      <h2> ATTENZIONE!</h2>
      <h3>Per alcuni prodotti il prezzo è variato o non sono piu disponibili, quelli non disponibili sono stati rimossi dal catalogo.</h3>
  </div>
  <% }%>

  <!--CARRELLO:-->
  <h3 id="h3_carrello">Carrello</h3>

  <% if(!carrello.isEmpty()){%>
  <div id="div_contenitore">
        <div class="div_tabella_prodotti_carrello">

            <table> <!-- Puoi personalizzare la tua tabella aggiungendo attributi come border, cellspacing, cellpadding, ecc. -->
                <tr>
                    <th>Prodotto</th>
                    <th>Prezzo</th>

                </tr>

                <%for(ContenutoCarrello cc: carrello.getContenutoCarrello()){
                    Prodotto prodotto= cc.getProdotto();%>
                <tr>
                    <td>
                        <div class="div_tuttoIlProdotto">
                            <div class="div_immagineProdotto">
                                <img src="static/image/bottle.png">
                            </div>
                            <div class="div_Prodotto">
                                <div class="div_nomeProdotto"><label name="nomeProdotto"><%= prodotto.getNome()%></label><br></div>
                                <div class="div_quantità"><label name="quantità"><%= cc.getQuantita()%></label><br></div>
                                <div class="div_bottone_rimuovi"><form action="rimuoviProdotto" method="post">
                                    <input type="hidden" name="id" value="<%= prodotto.getId()%>">
                                    <button class="button_rimuovi" type="submit" value="RIMUOVI X">RIMUOVI X</button></form></div> <!--l'elemento deve eliminarsi dal carrello-->
                            </div>
                        </div>
                    </td>
                    <td class="prezzo"><%= prodotto.getPrezzo()%></td>
                </tr>
                <%}%>
            </table>
        </div>

      <div class="div_carrelloTotale">

            <div id="div_subtotale">
                <label id="subtotale">Subtotale</label>
                <label id="prezzo_subtotale"><%= prezzoTotale%>€</label>
            </div>

            <div id="div_spedizione">
                <label id="spedizione">Costo spedizione</label>
                <label id="prezzo_spedizione">4,99€</label>
            </div>

            <div id="div_totale">
                <label id="totale">Totale</label>
                <% double prezzoTotalep= (prezzoTotale+ 4.99);
                    prezzoTotalep = BigDecimal.valueOf(prezzoTotalep).setScale(2, RoundingMode.HALF_UP).doubleValue();%>
                <label id="prezzo_totale"><%= prezzoTotalep%>€</label>
            </div>

            <div id="div_ProcediOrdine">
                <button id="button_procedi_ordine" type="submit" value="Procedi all'ordine">Procedi all'ordine</button>
            </div>

        </div>

    </div>
  <%}else {%>
  <div class="contenitore-messaggio">
      <h2>Il carrello è vuoto!</h2>
      <h2>Aggiungi degli articoli per effettuare un acquisto. </h2>
  </div>
  <%}%>

  <jsp:include page="footer.html">
      <jsp:param name="footer" value=""/>
  </jsp:include>

  </body>
</html>