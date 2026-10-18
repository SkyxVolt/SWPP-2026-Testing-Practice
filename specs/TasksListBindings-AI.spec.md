# Spec: `TasksListBindings.kt`

> Post-conditions are the test oracle: write the expected behavior from intent, not from the code.
> Tag each post-condition [Unit] if its test runs on the JVM; otherwise tag [Integration].
> End each valid case with `Tested by <test>` if an existing test checks it, or `Tip:` describing the test to write.

## `setDueDateLabel(textView: TextView, task: Task?)`

**Intent**: Show the due-date label for a task in the task list. When there is no label to show, clear and hide the TextView so a recycled row cannot display stale content.

**Pre-conditions**
- `textView` is a non-null TextView.
- `task` is null or a valid Task.

**Post-conditions**
- Success cases
  - For a completed task, show `"CLEAR"` and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and a completed task with due date `2026-10-20` while the current date is `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"CLEAR"` and visibility is `VISIBLE`.
  - For an active task due today, show `"D-Day"` and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due on the current date `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"D-Day"` and visibility is `VISIBLE`.
  - For an active task due in the future, show the day-count label and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due `2026-10-21` when the current date is `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"D-3"` and visibility is `VISIBLE`.
  - For an active overdue task, show the overdue label and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due `2026-10-15` when the current date is `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"D+3"` and visibility is `VISIBLE`.
- Error cases
  - None. A null task is accepted and results in no displayed label. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `VISIBLE`, and `task = null` / When `setDueDateLabel` is called / Then the TextView text is `""` and visibility is `GONE`.
- Edge cases
  - An active task with no due date clears the text and hides the TextView. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `VISIBLE`, and an active task with `dueDate = null` / When `setDueDateLabel` is called / Then the TextView text is `""` and visibility is `GONE`.
  - A due date one calendar day in the future displays `"D-1"` and is visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due `2026-10-19` when the current date is `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"D-1"` and visibility is `VISIBLE`.
  - A due date one calendar day in the past displays `"D+1"` and is visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due `2026-10-17` when the current date is `2026-10-18` / When `setDueDateLabel` is called / Then the TextView text is `"D+1"` and visibility is `VISIBLE`.

**Undefined behavior** (optional)
- None.
