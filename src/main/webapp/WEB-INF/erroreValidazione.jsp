<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Errore Validazione Dati BeerCellar</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_errore.css" type="text/css">
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp">
          <jsp:param name="header" value=""/>
        </jsp:include>


        <div class="contenitore_elementi_pagina_errore">
            <h1>ERRORE</h1>
            <h2>Validazione dei dati nel Database</h2>
            <p>&Egrave; avvenuto un errore durante la fase di avvio del sistema.</p>
            <p><%=application.getAttribute("errore-validazione")%></p>
        </div>

        <div class="background_image">
            <img src="${pageContext.request.contextPath}/static/image/pngaaa.com-1812991.png" alt="beer" id="beer-image1">
        </div>

        <jsp:include page="/WEB-INF/footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>

    </body>
</html>