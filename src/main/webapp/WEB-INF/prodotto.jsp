<%@ page import="model.entity.Account" %>
<%@ page import="model.entity.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
  <head>
    <meta charset="UTF-8">
    <title>Pagina Prodotto BeerCellar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotto_utente.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotto_admin.css" type="text/css">
  </head>

  <body>
    <%Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");%>

    <jsp:include page="header.jsp">
      <jsp:param name="header" value=""/>
    </jsp:include>

    <!--SUCCESSO:-->
    <% if(request.getParameter("Successo")!=null && ((Account)session.getAttribute("account")).isGestore()){%>
      <div id="blocco-messaggio-successo">
        <h2>Prodotto Inserito</h2>
        <h3>L'inserimento del nuovo prodotto è avvenuto con successo.</h3>
      </div>
    <% }%>
    <!--SUCCESSO:-->

    <!--SUCCESSO:-->
    <% if(request.getParameter("successo-modifica")!=null && ((Account)session.getAttribute("account")).isGestore()){%>
      <div id="blocco-messaggio-successo">
        <h2>Prodotto Modificato</h2>
        <h3>La modifica del prodotto è stata effettuata con successo.</h3>
      </div>
    <% }%>
    <!--SUCCESSO:-->

    <!--ERRORE:-->
    <% if(request.getParameter("errore-nessuna-modifica")!=null){%>
    <div id="blocco-messaggio-cambiamenti">
      <h3>Nessun parametro modificato, cambiamento non effettuato.</h3>
    </div>
    <% }%>
    <!--ERRORE:-->

    <%if (((Account) session.getAttribute("account")).isGestore()) {%>
        <form action="visualizzaModificaProdotto" method="post">
          <div id="tasto_modifica_prodotto">
            <input type="hidden" name="id_prodotto" value="<%=prodotto.getId()%>">
            <button type="submit" class="button_action" >Modifica Prodotto</button>
          </div>
        </form>

    <%}%>

    <div class="contenuto_prodotto">
      <div class="contenitore_immagine">
        <img src="${pageContext.request.contextPath}<%="/upload/ID_"+prodotto.getId()+".png"%>">
      </div>

      <div class="contenitore_informazioni">
        <h2><%=prodotto.getNome()%></h2>
        <%if (!prodotto.isGlutine()){%>
          <div class="contenitore_glutine"><i class="fa-solid fa-wheat-awn-circle-exclamation"></i> Gluten Free</div>
        <%}%>
        <p><%=prodotto.getFormato()%></p>
        <p><%=prodotto.getStile()%></p>
        <p><%=prodotto.getColore()%></p>
        <p><%=prodotto.getBirrificio()%></p>
        <p><%=prodotto.getFermentazione()%></p>
        <p><%=prodotto.getTassoAlcolico()%>%</p>
        <h3>€<%=prodotto.getPrezzo()%></h3>


        <%if (!((Account) session.getAttribute("account")).isGestore()) {%>
          <form action="aggiungiProdotto">
            <div class="contenitore_selettore">
              <input type="hidden" name="id" value="<%=prodotto.getId()%>">
              <input type="number" name="quantita" value="1" min="1" max="99">
            </div>

            <input type="submit" class="button_action" value="Aggiungi al carrello"/>
          </form>

          <hr>

          <div class="contenitore_decorazione_pagamento">
            Metodi di pagamento<br>
            <img src="${pageContext.request.contextPath}/static/image/payment-method-png-transparent-images-175362-6659871-1024x213.png" alt="metodi di pagamento">
          </div>
        <%}%>
      </div>

      <div class="contenitore_descrizione">
        <h2>Descrizione</h2>
        <p><%=prodotto.getDescrizione()%></p>
      </div>
    </div>

    <jsp:include page="footer.html">
      <jsp:param name="footer" value=""/>
    </jsp:include>
  </body>
</html>
