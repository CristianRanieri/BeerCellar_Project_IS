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
      <h1>ERRORE</h1>
      <h2>VALIDAZIONE DATI NEL DATABASE</h2>
      <p>E avvenuto un errore durante la fase di avvio del sistema.</p>
      <p><%= application.getAttribute("errore-validazione")%></p>

    </div>

    <div class="background_image">
      <img src="./static/image/pngaaa.com-1812991.png" alt="beer" id="beer-image1">
    </div>

  <jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
  </jsp:include>

  </body>
</html>