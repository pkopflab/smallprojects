from analyze_tickets import suggest_priority


def test_priority_high_high_is_p1():
    assert suggest_priority("high", "high") == "P1"


def test_priority_low_low_is_p4():
    assert suggest_priority("low", "low") == "P4"


def test_priority_medium_low_defaults_to_p3():
    assert suggest_priority("medium", "low") == "P3"
