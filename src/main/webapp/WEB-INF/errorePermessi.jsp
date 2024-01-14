<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8">
    <title>Error Permessi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_errore.css" type="text/css">
  </head>

  <jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
  </jsp:include>

  </div>
    <div class="contenitore_elementi_pagina_errore">
      <h2>ERRORE</h2>
      <h1>OH NO!</h1>
      <h3>Ma &egrave; tutto OK!</h3>

      <% if(request.getAttribute("errore-prodotto-null")!=null){%>
        <p>Il prodotto di cui hai fatto richiesta al momento non &egrave; disponibile. Ritorna al nostro catalogo.</p>
      <%}else {%>
        <p>Non hai i permessi per accedere a questa pagina. Ritorna al nostro catalogo.</p>
      <%}%>

      <form action="index.jsp">
        <button type="submit" value="TORNA ALLA HOME">Torna alla Home</button>
      </form>
    </div>

    <div class="background_image">
      <img src="./static/image/pngaaa.com-1812991.png" alt="beer" id="beer-image1">
    </div>

  <jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
  </jsp:include>

  </body>
</html>