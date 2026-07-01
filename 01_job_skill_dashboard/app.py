from pathlib import Path
import re

import matplotlib.pyplot as plt
import pandas as pd
import streamlit as st


DATA_PATH = Path(__file__).parent / "data" / "job_postings_sample.csv"

SKILLS = [
    "Python", "SQL", "Java", "Power BI", "Tableau", "Excel", "SAP",
    "Machine Learning", "ServiceNow", "Azure", "AWS", "Scrum",
    "Data Analytics", "ETL", "API", "Process Mining"
]


def load_data(path: Path = DATA_PATH) -> pd.DataFrame:
    """Load job posting sample data."""
    return pd.read_csv(path)


def count_skills(df: pd.DataFrame) -> pd.DataFrame:
    """Count skill mentions in title + description."""
    text = (df["title"].fillna("") + " " + df["description"].fillna("")).str.lower()
    counts = []
    for skill in SKILLS:
        pattern = re.escape(skill.lower())
        counts.append({
            "skill": skill,
            "mentions": int(text.str.contains(pattern, regex=True).sum())
        })
    return pd.DataFrame(counts).sort_values("mentions", ascending=False)


def main() -> None:
    st.set_page_config(page_title="Job Skill Dashboard", layout="wide")
    st.title("Job Skill Dashboard")
    st.write("Analyse von Beispiel-Stellenanzeigen für Data-, IT- und Business-Analytics-Rollen.")

    df = load_data()

    with st.sidebar:
        st.header("Filter")
        cities = ["Alle"] + sorted(df["city"].unique().tolist())
        selected_city = st.selectbox("Stadt", cities)
        work_models = ["Alle"] + sorted(df["work_model"].unique().tolist())
        selected_work_model = st.selectbox("Arbeitsmodell", work_models)

    filtered = df.copy()
    if selected_city != "Alle":
        filtered = filtered[filtered["city"] == selected_city]
    if selected_work_model != "Alle":
        filtered = filtered[filtered["work_model"] == selected_work_model]

    col1, col2, col3 = st.columns(3)
    col1.metric("Anzahl Anzeigen", len(filtered))
    col2.metric("Ø Erfahrungslevel", round(filtered["experience_level"].mean(), 1))
    col3.metric("Ø Gehaltsschätzung", f"{round(filtered['salary_estimate_eur'].mean()):,.0f} €".replace(",", "."))

    st.subheader("Gefragte Skills")
    skill_counts = count_skills(filtered)
    st.dataframe(skill_counts, use_container_width=True)

    fig, ax = plt.subplots()
    ax.bar(skill_counts["skill"], skill_counts["mentions"])
    ax.set_ylabel("Erwähnungen")
    ax.set_xlabel("Skill")
    ax.tick_params(axis="x", rotation=45)
    st.pyplot(fig)

    st.subheader("Datenbasis")
    st.dataframe(filtered, use_container_width=True)


if __name__ == "__main__":
    main()
