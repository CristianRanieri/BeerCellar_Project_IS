<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
  <head>
    <meta charset="UTF-8">
    <title>Sei Maggiorenne?</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_banner_maggiorenne.css" type="text/css">
  </head>

  <body>
    <div id="ageVerificationBanner" class="age-verification-banner">
      <div class="banner-content">
        <p>Benvenuto! Per accedere a questo sito, conferma di essere maggiorenne.</p>
        <button id="confirmButton">Sì, sono maggiorenne</button>
        <button id="rejectButton">No, non sono maggiorenne</button>
      </div>
    </div>

    <script>
      document.addEventListener("DOMContentLoaded", function() {
        // Controlla il parametro sonoMaggiorenne nella sessionStorage
        var sonoMaggiorenne = sessionStorage.getItem('sonoMaggiorenne');

        // Se sonoMaggiorenne è null, mostra il banner
        if (sonoMaggiorenne === null) {
          var ageVerificationBanner = document.getElementById("ageVerificationBanner");
          ageVerificationBanner.style.display = "flex";
        }

        // Gestisci il click sul pulsante di conferma
        document.getElementById("confirmButton").addEventListener("click", function() {
          // Nascondi il banner
          ageVerificationBanner.style.display = "none";

          // Imposta il parametro sonoMaggiorenne a true nella sessionStorage
          sessionStorage.setItem('sonoMaggiorenne', 'true');
        });

        // Gestisci il click sul pulsante di rifiuto
        document.getElementById("rejectButton").addEventListener("click", function() {
          // Reindirizza l'utente o esegui altre azioni come chiudere la finestra
          alert("Mi dispiace, devi essere maggiorenne per accedere a questo sito.");
          window.location.href = "https://www.google.com"; // Reindirizza ad un altro sito, puoi personalizzare l'URL
        });
      });
    </script>
  </body>
</html>