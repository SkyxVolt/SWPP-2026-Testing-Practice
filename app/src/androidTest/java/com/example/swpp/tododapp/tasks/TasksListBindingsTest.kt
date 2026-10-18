package com.example.swpp.tododapp.tasks

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.swpp.tododapp.data.Task
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksListBindingsTest {
    private fun textView(initialText: String = "old", initialVisibility: Int = View.GONE) =
        TextView(ApplicationProvider.getApplicationContext()).apply {
            text = initialText
            visibility = initialVisibility
        }

    @Test
    fun setDueDateLabel_completedWithFutureDueDate_showsClearAndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = true, dueDate = today.plusDays(2))

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("CLEAR", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_completedWithoutDueDate_showsClearAndVisible() {
        // GIVEN
        val view = textView()
        val task = Task(isCompleted = true, dueDate = null)

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("CLEAR", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeDueToday_showsDdayAndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = false, dueDate = today)

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("D-Day", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeDueInFuture_showsDayCountAndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = false, dueDate = today.plusDays(3))

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("D-3", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeOverdue_showsOverdueCountAndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = false, dueDate = today.minusDays(3))

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("D+3", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_nullTask_clearsTextAndHidesView() {
        // GIVEN
        val view = textView(initialVisibility = View.VISIBLE)

        // WHEN
        setDueDateLabel(view, null)

        // THEN
        assertEquals("", view.text.toString())
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeWithoutDueDate_clearsTextAndHidesView() {
        // GIVEN
        val view = textView(initialVisibility = View.VISIBLE)
        val task = Task(isCompleted = false, dueDate = null)

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("", view.text.toString())
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeDueTomorrow_showsDminus1AndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = false, dueDate = today.plusDays(1))

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("D-1", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setDueDateLabel_activeDueYesterday_showsDplus1AndVisible() {
        // GIVEN
        val today = LocalDate.now()
        val view = textView()
        val task = Task(isCompleted = false, dueDate = today.minusDays(1))

        // WHEN
        setDueDateLabel(view, task)

        // THEN
        assertEquals("D+1", view.text.toString())
        assertEquals(View.VISIBLE, view.visibility)
    }
}
