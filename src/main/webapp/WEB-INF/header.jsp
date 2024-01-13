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

      <div>
        <form action="visualizzaCarrello" method="get">
          <input type="submit" value="&#xf07a;" class="icon-cart">
        </form>
      </div>
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