from pathlib import Path
import pandas as pd


DATA_PATH = Path(__file__).parent / "data" / "tickets_sample.csv"
OUTPUT_DIR = Path(__file__).parent / "output"


def suggest_priority(impact: str, urgency: str) -> str:
    """Suggest ticket priority from impact and urgency."""
    impact = impact.lower()
    urgency = urgency.lower()

    if impact == "high" and urgency in {"high", "medium"}:
        return "P1"
    if impact == "medium" and urgency == "high":
        return "P2"
    if impact == "low" and urgency == "low":
        return "P4"
    return "P3"


def load_tickets(path: Path = DATA_PATH) -> pd.DataFrame:
    df = pd.read_csv(path, parse_dates=["created_at", "resolved_at"])
    df["resolution_hours"] = (df["resolved_at"] - df["created_at"]).dt.total_seconds() / 3600
    df["suggested_priority"] = df.apply(
        lambda row: suggest_priority(row["impact"], row["urgency"]), axis=1
    )
    df["sla_breached"] = df["resolution_hours"] > df["sla_hours"]
    return df


def calculate_kpis(df: pd.DataFrame) -> pd.DataFrame:
    grouped = df.groupby("category").agg(
        tickets=("ticket_id", "count"),
        avg_resolution_hours=("resolution_hours", "mean"),
        sla_breach_rate=("sla_breached", "mean"),
    ).reset_index()

    grouped["avg_resolution_hours"] = grouped["avg_resolution_hours"].round(2)
    grouped["sla_breach_rate"] = (grouped["sla_breach_rate"] * 100).round(1)
    return grouped.sort_values("sla_breach_rate", ascending=False)


def main() -> None:
    OUTPUT_DIR.mkdir(exist_ok=True)
    tickets = load_tickets()
    kpis = calculate_kpis(tickets)

    print("Ticket KPIs by category")
    print(kpis.to_string(index=False))

    tickets.to_csv(OUTPUT_DIR / "tickets_enriched.csv", index=False)
    kpis.to_csv(OUTPUT_DIR / "category_kpis.csv", index=False)
    print(f"\nResults saved to: {OUTPUT_DIR}")


if __name__ == "__main__":
    main()
