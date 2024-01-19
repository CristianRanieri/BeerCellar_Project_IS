<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Errore 500 BeerCellar</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_errore.css" type="text/css">
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp">
            <jsp:param name="header" value=""/>
        </jsp:include>

        <div class="contenitore_elementi_pagina_errore">
            <h2>OH NO!</h2>
            <h1>Errore 500</h1>
            <h3>Errore del server</h3>

            <p>Ci scusiamo ma si &egrave; riscontrato un errore del server. Riprovare pi&ugrave; tardi.</p>

            <form action="index.jsp">
                <button type="submit" value="TORNA ALLA HOME">Torna alla Home</button>
            </form>
        </div>

        <div class="background_image">
            <img src="${pageContext.request.contextPath}/static/image/pngaaa.com-1812991.png" alt="beer" id="beer-image1">
        </div>

        <jsp:include page="/WEB-INF/footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>

    </body>
</html>