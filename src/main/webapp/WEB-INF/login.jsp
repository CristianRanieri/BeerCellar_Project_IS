<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 28/12/2023
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>


<c:if test="${requestScope.error2}">
  <div id="errore-registrazione">
    <div>
      <h3 class="testo-croce-error"> &times; </h3>
      <h4>Email o password non valida</h4>
    </div>
  </div>
</c:if>


<c:if test="${requestScope.error1}">
  <div id="errore-registrazione">
    <div>
      <h3 class="testo-croce-error"> &times; </h3>
      <h4>Parametri inseriti non validi</h4>
    </div>
  </div>
</c:if>


<form action="login" method="post">

  <div class="blocco-input-nep">
    <label>&nbsp;&nbsp;&nbsp;Indirizzo email</label>
    <div>
      <input type="email" name="email" pattern="^[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,4}$" title="Dopo la @ almeno un carattere, seguito da un punto e un altro carattere." maxlength="30">
    </div>
  </div>

  <div class="blocco-input-nep">
    <label>&nbsp;&nbsp;&nbsp;Password</label>
    <div>
      <input type="password" name="pass" minlength="8" maxlength="16" title="Da 8 a 16 caratteri, tra cui un numero, una maiuscola e un carattere speciale," pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" required>
    </div>
  </div>

  <div id="blocco-input-bottone">
    <div>
      <input type="submit" id="bottone-login" value="LOGIN">
    </div>
    <label><a href="visualizzaLogin"> Non hai un account? Registrati ora. </a></label>
  </div>
</form>

</body>
</html>
