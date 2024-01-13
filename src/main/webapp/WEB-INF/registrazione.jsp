<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 28/12/2023
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" href="static/css/css_pagina_registrazione_e_login.css" type="text/css">
</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
</jsp:include>

<c:if test="${requestScope.error2}">
    <div id="errore-registrazione">
        <div>
            <h3 class="testo-croce-error"> &times; </h3>
            <h4>Creazione account non effettuata, credenziali non valide.</h4>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.error1}">
    <div id="errore-registrazione">
        <div>
            <h3 class="testo-croce-error"> &times; </h3>
            <h4>Creazione account non effettuata, email gia esistente.</h4>
        </div>
    </div>
</c:if>

<div class="buttons">
    <form action="visualizzaRegistrazione"><button id="button_registrati" class="attivo">Registrati</button></form>
    <form action="visualizzaLogin"><button id="button_login">Login</button></form>
</div>

<div id="registrazione" method="post">
    <form id="registerForm" action="registrazione">
        <div class="form-row">
            <input type="text" name="nome" placeholder="Nome" maxlength="30" pattern="^[a-zA-Z]{5,30}$" title="Utilizare solo lettere minuscole o maiuscole minimo 5 massimo 30." required>
            <input type="email" name="email" placeholder="Email" name="email" pattern="^[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,4}$" title="Dopo la @ almeno un carattere, seguito da un punto e un altro carattere." maxlength="30" required>
        </div>
        <div class="form-row">
            <input type="password" name="pass" minlength="8" maxlength="30" title="Da 8 a 30 caratteri, tra cui un numero, una maiuscola e un carattere speciale," pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Password" required>
            <input type="password" name="confermaPass" minlength="8" maxlength="16" title="Da 8 a 16 caratteri, tra cui un numero, una maiuscola e un carattere speciale," pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Conferma Password" required>
        </div>
        <div class="form-row">
            <button type="submit">Registrati</button>
        </div>
    </form>
</div>

<jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
</jsp:include>

</body>
</html>
