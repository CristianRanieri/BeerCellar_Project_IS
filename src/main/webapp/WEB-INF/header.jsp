<%@ page import="model.entity.Account" %><%--
  Created by IntelliJ IDEA.
  User: 174907
  Date: 12/01/2024
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="./static/css/css_generic.css" type="text/css">
  <link rel="stylesheet" href="./static/css/css_top_bar.css" type="text/css"><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
  <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="contenitore_TopBar"><!-- Spostare solo questo contenitore in una jsp da @include re in ogni altra jsp -->

  <div class="top-bar">
    <div class="contenitore_logo">
      <img src="static/image/logo_project_4K_white.png" alt="logo">
    </div>

    <div id="div_ricerca">
      <form action="ricercaProdottiNome" method="get">
        <input type="text" name="ricerca" placeholder="&#xf002; Ricerca" class="input-barra">
      </form>
    </div>

    <div class="bottoni_utenti">
      <div>
        <form action="visualizzaLogin" method="get">
          <input type="submit" value="&#xf007;" class="icon-user">
        </form>
      </div>


      <%if (!((Account) session.getAttribute("account")).isGestore()){%>
        <div>
          <form action="visualizzaCarrello" method="get">
            <input type="submit" value="&#xf07a;" class="icon-cart">
          </form>
        </div>
      <%}%>
    </div>
  </div>

  <div class="contenitore_filtri">

    <form action="ricercaProdotti?formato=bottiglia" method="get">
      <button class="image-button" type="submit">
        <img src="static/image/bottle.png">
        BOTTIGLIE
      </button>
    </form>

    <form action="ricercaProdotti?formato=lattina" method="get">
      <button class="image-button" type="submit">
        <img src="static/image/can.png">
        LATTINE
      </button>
    </form>

    <form action="ricercaProdotti?formato=fusto" method="get">
      <button class="image-button" type="submit">
        <img src="static/image/barrel.png">
        FUSTI
      </button>
    </form>

  </div>

</div>

</body>
</html>
