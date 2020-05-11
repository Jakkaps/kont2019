# Svar på teorispørsmål

Her besvares teorispørsmål. Hvis du bruker Eclipse, så velg **Markdown Source** nederst til venstre, for å redigere. Du trenger ikke tenke på formattering, men vi har brukt markdown for å lage overskriften.

### 1.1
Iterator og iterable forklarer begge hvordan man skal iterere over noe.
Dette kan for-each benytte seg av ved å unngå å bruke index-syntaks og heller
kontsturere en vanlig for løkke av den enklere for-each løkken.

Altså kan dette:
``
for (int i = 0; i < object.list.length(); i++){
    ChildObject obj = object.list[i];
    // kode
}
```
bli dette
```
for (ChildObject obj : object){`

}
```

