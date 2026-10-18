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
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and a completed task with due date in the future` (get current date to compare) / When `setDueDateLabel` is called / Then the TextView text is `"CLEAR"` and visibility is `VISIBLE`.
  - For a completed task with no due date, show "CLEAR" and make the TextView visible. [Integration]
    - Tip: Given a TextView with text "old" and visibility GONE, and a completed task with dueDate = null / When setDueDateLabel is called / Then the TextView text is "CLEAR" and visibility is VISIBLE.
  - For an active task with no due date, set TextView's visibility to `GONE`
  - For an active task due today, show `"D-Day"` and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due on the current date/ When `setDueDateLabel` is called / Then the TextView text is `"D-Day"` and visibility is `VISIBLE`.
  - For an active task due in the future, show the day-count label and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due in the future (get current date to compare) / When `setDueDateLabel` is called / Then the TextView text is `"D-N"` where N is the number of days from current date to the due date, and visibility is `VISIBLE`.
  - For an active overdue task, show the overdue label and make the TextView visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due in the past / When `setDueDateLabel` is called / Then the TextView text is `"D+N"` where N is the number of days after the due date until the current date, and visibility is `VISIBLE`.
  - For a null task, clear the text and hide the TextView. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `VISIBLE`, and `task = null` / When `setDueDateLabel` 

- Error cases
  is called / Then the TextView text is `""` and visibility is `GONE`.

- Edge cases
  - An active task with no due date clears the text and hides the TextView. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `VISIBLE`, and an active task with `dueDate = null` / When `setDueDateLabel` is called / Then the TextView text is `""` and visibility is `GONE`.
  - A due date one calendar day in the future displays `"D-1"` and is visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due one day after the current date (get current date for comparison) / When `setDueDateLabel` is called / Then the TextView text is `"D-1"` and visibility is `VISIBLE`.
  - A due date one calendar day in the past displays `"D+1"` and is visible. `[Integration]`
    - Tip: Given a TextView with text `"old"` and visibility `GONE`, and an active task due one day before the current date (get current date for comparison) / When `setDueDateLabel` is called / Then the TextView text is `"D+1"` and visibility is `VISIBLE`.

**Undefined behavior** (optional)
- None.
