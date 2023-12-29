<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 28/12/2023
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Utente</title>
</head>
<body>

<c:if test="${requestScope.error2}">
   <div>
       <h3 class="testo-croce-error"> &times; </h3>
       <h4>Modifica credenziali account non effettuata, parametri non validi.</h4>
   </div>
</c:if>

<c:if test="${requestScope.error1}">
    <div>
        <h3 class="testo-croce-error"> &times; </h3>
        <h4>Modifica credenziali account non effettuata, password inserita uguale a quella registrata.</h4>
    </div>
</c:if>

<c:if test="${requestScope.corretto}">
    <div>
        <h3 class="testo-croce-error"> &times; </h3>
        <h4>Modifica credenziali account effettuata correttamente.</h4>
    </div>
</c:if>


<form action="modificaCredenziali" method="post">

    <div class="blocco-input-nep">
        <label>&nbsp;&nbsp;&nbsp;Nome</label>
        <div>
            <input type="text" id="nome" name="nome" value="${account.nome}" maxlength="30" pattern="^[a-zA-Z]{5,30}$" title="Utilizare solo lettere minuscole o maiuscole minimo 5 massimo 30." required>
        </div>
    </div>

    <div class="blocco-input-nep">
        <label>&nbsp;&nbsp;&nbsp;Password</label>
        <div>
            <input type="password" name="pass" placeholder="**************" minlength="8" maxlength="30" title="Da 8 a 30 caratteri, tra cui un numero, una maiuscola e un carattere speciale tra i seguenti !@#$%^&*" pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" required>
        </div>
    </div>

    <div id="blocco-input-bottone">
        <input type="submit" id="bottone-login" value="CAMBIA CREDENZIALI">
    </div>
</form>

<div id="blocco-logout-bottone">
    <form action="logout">
        <input type="submit" id="bottone-logout" value="LOGOUT">
    </form>
</div>

<div id="blocco-logout-bottone">
    <form action="visualizzaOrdini">
        <input type="submit" value="STORICO ORDINI">
    </form>
</div>

</body>
</html>
