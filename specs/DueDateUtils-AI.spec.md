# Spec: `DueDateUtils.kt`

> Post-conditions are the test oracle: write the expected behavior from intent, not from the code.
> Tag each post-condition [Unit] if its test runs on the JVM; otherwise tag [Integration].
> End each valid case with `Tested by <test>` if an existing test checks it, or `Tip:` describing the test to write.

## `dueDateLabel(dueDate: LocalDate?, today: LocalDate, isCompleted: Boolean): String`

**Intent**: Build the D-day label for a task from its due date and completion status. Day counts are calendar-day differences and work across month and year boundaries.

**Pre-conditions**
- `today` is a valid `LocalDate`.
- `dueDate` is null or a valid `LocalDate`.

**Post-conditions**
- Success cases
  - A completed task returns `"CLEAR"` whether it has a due date or not. `[Unit]`
    - Tip: Given `dueDate = 2026-10-20`, `today = 2026-10-18`, and `isCompleted = true` / When `dueDateLabel` is called / Then the result is `"CLEAR"`.
  - An active task without a due date returns an empty string. `[Unit]`
    - Tip: Given `dueDate = null`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `""`.
  - An active task due today returns `"D-Day"`. `[Unit]`
    - Tip: Given `dueDate = 2026-10-18`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-Day"`.
  - An active task due in the future returns `"D-N"` where N is the total number of calendar days until the due date. `[Unit]`
    - Tip: Given `dueDate = 2026-10-21`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-3"`.
  - An active task whose due date has passed returns `"D+N"` where N is the total number of calendar days since the due date. `[Unit]`
    - Tip: Given `dueDate = 2026-10-15`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D+3"`.
- Error cases
  - None. Every nullable due date and valid date pair is accepted. `[Unit]`
    - Tip: Given `dueDate = null`, `today = 2026-10-18`, and `isCompleted = true` / When `dueDateLabel` is called / Then the result is `"CLEAR"`.
- Edge cases
  - One day before the due date returns `"D-1"`. `[Unit]`
    - Tip: Given `dueDate = 2026-10-19`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-1"`.
  - One day after the due date returns `"D+1"`. `[Unit]`
    - Tip: Given `dueDate = 2026-10-17`, `today = 2026-10-18`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D+1"`.
  - A due date one calendar day after a month boundary is counted as one day. `[Unit]`
    - Tip: Given `dueDate = 2026-11-01`, `today = 2026-10-31`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-1"`.
  - A due date one calendar day after a year boundary is counted as one day. `[Unit]`
    - Tip: Given `dueDate = 2027-01-01`, `today = 2026-12-31`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-1"`.
  - February 29 is counted as the next calendar day after February 28 in a leap year. `[Unit]`
    - Tip: Given `dueDate = 2024-02-29`, `today = 2024-02-28`, and `isCompleted = false` / When `dueDateLabel` is called / Then the result is `"D-1"`.

**Undefined behavior** (optional)
- None.
