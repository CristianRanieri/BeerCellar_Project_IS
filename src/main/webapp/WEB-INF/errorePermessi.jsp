<!DOCTYPE html>
<html lang="it">
  <head>
    <title>Error Permessi</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- Media Query -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_generic.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_top_bar.css" type="text/css"><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
    <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_errore.css" type="text/css">
  </head>

  <jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
  </jsp:include>

  </div>
    <div class="contenitore_elementi_pagina_errore">
      <h2>ERRORE</h2>
      <h1>OH NO!</h1>
      <h3>Ma è tutto OK!</h3>
      <p>Non hai i permessi per accedere a questa pagina. Ritorna al nostro catalogo.</p>
      <form action="index.jsp">
        <button type="submit" value="TORNA ALLA HOME">Torna alla Home</button>
      </form>
    </div>

    <div class="background_image">
      <img src="./static/image/pngaaa.com-1812991.png" alt="beer" id="beer-image1">
    </div>

  <jsp:include page="footer.jsp">
    <jsp:param name="footer" value=""/>
  </jsp:include>

  </body>
</html>