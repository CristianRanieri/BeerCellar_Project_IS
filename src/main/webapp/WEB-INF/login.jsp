<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Login</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_registrazione_e_login.css" type="text/css">
  </head>
  <body>

    <jsp:include page="header.jsp">
      <jsp:param name="header" value=""/>
    </jsp:include>

    <c:if test="${requestScope.error2}">
      <div id="blocco-messaggio-cambiamenti">
          <h3>Email o password non valida, le chiedeamo di riprovare.</h3>
      </div>
    </c:if>


    <c:if test="${requestScope.error1}">
      <div id="blocco-messaggio-cambiamenti">
        <h3>L'email o la password non è stata inserita o non rispettana il formato indicato, la esortiamo a riprovare.</h3>
      </div>
    </c:if>

    <div class="contenitore_bottoni_login_registrazione">
      <div class="buttons">
        <form action="visualizzaRegistrazione"><button id="button_registrati">Registrati</button></form>
        <form action="visualizzaLogin"><button id="button_login"  class="attivo">Login</button></form>
      </div>
    </div>

    <div id="login" style="display: flex">
      <form id="loginForm" action="login" method="post">
        <div class="form-row">
          <input type="text" name="email" pattern="^[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,4}$" title="Dopo la @ almeno un carattere, seguito da un punto e un altro carattere." maxlength="134" placeholder="Email" required>
          <input type="password" name="pass" minlength="8" maxlength="30" title="Da 8 a 30 caratteri, tra cui un numero, una maiuscola e un carattere speciale," pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Password" required>
        </div>
        <div class="form-row">
          <button type="submit">Accedi</button>
        </div>
      </form>
    </div>

    <jsp:include page="footer.html">
      <jsp:param name="footer" value=""/>
    </jsp:include>

  </body>
</html>