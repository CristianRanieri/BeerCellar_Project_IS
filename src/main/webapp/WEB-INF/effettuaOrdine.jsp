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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_pagina_dati_ordine.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_errore.css" type="text/css">
</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="header" value=""/>
</jsp:include>

<!--ERRORE:-->
<% if(request.getAttribute("errore")!=null){%>
<div id="blocco-messaggio-cambiamenti">
    <h2>ERRORE!</h2>
    <h3>Alcuni dati selezionati o inseriti non sono stati inviati o non rispettano il formato indicato, la esortiamo a riprovare.</h3>
</div>
<% }%>
<!--ERRORE:-->

<h1>Procedi All'Acquisto</h1>

<form action="effettuaOrdine" method="post">

    <div id="contenitore">
        <div id="datiCliente">
            <h3><span> DATI CLIENTE</span></h3><br>
            <div>
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" pattern="^[a-zA-Z ]{3,30}$" required>
                </div>

                <div>
                    <label for="cognome">Cognome</label>
                    <input type="text" id="cognome" name="cognome" pattern="^[a-zA-Z ]{3,30}$" required>
                </div>
            </div>
        </div>

        <div id="datiPagamento">
            <h3><span> DATI PAGAMENTO</span></h3><br>
            <div>
                <div>
                    <div>
                        <label for="numeroCarta">Numero Carta</label>
                        <input type="text" id="numeroCarta" name="carta" pattern="^\d{4}\s\d{4}\s\d{4}\s\d{4}$" required>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="dataScadenza">Data di scadenza</label>
                        <input type="month" id="dataScadenza" name="dataScadenza" required>
                    </div>
                </div>
            </div>
            <div>
                <div>
                    <div>
                        <label for="codiceCVV">Codice CVV</label>
                        <input type="text" id="codiceCVV" name="cvv" pattern="^\d{3}$" required>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="nomeCarta">Nome Carta</label>
                        <input type="text" id="nomeCarta" name="nomeCarta" pattern="^[a-zA-Z ]{3,30}$" required>
                    </div>
                </div>
            </div>
        </div>


        <div id="datiIndirizzoConsegna">
            <h3><span> INDIRIZZO DI CONSEGNA</span></h3><br>
            <div>
                <div>
                    <div>
                        <label for="indirizzo">Indirizzo</label>
                        <input type="text" id="indirizzo" name="indirizzo" pattern="^[a-zA-Z0-9 ]{3,30}$" required>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="cap">CAP</label>
                        <input type="text" id="cap" name="cap" pattern="^\d{5}$" required>
                    </div>
                </div>
            </div>
            <div>
                <div>
                    <div>
                        <label for="provincia">Provincia</label>
                        <select name="provincia" id="provincia" onchange="caricaCitta()">
                            <option value="" disabled selected>Scegli la provincia</option>
                        </select>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="citta">Città</label>
                        <select id="citta" name="citta"></select>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contenitore_bottone_procedi">
        <button type="submit">Procedi al pagamento</button>
    </div>
</form>

<script>
    function caricaCitta() {
        var provinciaSelect = document.getElementById("provincia");
        var cittaSelect = document.getElementById("citta");

        cittaSelect.innerHTML = "";

        var opzioneSelezionata =  provinciaSelect.options[provinciaSelect.selectedIndex];

        var provinciaSelezionata = opzioneSelezionata.id;

        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var parserComuni = new DOMParser();
                    var xmlDocComuni = parserComuni.parseFromString(xhr.responseText, 'text/xml');

                    var comuniElements = xmlDocComuni.querySelectorAll('comune');

                    // Filtra i comuni per provincia selezionata
                    var comuniPerProvincia = Array.from(comuniElements).filter(function(comuneElement) {
                        var idProvincia = comuneElement.querySelector('campo[nome="id_provincia"]').textContent;
                        return idProvincia === provinciaSelezionata;
                    });

                    // Aggiungi le opzioni alla select delle città
                    comuniPerProvincia.forEach(function(comuneElement) {
                        var nomeComune = comuneElement.querySelector('campo[nome="nome"]').textContent;
                        var option = document.createElement("option");
                        option.value = nomeComune;
                        option.text = nomeComune;
                        cittaSelect.add(option);
                    });
                } else {
                    console.error('Errore nel recupero dei dati dei comuni. Codice di stato:', xhr.status);
                }
            }
        };

        xhr.open('GET', './static/dataset_PC/comuni.xml', true);  // Assicurati di utilizzare il percorso corretto per il file dei comuni
        xhr.send();
    }
    // Popola la select delle province al caricamento della pagina
    function popolaProvince() {
        var provinciaSelect = document.getElementById("provincia");
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var parserProvince = new DOMParser();
                    var xmlDocProvince = parserProvince.parseFromString(xhr.responseText, 'text/xml');

                    var provinceNodes = xmlDocProvince.querySelectorAll('provincia');

                    provinceNodes.forEach(function(provinciaNode) {
                        var idProvincia = provinciaNode.querySelector('campo[nome="id"]').textContent;
                        var nomeProvincia = provinciaNode.querySelector('campo[nome="nome"]').textContent;

                        var option = document.createElement("option");
                        option.value = nomeProvincia;
                        option.text = nomeProvincia;
                        option.id = idProvincia;
                        provinciaSelect.add(option);
                    });
                } else {
                    console.error('Errore nel recupero dei dati delle province. Codice di stato:', xhr.status);
                }
            }
        };

        xhr.open('GET', './static/dataset_PC/province.xml', true);
        xhr.send();
    }

    // Chiamare la funzione per popolare le province
    popolaProvince();

</script>

<jsp:include page="footer.html">
    <jsp:param name="footer" value=""/>
</jsp:include>

</body>
</html>
