<%@ page import="model.entity.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.AcquistoProdotto" %>
<%@ page import="model.entity.Account" %><%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 27/12/2023
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordini</title>

    <% int numeroOrdini = (int) request.getAttribute("numeroOrdini");%>
</head>
<body>

<% Account account= (Account) request.getAttribute("account");
   if(account.isGestore()){ %>
    <form action="ricercaOrdini">
        <select name="tipoID">
            <%if(request.getParameter("tipoID")!=null && request.getParameter("tipoID").equals("Utente")){%>
                <option value="Utente" selected> ID Utente </option>
                <option value="Ordine"> ID Ordine </option>
            <%}else{%>
                <option value="Ordine" selected> ID Ordine </option>
                <option value="Utente"> ID Utente </option>
            <%}%>
        </select>
        <%if(request.getParameter("numero")!=null){%>
            <input type="text" name="numero" value="<%= request.getParameter("numero")%>">
        <%}else{%>
            <input type="text" name="numero">

        <%}%>
        <input type="submit" value="RICERCA">
    </form>
<%}%>


<%  ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
for(Ordine ordine : ordini){%>

    <h4>id ordine: <%= ordine.getId()%>, data Ordine: <%= ordine.getData()%>, PrezzoTotale: <%= ordine.getPrezzoTotale()%></h4>
    <h4>lista dei prodotti dell'ordine</h4>

    <%for(AcquistoProdotto acquisto : ordine.getProdotti()){%>
        <h4>nome prodotto: <%= acquisto.getProdotto().getNome()%>, prezzo acquisto: <%= acquisto.getPrezzoAcquisto()%>, quantita: <%= acquisto.getQuantita()%></h4>
    <%}%>
    <h2>---------------------------------------------------------------------------------------------------------------------<h2>
<%}%>


<%if(request.getAttribute("ricerca")!=null){ %>
    <form action="ricercaOrdini">
        <input name="tipoID" type="hidden" value="<%= request.getParameter("tipoID")%>">
        <input name="numero" type="hidden" value="<%= request.getParameter("numero")%>">
        <input name="offset" id="offset" type="hidden" value="<%= numeroOrdini%>">
        <button class="buttonMostraProdotti" id="successivo" > Successivo </button>
    </form>

    <form action="ricercaOrdini">
        <input name="tipoID" type="hidden" value="<%= request.getParameter("tipoID")%>">
        <input name="numero" type="hidden" value="<%= request.getParameter("numero")%>">
        <input name="offset" id="offset2" type="hidden" value="">
        <button class="buttonMostraProdotti" id="precedente" > Precedente </button>
    </form>
<%}else{%>
    <form action="visualizzaOrdini">
        <input name="offset" id="offset" type="hidden" value="<%= numeroOrdini%>">
        <button class="buttonMostraProdotti" id="successivo" > Successivo </button>
    </form>

    <form action="visualizzaOrdini">
        <input name="offset" id="offset2" type="hidden" value="">
        <button class="buttonMostraProdotti" id="precedente" > Precedente </button>
    </form>
<%}%>

    <script>
    let no= <%= request.getAttribute("nuoviOrdini")%>;
    let ot= <%= request.getAttribute("numeroOrdini")%>;
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

</body>

</html>
