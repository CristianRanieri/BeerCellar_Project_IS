<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Utente</title>
    <link rel="stylesheet" href="./static/css/css_area_utente.css" type="text/css">
    <link rel="stylesheet" href="./static/css/css_errore.css" type="text/css">

</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
</jsp:include>

<c:if test="${requestScope.error2}">
    <div id="blocco-messaggio-cambiamenti">
        <h3>Parametri inseriti non validi, modifica credenziali non effettuata.</h3>
    </div>
</c:if>

<c:if test="${requestScope.error1}">
    <div id="blocco-messaggio-cambiamenti">
        <h3>Password inserita è uguale a quella attuale, modifica credenziali non effettuata.</h3>
    </div>
</c:if>

<c:if test="${requestScope.corretto}">
    <div id="blocco-messaggio-successo">
        <h3>Modifica credenziali account effettuata correttamente.</h3>
    </div>
</c:if>

<div class="contenitore_pagina_area_utente">

    <div class="contenitore_seleziona_opzione_area_utente">
        <form action="visualizzaAreaUtente">
            <input type="submit" value="Profilo">
            <input formaction="visualizzaOrdini" type="submit" value="Storico Ordini">
            <input formaction="logout" type="submit" value="Esci">
        </form>
    </div>

    <div id="divProfilo">
        <h1>Profilo</h1>

        <form action="modificaCredenziali" method="post">
            <div id="divInternoAProfilo">
                <div class="form-row">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" value="${account.nome}" maxlength="30" pattern="^[a-zA-Z]{5,30}$" title="Utilizare solo lettere minuscole o maiuscole minimo 5 massimo 30." required>
                </div>

                <div class="form-row">
                    <label for="email">E-mail</label>
                    <input type="text" id="email" value="${account.email}" readonly>
                </div>

                <div class="form-row">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="pass" placeholder="**************" minlength="8" maxlength="30" title="Da 8 a 30 caratteri, tra cui un numero, una maiuscola e un carattere speciale tra i seguenti !@#$%^&*" pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" required>
                </div>
            </div>
            <input type="submit" value="Salva Modifiche">
        </form>
    </div>
</div>

<jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
</jsp:include>

</body>
</html>
