<%@ page import="model.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_homepage.css" type="text/css">
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="header" value=""/>
        </jsp:include>

        <div class="contenitore_scoperta">
            <form action="" method="post" class="contenitore_bottone_scopri">
                <h1>Dal classico al fuori dagli schemi: trova la tua birra ideale qui!</h1>
                <input type="submit" value="Scopri il catalogo">
            </form>
        </div>







        <h2 class="h2_padding">Prodotti piu venduti</h2>
        <div class="contenitore_prodotti_homepage">
            <%for(Prodotto prodotto : ((ArrayList<Prodotto>)application.getAttribute("prodottiPiuVenduti"))) {%>
            <div class="contenitore_prodotto_homepage">
                <div class="contenitore_immagine_homepage">
                    <a href="visualizzaProdotto?id_prodotto=<%=prodotto.getId()%>">
                        <img class="" src="${pageContext.request.contextPath}<%="/upload/ID_"+prodotto.getId()+".png"%>">
                    </a>
                </div>
                <h3><%=prodotto.getNome()%></h3>
                <form action="visualizzaProdotto" method="get" class="contenitore_bottone_acquista">
                    <input type="submit" value="Acquista" class="button-acquista">
                    <input type="hidden" value="<%=prodotto.getId()%>" name="id_prodotto">
                </form>
            </div>
            <%}%>
        </div>


        <jsp:include page="footer.html">
            <jsp:param name="footer" value=""/>
        </jsp:include>
    </body>
</html>
