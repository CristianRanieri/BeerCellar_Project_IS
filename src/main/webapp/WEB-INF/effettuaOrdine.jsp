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
        <input type="text" name="nome">
        <label>cognome</label>
        <input type="text" name="cognome">
        <label>numero carta</label>
        <input type="text" name="carta">
        <label>data</label>
        <input type="date" name="nome">
        <label>CVV</label>
        <input type="text" name="cvv">
        <label>nome carta</label>
        <input type="text" name="nomeCarta">
        <label>indirizzo</label>
        <input type="text" name="indirizzo">
        <label>citta</label>
        <input type="text" name="citta">
        <label>cap</label>
        <input type="text" name="cap">
        <label>provincia</label>
        <input type="text" name="provincia">
        <input type="submit" value="Procedi al pagamento">
    </form>
</body>
</html>
