<%@ page import="model.entity.Account" %>
<%@ page import="model.entity.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
  <head>
    <meta charset="UTF-8">
    <title>Pagina Prodotto BeerCellar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- Media Query -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_generic.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotto_utente.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotto_admin.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_top_bar.css" type="text/css"><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
    <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
  </head>

  <body>

    <%Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");%>

    <jsp:include page="header.jsp">
      <jsp:param name="header" value=""/>
    </jsp:include>

    <%
      if (((Account) session.getAttribute("account")).isGestore()) {
    %>
        <form action="visualizzaModificaProdotto" method="post">
          <div id="tasto_modifica_prodotto">
            <input type="hidden" name="id_prodotto" value="<%=prodotto.getId()%>">
            <button type="submit" class="button_action" >Modifica Prodotto</button>
          </div>
        </form>

    <%}%>

    <div class="contenuto_prodotto">
      <div class="contenitore_immagine">
        <img src="${pageContext.request.contextPath}/static/image/bottle.png" alt="example">
      </div>

      <div class="contenitore_informazioni">
        <h2><%=prodotto.getNome()%></h2>
        <p><%=prodotto.getFormato()%></p>
        <p><%=prodotto.getStile()%></p>
        <p><%=prodotto.getColore()%></p>
        <h3>€<%=prodotto.getPrezzo()%></h3>

        <%
          if (!((Account) session.getAttribute("account")).isGestore()) {
        %>

          <form action="aggiungiProdotto">
            <div class="contenitore_selettore">
              <input type="hidden" name="id" value="<%=prodotto.getId()%>">
              <input type="number" name="quantita" value="1" min="1"/>
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
        <p>Descrizione testuale del prodotto e/o del birrificio</p>
      </div>
    </div>


    <jsp:include page="footer.jsp">
      <jsp:param name="footer" value=""/>
    </jsp:include>
  </body>
</html>
