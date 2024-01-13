<%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 27/12/2023
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Effettua Ordine</title>
</head>
<body>
    <form action="effettuaOrdine" method="post">
        <label>nome</label>
        <input type="text" name="nome" pattern="^[a-zA-Z]{5,30}$">
        <label>cognome</label>
        <input type="text" name="cognome" pattern="^[a-zA-Z]{5,30}$">
        <label>numero carta</label>
        <input type="text" name="carta" pattern="^\d{4}\s\d{4}\s\d{4}\s\d{4}$">
        <label>data</label>
        <input type="date" name="dataScadenza">
        <label>CVV</label>
        <input type="text" name="cvv" pattern="^\d{3}$">
        <label>nome carta</label>
        <input type="text" name="nomeCarta" pattern="^[a-zA-Z]{5,30}$">
        <label>indirizzo</label>
        <input type="text" name="indirizzo" pattern="^[a-zA-Z0-9 ]{3,30}$">
        <label>citta</label>
        <input type="text" name="citta" pattern="^[a-zA-Z]{5,30}$">
        <label>cap</label>
        <input type="text" name="cap" pattern="^\d{5}$">
        <label>provincia</label>
        <input type="text" name="provincia" pattern="^[a-zA-Z]{5,30}$">
        <input type="submit" value="Procedi al pagamento">
    </form>
</body>
</html>
