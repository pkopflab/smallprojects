# ITSM Ticket Analyzer

Ein Python-Projekt zur Auswertung von IT-Service-Tickets. Es berechnet Ticket-KPIs, erkennt SLA-Verletzungen und schlägt auf Basis einfacher Regeln eine Priorität vor.

## Warum das gut auf GitHub wirkt

- knüpft an ITSM, ServiceNow und Prozessanalyse an
- zeigt Datenaufbereitung, KPI-Denken und Business-Kontext
- eignet sich gut für Bewerbungen in IT, Consulting, Risk, Operations und Process Management

## Tech Stack

- Python
- pandas
- matplotlib
- pytest

## Installation

```bash
pip install -r requirements.txt
```

## Analyse ausführen

```bash
python analyze_tickets.py
```

Danach wird im Ordner `output/` eine CSV-Datei mit KPI-Ergebnissen erzeugt.

## Tests

```bash
pytest
```

## Mögliche Erweiterungen

- NLP-Klassifikation von Tickettexten
- Anbindung an ServiceNow-Export
- Dashboard mit Streamlit
- SLA-Reporting pro Team oder Kategorie
