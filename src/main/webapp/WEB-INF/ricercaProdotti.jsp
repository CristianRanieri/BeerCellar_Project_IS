<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.Account" %>
<%@ page import="model.entity.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 15/01/2024
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogo prodotti</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotti_utente.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_prodotti_admin.css" type="text/css">
</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
</jsp:include>

<% int numeroProdotti = (int) request.getAttribute("numeroProdotti");
    ArrayList<String> stili = (ArrayList<String>) request.getServletContext().getAttribute("stili");
    ArrayList<String> colori = (ArrayList<String>) request.getServletContext().getAttribute("colori");
%>

<!--Barra filtri-->
<div class="filter-beer">
    <form action="<%=request.getAttribute("tipoRicerca")%>">
    <select id="stile" name="stile">
        <option value="tutti" >Stile</option>
        <% for(String stile : stili){%>
            <% if(request.getAttribute("stile")!=null && request.getAttribute("stile").equals(stile)){%>
                <option value="<%= stile%>" selected> <%= stile%> </option>
            <%} else{%>
                <option value="<%= stile%>"> <%= stile%></option>
            <%}%>
        <%}%>
    </select>

    <select id="colore" name="colore">
        <option value="tutti" >Colore</option>
        <% for(String colore : colori){%>
            <% if(request.getAttribute("colore")!=null && request.getAttribute("colore").equals(colore)){%>
                <option value="<%= colore%>" selected> <%= colore%> </option>
            <%} else{%>
                <option value="<%= colore%>"> <%= colore%></option>
            <%}%>
        <%}%>
    </select>


    <% if(request.getAttribute("tassoAlcolico")!= null){%>
        <input type="text" id="tasso_alcolico" name="tassoAlcolico" value="<%=request.getAttribute("tassoAlcolico")%>" placeholder="Tasso Alcolico(es. 6.0)">
    <%} else{%>
        <input type="text" id="tasso_alcolico" name="tassoAlcolico" placeholder="Tasso Alcolico(es. 6.0)">
    <%}%>

    <button type="submit" id="button_ricerca">Ricerca</button>
        <input type="hidden" value="<%=request.getParameter("formato")%>" name="formato">
    </form>
</div>

<!--Aggiunta del prodotto per il gestore-->
<%if(((Account)session.getAttribute("account")).isGestore()){%>
    <form action="visualizzaCreaProdotto" method="get">
        <div id="divButtonAggiungiP">
            <button type="submit" id="button_aggiungi_prodotto">Aggiungi Prodotto</button>
        </div>
    </form>
<%}%>

<!--Lista dei prodotti-->
<div class="lista-prodotti">

    <%for(Prodotto prodotto : ((ArrayList<Prodotto>)request.getAttribute("prodotti"))) {%>
    <div class="contenitore-prodotto">
        <div class="div_img">
           <a href="visualizzaProdotto?id_prodotto=<%=prodotto.getId()%>"> <img  class="img_prodotto" src="${pageContext.request.contextPath}<%="/upload/ID_"+prodotto.getId()+".png"%>"></a>
        </div>
        <h3><%=prodotto.getNome()%></h3>
        <%if (!prodotto.isGlutine()){%>
            <div class="contenitore_glutine"><i class="fa-solid fa-wheat-awn-circle-exclamation"></i></div>
        <%}%>
        <form action="visualizzaProdotto" method="get">
            <div class="div-button">
                <%if(((Account)session.getAttribute("account")).isGestore()){%>
                    <input type="submit" value="Vedi" class="button-acquista">
                <%} else {%>
                    <input type="submit" value="Acquista" class="button-acquista">
                <%}%>
                <input type="hidden" value="<%=prodotto.getId()%>" name="id_prodotto">
            </div>
        </form>
    </div>
    <%}%>
</div>


<div style="margin: auto;display: block;width: 242px;">
    <div style="display: inline-block">
        <form action="<%=request.getAttribute("tipoRicerca")%>">
            <input name="formato" type="hidden" value="<%= request.getParameter("formato")%>">
            <input name="stile" type="hidden" value="<%= request.getParameter("stile")%>">
            <input name="colore" type="hidden" value="<%= request.getParameter("colore")%>">
            <input name="tassoAlcolico" type="hidden" value="<%= request.getParameter("tassoAlcolico")%>">
            <input name="offset" id="offset2" type="hidden" value="">
            <button class="buttonMostraProdotti" id="precedente" > Precedente </button>
        </form>
    </div>

    <div style="display: inline-block">
        <form action="<%=request.getAttribute("tipoRicerca")%>">
            <input name="formato" type="hidden" value="<%= request.getParameter("formato")%>">
            <input name="stile" type="hidden" value="<%= request.getParameter("stile")%>">
            <input name="colore" type="hidden" value="<%= request.getParameter("colore")%>">
            <input name="tassoAlcolico" type="hidden" value="<%= request.getParameter("tassoAlcolico")%>">
            <input name="offset" id="offset" type="hidden" value="<%= numeroProdotti%>">
            <button class="buttonMostraProdotti" id="successivo" > Successivo </button>
        </form>
    </div>
</div>

<script>
    let no= <%= request.getAttribute("nuoviProdotti")%>;
    let ot= <%= request.getAttribute("numeroProdotti")%>;
    document.getElementById("offset2").value= ot-no-10;

    if(no <= 9)
        document.getElementById("successivo").style.display= "none";
    else
        document.getElementById("successivo").style.display = "true";

    if(ot >= 11)
        document.getElementById("precedente").style.display= "true";
    else
        document.getElementById("precedente").style.display= "none";
</script>


<jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
</jsp:include>

</body>
</html>
