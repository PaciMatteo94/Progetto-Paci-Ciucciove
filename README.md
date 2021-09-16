## Progetto-Paci-Ciucciovè
# Introduzione
Il seguente applicativo ha come scopo la raccolta, il filtraggio e l'analisi di risultati riguardanti le offerte di lavoro ottenute sfruttando l'API [Findwork](https://findwork.dev).
<br>
Il lavoro è incentrato nel visualizzare in formato **JSON** gli annunci e le statistiche dei lavori sottoposti a filtri preimpostati richiamando e interfacciandosi con lo schema d'uso dell'API Web.<br>
I filtri per i lavori sono:
<ul>
  <li><b>location</b> che specifica, fino ad un massimo di 5, le città per cui si vogliono ricercare lavori</li>
  <li><b>remote</b> indica se il lavoro in questione può essere svolto da remoto</li>
  <li><b>employmentType</b> che indica il tempo di lavoro</li>
</ul>
Esempio di link di Findowork: https://findwork.dev/api/jobs/?location=Moscow&remote=true&employment_type=full+time<br>
Link ricostruito: <b>https://localhost:8080/cities?location=Moscow&remote=yes&emplyementType=full+time</b><br>

I filtri per le statistiche riguardanti i lavori sono:
<ul>
  <li><b>location</b> che specifica, fino ad un massimo di 5, le città per cui si vogliono ricercare lavori</li>
  <li><b>remote</b> indica se il lavoro in questione può essere svolto da remoto</li>
  <li><b>date</b> che indica la data di inizio da cui selezionare i lavori su cui calcolare le statistiche</li>
</ul>
Esempio di chiamata per ottenere le statistiche di una città:<br>
https://localhost:8080/stats?location=Berlin&remote=yes&date=2021-09-01<br>
<br>
Il programma calcolerà le statistiche dei lavori sui seguenti parametri:
<ul>
    <li>Calcolo del numero di lavori <b>part-time</b> e relativa percentuale</li>
    <li>Calcolo del numero di lavori <b>full-time</b> e relativa percentuale</li>
    <li>Calcolo del numero di lavori dove <i><b>non</b> era specificato</i> l'ammontare delle ore di lavoro e relativa percentuale</li>
    <li>Calcolo, eseguito sul totale, della presenza e dunque conoscenza, tra tutte le offerte di lavoro, di alcuni dei <b>linguaggi di programmazione</b> più diffusi</li>
</ul>

# Esempi di chiamate
Qui di seguito viene riportata una chiamata alla rotta <i>cities</i> che vedremo più avanti passando al parametro **location** il valore Moscow:
```json
{
  "CityCount": 1,
  "Results": [
      {
          "Location": "Moscow",
          "Count": 2,
          "Works": [
              {
                  "role": "Software Engineers and many more",
                  "companyName": "Revolut",
                  "employementType": null,
                  "remote": true,
                  "keywords": [
                      "ml",
                      "kubernetes",
                      "python",
                      "kotlin",
                      "postgres",
                      "gcp",
                      "terraform"
                  ],
                  "dataPosted": "2021-09-01T18:04:13Z",
                  "text": "Revolut is one of the fastest growing companies in Europe. We’re hiring throughout the company for many roles. I work on Data Platform, helping people bring ML models to production. We use Terraform, Kubernetes, GCP, Vault, Python, Java, Kotlin, Postgres and many more. There are a lot of interesting challenges and projects. We&#x27;re looking for experienced engineers that will work within a distributed company.<br>We are valued at 33B and on the track to IPO soon.<br>Careers: <a href=\"https:&#x2F;&#x2F;jobs.lever.co&#x2F;revolut?lever-via=8QDkIWda8L\" rel=\"nofollow\">https:&#x2F;&#x2F;jobs.lever.co&#x2F;revolut?lever-via=8QDkIWda8L</a>"
              },
              {
                  "role": "etc..."}
          ]
      }
  ]
}
```
Di seguito invece è riportato un semplice risultato della rotta delle statistiche.
```json
{
    "CityCount": 1,
    "Results": [
        {
            "Location": "Berlin",
            "Count": 41,
            "Stats": {
                "fullTimeAmount": 33,
                "partTimeAmount": 0,
                "notSpecifiedAmount": 8,
                "fullTimePerc": 80.49,
                "partTimePerc": 0.0,
                "notSpecifiedPerc": 19.51,
                "percPython": 31.71,
                "percPhp": 4.88,
                "percSpring": 21.95,
                "percTypescript": 19.51,
                "percSql": 19.51,
                "commonLanguages": [
                    "python",
                    "php",
                    "spring",
                    "typescript",
                    "sql"
                ]
            }
        }
    ]
}
```

# Diagrammi UML
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/umlprogetto.png"></img><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/umlprogetto2.png"></img><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/NewModel%20Sequence%20Diagram.jpg"></img><br>


# Rotte
<h3>ROTTA PREFERENZE</h3><br>
La rotta delle preferenze si divide in due tipi di chiamate, una chiamata di tipo GET e una di tipo POST.<br>
Entrame le chiamate possono essere effetuate su postman tramite l'url <b>localhost:8080/preferences</b> sostituendo adeguatamente il metodo di richiesta.<br>
Tramite la chiamata di tipo GET verranno visualizzate le città di preferenza su cui è possibile effettuare le ricerche dei lavori e il calcolo delle statistiche.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/preferences/Schermata%202021-09-16%20alle%2015.23.47.png"></img><br>
La chiamata invece di tipo POST darà la possibilità di modificare le città di preferenza.<br>
Per effettuare la modifica bisognerà fornire i parametri di autenticazione nel body della richiesta e inserire le località con cui sostituire le preferenze.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/preferences/Schermata%202021-09-16%20alle%2016.02.18.png"></img><br>
Le credenziali da inserire nel body di tipo <u>raw</u> su postman per avere accesso a tale funzionalità sono <b>admin,root</b>.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/preferences/Schermata%202021-09-16%20alle%2016.04.22.png"></img><br>
<p>

<h3>ROTTA PER LA RICERCA DI LAVORI</h3>

La rotta in questo caso usa solo il metodo GET e viene referenziata dall'url: <b>localhost:8080/cities</b><br>
Possibili casi di utilizzo:
- Senza inserimento di locazioni o parametri di filtraggio verrano restituiti i lavori di tipo java presenti nelle città di preferenza.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/cities/Schermata%202021-09-16%20alle%2016.25.16.png"></img><br>
- Con l'inserimento solo di parametri di filtraggio verrano restituiti i lavori presenti nelle città di preferenza oppurtunamente filtrati.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/cities/Schermata%202021-09-16%20alle%2016.47.22.png"></img><br>
- Con l'inserimento di locazioni, con possibilità di filtrare o meno i lavori tramite i parametri descritti sopra, verrano restituiti i lavori presenti in tali locazioni.<br><br>
- <img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/cities/Schermata%202021-09-16%20alle%2016.27.30.png"></img><br>

<h3>ROTTA PER IL CALCOLO DELLE STATISTICHE</h3>

Anche in questo caso il metodo usato è GET e tale rotta viene referenziata tramite l'url: <b>localhost:8080/stats</b><br>
Possibili casi di utilizzo:<br>
- Senza inserimento di locazioni o parametri di filtraggio verranno restituite le statistiche delle città di preferenza.<br><br>
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/stats/Schermata%202021-09-16%20alle%2015.30.44.png"></img><br>
- Con l'inserimento solo di parametri di filtraggio verrano restituite le statistiche filtrate delle città di preferenza.
<img src=""></img><br>
- Con l'inserimento di locazioni, con possibilità di filtrare o meno i lavori tramite i parametri descritti sopra, verranno restituite le statistiche per tali locazioni.
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/images/stats/Schermata%202021-09-16%20alle%2015.38.05.png"></img><br>
# Gestione Eccezioni
Sono state create eccezioni specifiche per la gestione di alcuni casi specifici e abbiamo utlizzato anche eccezioni gia presenti nelle librerie usate.
La generazione di una di queste eccezioni porta la visualizzazione di un messaggio salvato in oggetto, dove si descrive a cosa è dovuto l'errore.
Le eccezioni utilizzate sono:
- <b>EmptyBodyException</b> nel caso in cui non sono inseriti parametri nel body durante la richiesta di POST delle preferenze.
- <b>WrongCredentialsException</b> nel caso in cui sono stati inseriti parametri sbagliati nel body durante la richiesta di POST delle preferenze.
- <b>NoLocationException</b> nel caso in cui si inserisce il parametro location ma lo si lascia vuoto.
- <b>NoResultsException</b> nel caso in cui non si hanno risultati con i parametri immessi.
- <b>UnsupportedValueException</b> nel caso in cui si inserisce un valore non supportato in uno dei parametri.
- <b>DateTimeParseException</b> nel caso in cui viene inserita un formato di data sbagliato.
- <b>MethodArgumentTypeMismatchException</b> nel caso in cui si inserisce un argomento non supportato da uno dei parametri, viene utilizzato insieme all'eccezione <b>UnsupportedValueException</b>.
# Test
Sono presenti 3 unità di test:
- <b>TestAPICall</b> controlla se viene salvata correttamente nell'oggetto City la località su cui viene effettuata la ricerca nell'API.
- <b>TestException</b> è un insieme di test che controlla il corretto funzionamento di alcuni eccezioni utilizzare nel programma.
- <b>TestPref</b> controlla se il singolo oggetto generato dalla classe contiene le città di preferenza da noi impostate di default.

# Autori

- <b>Paci Matteo</b>
- <b>Ciucciovè Leonardo</b>
