# Bike rental app

Temaet for oppgaven er administrasjon av sykkelutleie. Sykkelutleietjenesten [BikeRental](BikeRental.java) tilbyr leie av sykler (Bike)[Bike.java] som plukkes opp og settes tilbake på utvalgte stasjoner spredt rundt omkring. Syklene har GPS, slik at de hele tiden spores (GeoLocation)[GeoLocation.java].

Den som skal leie sykkel (Person)[Person.java] bruker en app for å få oversikt over hvor (ved hvilke stasjoner) det er tilgjengelige sykler. Deretter er det bare å identifisere sykkelen, angi hvor lenge man ønsker å leie den, og en kan sykle av gårde. Når en er ferdig med å bruke sykkelen, må den settes tilbake på en stasjon (ikke nødvendigvis den samme som en tok den fra). Ved hjelp av appen angir man at leieforholdet er avsluttet. Leieprisen blir da beregnet og pengene trukket.
 
Prisen er basert på påbegynte timer, og merk at en ikke betaler for mer enn faktisk bruk. Hvis man altså angir at en ønsker å leie en sykkel i tre timer, men leverer den tilbake etter en halv time, så betaler en for én times bruk. Det er mulig å utvide leietiden underveis, noe som utløser et lite gebyr. Hvis en utvider leietiden eller leverer sykkelen etter at sykkelen skulle vært levert, så påløper det også et gebyr.
