## Progetto-Paci-Ciucciove
# Introduzione
Il seguente applicativo ha come scopo la raccolta, il filtraggio e l'analisi di risultati riguardanti le offerte di lavoro ottenute sfruttando l'API [Findwork](https://findwork.dev).
<br>
Inizialmente il lavoro si è incentrato nel visualizzare in formato **JSON** gli annunci sottoposti a filtri preimpostati richiamando e interfacciandosi con lo schema d'uso dell'API Web.
I filtri:
<ul>
  <li>**location** che specifica, fino ad un massimo di 5, le città per cui si vogliono ricercare lavori</li>
  <li>**remote** indica se il lavoro in questione può essere svolto da remoto</li>
  <li>**employment_type** che indica il tempo di lavoro</li>
</ul>
Esempio di link di Findowork: https://findwork.dev/api/jobs/?location=Moscow&remote=true&employment_type=full+time
Link ricostruito: https://localhost:8080/cities?location=Moscow&remote=true&emplyementType=full+time\n
Il passo successivo è stato calcolare le statistiche, mediante lo stesso raggruppamento, delle seguenti caratteristiche sul totale richiamando un'altra rotta:
<ul>
    <li>Calcolo del numero di lavori <b>part-time</b> e relativa percentuale</li>
    <li>Calcolo del numero di lavori <b>full-time</b> e relativa percentuale</li>
    <li>Calcolo del numero di lavori dove <i><b>non</b> era specificato</i> l'ammontare delle ore di lavoro e relativa percentuale</li>
    <li>Calcolo, eseguito sul totale, della presenza e dunque conoscenza, tra tutte le offerte di lavoro, di <b>linguaggi di programmazione</b> più diffusi</li>
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
# Diagrammi UML
<img src="https://github.com/PaciMatteo94/Progetto-Paci-Ciucciove/blob/main/NewModel%20Sequence%20Diagram.jpg">
<br>
# Rotte

# Gestione Eccezioni

# Test

# Autori
