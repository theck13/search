package com.heckofanapp.storybook

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.requestFocus
import com.heckofanapp.search.Search
import com.heckofanapp.search.SearchAvatar
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TAG = "Search"

class TestSearch {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    var isExpanded by mutableStateOf(false)
    var isFocused by mutableStateOf(false)
    var query by mutableStateOf("")

    lateinit var descriptionAvatar: String
    lateinit var descriptionBack: String
    lateinit var descriptionClear: String
    lateinit var descriptionSearch: String
    lateinit var focusManager: FocusManager
    lateinit var placeholderCollapsed: String
    lateinit var placeholderExpanded: String

    @Before
    fun before() {
        descriptionAvatar = rule.activity.getString(R.string.test_action_avatar)
        descriptionBack = rule.activity.getString(R.string.test_action_back)
        descriptionClear = rule.activity.getString(R.string.test_action_clear)
        descriptionSearch = rule.activity.getString(R.string.test_action_search)
        placeholderCollapsed = rule.activity.getString(R.string.test_placeholder_collapsed)
        placeholderExpanded = rule.activity.getString(R.string.test_placeholder_expanded)

        rule.setContent {
            focusManager = LocalFocusManager.current

            Search(
                modifier = Modifier.testTag(TAG),
                avatar = SearchAvatar(
                    description = descriptionAvatar,
                    image = {},
                    onClick = {},
                ),
                descriptionClear = descriptionClear,
                descriptionCollapsed = descriptionSearch,
                descriptionExpanded = descriptionBack,
                focusManager = focusManager,
                isExpanded = isExpanded,
                onFocusChange = { hasFocus ->
                    if (hasFocus) {
                        isExpanded = true
                        isFocused = true
                    } else {
                        isFocused = false
                    }
                },
                onIconClicked = {
                    isExpanded = isExpanded.not()
                    isFocused = isFocused.not()
                },
                onQueryChange = { queryChange ->
                    query = queryChange
                },
                placeholder =
                    if (isExpanded) {
                        placeholderExpanded
                    } else {
                        placeholderCollapsed
                    },
                query = query,
            )
        }
    }

    @Test
    fun hasAvatar() {
        val avatar = rule.onNodeWithContentDescription(
            label = descriptionAvatar,
        )
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        avatar.assertExists()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()
        avatar.assertExists()

        val text = "test"
        field.assertTextEquals("")
        field.performTextInput(text)
        field.assertTextEquals(text)
        avatar.assertExists()

        val back = getBack()
        back.performClick()
        avatar.assertExists()
        avatar.performTouchInput {
            longClick()
        }
        avatar.assertExists()
    }

    @Test
    fun hasNoNewlineAfterInsertNewline() {
        val field = getField()
        val newline = "\n"
        field.performClick()
        field.performTextInput(
            text = newline,
        )
        field.assert(
            hasText(
                substring = true,
                text = newline,
            ).not()
        )
    }

    @Test
    fun hasNoTextAfterInsertQueryAndClear() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()

        val clear = rule.onNodeWithContentDescription(
            label = descriptionClear,
        )
        val text = "test"
        field.assertTextEquals("")
        field.performTextInput(text)
        field.assertTextEquals(text)
        clear.performClick()
        field.assertTextEquals("")
    }

    @Test
    fun hasNoTextAfterInsertQueryAndCollapseThenClear() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()

        val text = "test"
        field.assertTextEquals("")
        field.performTextInput(text)
        field.assertTextEquals(text)

        val icon = rule.onNodeWithContentDescription(
            label = descriptionBack,
        )
        icon.performClick()

        val query = rule.onNodeWithText(
            text = text,
        )
        query.assertExists()

        val clear = rule.onNodeWithContentDescription(
            label = descriptionClear,
        )
        clear.performClick()
        field.assertTextEquals("")
        query.assertDoesNotExist()
    }

    @Test
    fun hasTextAfterInsertQuery() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()

        val textFirst = "first"
        val textSecond = "second"
        field.assertTextEquals("")
        field.performTextInput(textFirst)
        field.assertTextEquals(textFirst)
        field.performTextInput(textSecond)
        field.assertTextEquals(textFirst + textSecond)
    }

    @Test
    fun hasTextAfterInsertQueryAndCollapse() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()

        val text = "test"
        field.assertTextEquals("")
        field.performTextInput(text)
        field.assertTextEquals(text)

        val icon = rule.onNodeWithContentDescription(
            label = descriptionBack,
        )
        icon.performClick()

        val query = rule.onNodeWithText(
            text = text,
        )
        query.assertExists()
    }

    @Test
    fun isExpandedAndFocusedAfterClickField() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()
    }

    @Test
    fun isExpandedAndNotFocusedAfterToggleFocused() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.requestFocus()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()
        rule.runOnIdle {
            focusManager.clearFocus()
        }
        assert(isExpanded)
        assert(isFocused.not())
        field.assertIsNotFocused()
    }

    @Test
    fun isExpandedAndFocusedThenNotAfterToggleIcon() {
        val field = getField()
        val icon = getIcon()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        icon.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()
        icon.performClick()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
    }

    @Test
    fun showsTooltipAfterLongPressIconClear() {
        val field = getField()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        field.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()

        val clear = rule.onNodeWithContentDescription(
            label = descriptionClear,
        )
        val text = "test"
        field.assertTextEquals("")
        field.performTextInput(text)
        field.assertTextEquals(text)
        clear.performTouchInput {
            longClick()
        }

        val tooltip = rule.onNodeWithText(
            text = descriptionClear,
        )
        tooltip.assertExists()
    }

    @Test
    fun showsTooltipAfterLongClickIconCollapsed() {
        val field = getField()
        val icon = getIcon()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        icon.performTouchInput {
            longClick()
        }

        val tooltip = rule.onNodeWithText(
            text = descriptionSearch,
        )
        tooltip.assertExists()
    }

    @Test
    fun showsTooltipAfterLongClickIconExpanded() {
        val field = getField()
        val icon = getIcon()
        assert(isExpanded.not())
        assert(isFocused.not())
        field.assertIsNotFocused()
        icon.performClick()
        assert(isExpanded)
        assert(isFocused)
        field.assertIsFocused()
        icon.performTouchInput {
            longClick()
        }
        val tooltip = rule.onNodeWithText(
            text = descriptionBack,
        )
        tooltip.assertExists()
    }

    private fun getField(): SemanticsNodeInteraction {
        return rule
            .onNodeWithTag(
                testTag = TAG,
            )
            .onChildren()
            .filterToOne(
                matcher = hasSetTextAction(),
            )
    }
    private fun getBack(): SemanticsNodeInteraction {
        return rule.onNodeWithContentDescription(
            label = descriptionBack,
        )
    }

    private fun getIcon(): SemanticsNodeInteraction {
        return getInput().onChildAt(
            index = 0,
        )
    }

    private fun getInput(): SemanticsNodeInteraction {
        return getField().onChildAt(
            index = 0,
        )
    }

    private fun getQuery(
        text: String,
    ): SemanticsNodeInteraction {
        return rule.onNodeWithText(
            text = text,
        )
    }
}
