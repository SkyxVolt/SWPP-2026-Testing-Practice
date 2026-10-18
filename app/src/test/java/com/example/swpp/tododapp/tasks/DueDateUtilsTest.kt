package com.example.swpp.tododapp.tasks

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class DueDateUtilsTest {
    @Test
    fun dueDateLabel_completedWithDueDate_returnsClear() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 10, 20)
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = true)

        // THEN
        assertEquals("CLEAR", result)
    }

    @Test
    fun dueDateLabel_completedWithoutDueDate_returnsClear() {
        // GIVEN
        val dueDate: LocalDate? = null
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = true)

        // THEN
        assertEquals("CLEAR", result)
    }

    @Test
    fun dueDateLabel_activeWithoutDueDate_returnsEmptyString() {
        // GIVEN
        val dueDate: LocalDate? = null
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("", result)
    }

    @Test
    fun dueDateLabel_dueToday_returnsDday() {
        // GIVEN
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(today, today, isCompleted = false)

        // THEN
        assertEquals("D-Day", result)
    }

    @Test
    fun dueDateLabel_dueThreeDaysAhead_returnsDminus3() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 10, 21)
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D-3", result)
    }

    @Test
    fun dueDateLabel_overdueByThreeDays_returnsDplus3() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 10, 15)
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D+3", result)
    }

    @Test
    fun dueDateLabel_dueTomorrow_returnsDminus1() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 10, 19)
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D-1", result)
    }

    @Test
    fun dueDateLabel_dueYesterday_returnsDplus1() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 10, 17)
        val today = LocalDate.of(2026, 10, 18)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D+1", result)
    }

    @Test
    fun dueDateLabel_dueAfterMonthBoundary_returnsDminus1() {
        // GIVEN
        val dueDate = LocalDate.of(2026, 11, 1)
        val today = LocalDate.of(2026, 10, 31)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D-1", result)
    }

    @Test
    fun dueDateLabel_dueAfterYearBoundary_returnsDminus1() {
        // GIVEN
        val dueDate = LocalDate.of(2027, 1, 1)
        val today = LocalDate.of(2026, 12, 31)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D-1", result)
    }

    @Test
    fun dueDateLabel_dueAfterLeapDay_returnsDminus1() {
        // GIVEN
        val dueDate = LocalDate.of(2024, 2, 29)
        val today = LocalDate.of(2024, 2, 28)

        // WHEN
        val result = dueDateLabel(dueDate, today, isCompleted = false)

        // THEN
        assertEquals("D-1", result)
    }
}
