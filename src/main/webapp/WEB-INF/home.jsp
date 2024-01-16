<%@ page import="model.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotti_utente.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotti_admin.css" type="text/css"></head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="header" value=""/>
        </jsp:include>

        <h2>Prodotti piu venduti</h2>
        <!--Lista dei prodotti-->






        <div style="display: block;width: 80%;margin: auto">
            <div class="lista-prodotti">

                <%for(Prodotto prodotto : ((ArrayList<Prodotto>)application.getAttribute("prodottiPiuVenduti"))) {%>
                <div class="contenitore-prodotto">
                    <div class="div_img">
                        <a href="visualizzaProdotto?id_prodotto=<%=prodotto.getId()%>"> <img  class="img_prodotto" src="${pageContext.request.contextPath}<%="/upload/ID_"+prodotto.getId()+".png"%>"></a>
                    </div>
                    <h3><%=prodotto.getNome()%></h3>
                    <form action="visualizzaProdotto" method="get">
                        <div class="div-button">
                            <input type="submit" value="Acquista Prodotto" class="button-acquista">
                            <input type="hidden" value="<%=prodotto.getId()%>" name="id_prodotto">
                        </div>
                    </form>
                </div>
                <%}%>
            </div>
        </div>
        <jsp:include page="footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>
    </body>
</html>
