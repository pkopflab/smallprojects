# Job Skill Dashboard

Ein kleines Data-Analytics-Projekt zur Analyse von Jobanzeigen. Das Dashboard zeigt, welche Skills in Beispiel-Stellenanzeigen besonders häufig vorkommen, welche Rollen besonders datenorientiert sind und wie sich Remote-/Hybrid-Anteile verteilen.

## Warum das gut auf GitHub wirkt

- zeigt Python, pandas und Datenvisualisierung
- passt zu Wirtschaftsinformatik, Data Analytics und Bewerbungsphase
- ist sofort als Streamlit-App vorzeigbar
- lässt sich später leicht mit echten Stellenanzeigen erweitern

## Tech Stack

- Python
- pandas
- Streamlit
- matplotlib

## Installation

```bash
pip install -r requirements.txt
```

## Start

```bash
streamlit run app.py
```

## Projektstruktur

```text
.
├── app.py
├── data/
│   └── job_postings_sample.csv
├── requirements.txt
└── README.md
```

## Mögliche Erweiterungen

- echte Daten von Kaggle oder APIs ergänzen
- Skill-Trends über Zeit darstellen
- Filter nach Stadt, Branche oder Senioritätslevel
- Matching-Score zum eigenen Lebenslauf hinzufügen
