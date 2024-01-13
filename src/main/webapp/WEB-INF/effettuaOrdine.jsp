<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Effettua Ordine</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- Media Query -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_generic.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_top_bar.css" type="text/css"><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
    <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
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
