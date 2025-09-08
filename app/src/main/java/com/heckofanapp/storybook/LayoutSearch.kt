@file:OptIn(
    ExperimentalMaterial3Api::class,
)

package com.heckofanapp.storybook

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.PreviewDarkLightPhoneLandscape
import com.heckofanapp.search.PreviewDarkLightPhonePortrait
import com.heckofanapp.search.Search
import com.heckofanapp.search.SearchType
import com.heckofanapp.search.Token
import com.heckofanapp.search.component.Spacer2xl
import com.heckofanapp.search.component.Spacer3xl
import com.heckofanapp.search.component.SpacerLarge
import com.heckofanapp.search.component.SpacerMedium
import com.heckofanapp.search.component.SpacerSmall
import com.heckofanapp.search.component.SpacerXl
import com.heckofanapp.search.component.SpacerXs
import com.heckofanapp.search.component.Typography
import com.heckofanapp.storybook.composable.ListItemGame
import com.heckofanapp.storybook.composable.TextSection
import com.heckofanapp.storybook.composable.TextSwitch
import com.heckofanapp.storybook.composable.navigationBarsWithPadding
import com.heckofanapp.storybook.composable.safeDrawingExcludeSystemBars
import com.heckofanapp.storybook.extension.matchStringWithQueryIgnoringCase
import com.heckofanapp.storybook.theme.StorybookTheme

@Composable
fun LayoutSearch(
    title: String,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    StorybookTheme {
        Scaffold(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .nestedScroll(
                    connection = scrollBehavior.nestedScrollConnection,
                )
                .windowInsetsPadding(
                    insets = safeDrawingExcludeSystemBars()
                ),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    ),
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            color = Color.textIconNeutral,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = Typography.HeadingMedium.Default,
                            text = title,
                        )
                    },
                )
            },
        ) { padding ->
            BoxWithConstraints {
                val isPortrait = maxWidth < Token.BreakpointNormal

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            state = rememberScrollState(),
                            enabled = true,
                        )
                        .padding(
                            top = padding.calculateTopPadding(),
                        )
                        .padding(
                            paddingValues = navigationBarsWithPadding(
                                padding = PaddingValues(
                                    vertical = Token.SpacingMedium,
                                ),
                            ),
                        ),
                ) {
                    TextSection(
                        modifier = Modifier.padding(
                            horizontal = Token.SpacingMedium,
                        ),
                        textSubtitle = stringResource(R.string.components_search_description),
                    )

                    SpacerLarge()

                    SearchAttributes(
                        isPortrait = isPortrait,
                    )

                    Spacer2xl()

                    SearchExamples(
                        isPortrait = isPortrait,
                    )
                }
            }
        }
    }
}

@Composable
fun SearchAttributes(
    isPortrait: Boolean,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var iconSelected by rememberSaveable { mutableStateOf(SearchType.Search) }
    var isCheckedExpanded by rememberSaveable { mutableStateOf(false) }
    var isCheckedFocused by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    val onCheckedExpandedChange = { checked: Boolean ->
        if (checked) {
            focusRequester.requestFocus()
            isCheckedExpanded = true
            isCheckedFocused = true
        } else {
            focusManager.clearFocus()
            isCheckedExpanded = false
            isCheckedFocused = false
        }
    }
    val onCheckedFocusedChange = { checked: Boolean ->
        if (checked) {
            focusRequester.requestFocus()
            isCheckedExpanded = true
            isCheckedFocused = true
        } else {
            focusManager.clearFocus()
            isCheckedFocused = false
        }
    }

    BackHandler(
        enabled = isCheckedExpanded,
    ) {
        isCheckedExpanded = false
        isCheckedFocused = false
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.weight(
                weight = if (isPortrait) Constant.WeightFull else Constant.WeightColumnStart,
            ),
            horizontalAlignment = if (isPortrait) Alignment.CenterHorizontally else Alignment.Start,
        ) {
            TextSection(
                modifier = Modifier.padding(
                    horizontal = Token.SpacingMedium,
                ),
                textSubtitle = stringResource(R.string.attributes_search_description),
                textTitle = stringResource(R.string.attributes),
            )

            SpacerLarge()

            Box(
                modifier = Modifier.padding(
                    horizontal = Token.SpacingMedium,
                )
            ) {
                Search(
                    descriptionClear = stringResource(R.string.action_clear),
                    descriptionCollapsed = stringResource(iconSelected.string),
                    descriptionExpanded = stringResource(R.string.action_back),
                    focusManager = focusManager,
                    focusRequester = focusRequester,
                    isExpanded = isCheckedExpanded,
                    isMenu =
                        when (iconSelected) {
                            SearchType.Menu -> true
                            SearchType.Search -> false
                        },
                    onFocusChange = { hasFocus ->
                        if (hasFocus) {
                            isCheckedExpanded = true
                            isCheckedFocused = true
                        } else {
                            isCheckedFocused = false
                        }
                    },
                    onIconClicked = {
                        isCheckedExpanded = isCheckedExpanded.not()
                        isCheckedFocused = isCheckedFocused.not()
                    },
                    onQueryChange = { queryChange ->
                        query = queryChange
                    },
                    placeholder = stringResource(R.string.action_search),
                    query = query,
                )
            }

            if (isPortrait) {
                SpacerLarge()

                SearchAttributesOptions(
                    iconSelected = iconSelected,
                    isCheckedExpanded = isCheckedExpanded,
                    isCheckedFocused = isCheckedFocused,
                    isPortrait = isPortrait,
                    onCheckedExpandedChange = { checked ->
                        onCheckedExpandedChange(checked)
                    },
                    onCheckedFocusedChange = { checked ->
                        onCheckedFocusedChange(checked)
                    },
                    onIconSelected = { selected ->
                        iconSelected = selected
                    },
                )
            }
        }

        if (isPortrait.not()) {
            Column(
                modifier = Modifier
                    .padding(
                        start = Token.SpacingLarge,
                    )
                    .weight(
                        weight = Constant.WeightColumnEnd,
                    ),
            ) {
                SearchAttributesOptions(
                    iconSelected = iconSelected,
                    isCheckedExpanded = isCheckedExpanded,
                    isCheckedFocused = isCheckedFocused,
                    isPortrait = isPortrait,
                    onCheckedExpandedChange = { checked ->
                        onCheckedExpandedChange(checked)
                    },
                    onCheckedFocusedChange = { checked ->
                        onCheckedFocusedChange(checked)
                    },
                    onIconSelected = { selected ->
                        iconSelected = selected
                    },
                )
            }
        }
    }
}

@Composable
fun SearchAttributesDropdown(
    iconSelected: SearchType,
    isPortrait: Boolean,
    onIconSelected: (SearchType) -> Unit,
) {
    var isExpandedDropdown by rememberSaveable { mutableStateOf(false) }

    val degrees by animateFloatAsState(
        label = "Degrees",
        targetValue = if (isExpandedDropdown) 180f else 0f,
    )
    val modifier =
        if (isPortrait) {
            Modifier.width(
                width = TextFieldDefaults.MinWidth,
            )
        } else {
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Token.SpacingMedium,
                )
        }

    ExposedDropdownMenuBox(
        expanded = isExpandedDropdown,
        onExpandedChange = {
            isExpandedDropdown = !isExpandedDropdown
        },
    ) {
        TextField(
            modifier = modifier.menuAnchor(
                type = MenuAnchorType.PrimaryNotEditable,
            ),
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                disabledContainerColor = Color.backgroundNeutralHigh,
                disabledLabelColor = Color.textIconDisabled,
                focusedContainerColor = Color.backgroundNeutralHigh,
                focusedIndicatorColor = Color.backgroundTransparent,
                unfocusedContainerColor = Color.backgroundNeutralHigh,
                unfocusedIndicatorColor = Color.backgroundTransparent,
            ),
            label = {
                Text(
                    text = stringResource(R.string.attributes_search_icon_dropdown),
                )
            },
            onValueChange = {},
            readOnly = true,
            textStyle = Typography.BodyMedium.Default,
            trailingIcon = {
                Icon(
                    modifier = Modifier.rotate(
                        degrees = degrees,
                    ),
                    contentDescription = null,
                    imageVector = Icons.Filled.ArrowDropDown,
                )
            },
            value = stringResource(iconSelected.string),
        )

        ExposedDropdownMenu(
            modifier = Modifier.background(
                color = Color.backgroundNeutralHigh,
            ),
            expanded = isExpandedDropdown,
            onDismissRequest = {
                isExpandedDropdown = false
            },
        ) {
            listOf(
                SearchType.Menu,
                SearchType.Search,
            ).forEach { icon ->
                DropdownMenuItem(
                    onClick = {
                        isExpandedDropdown = false
                        onIconSelected(icon)
                    },
                    text = {
                        Text(
                            style = Typography.BodyMedium.Default,
                            text = stringResource(icon.string),
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun SearchAttributesOptions(
    iconSelected: SearchType,
    isCheckedExpanded: Boolean,
    isCheckedFocused: Boolean,
    isPortrait: Boolean,
    onCheckedExpandedChange: (Boolean) -> Unit,
    onCheckedFocusedChange: (Boolean) -> Unit,
    onIconSelected: (SearchType) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextSwitch(
            isChecked = isCheckedExpanded,
            onCheckedChange = { checked ->
                onCheckedExpandedChange(checked)
            },
            padding = PaddingValues(
                horizontal = Token.SpacingMedium,
            ),
            text = stringResource(R.string.attributes_search_expanded),
        )

        TextSwitch(
            isChecked = isCheckedFocused,
            onCheckedChange = { checked ->
                onCheckedFocusedChange(checked)
            },
            padding = PaddingValues(
                horizontal = Token.SpacingMedium,
            ),
            text = stringResource(R.string.attributes_search_focused),
        )

        if (isPortrait) {
            SpacerMedium()
        } else {
            SpacerSmall()
        }

        SearchAttributesDropdown(
            iconSelected = iconSelected,
            isPortrait = isPortrait,
            onIconSelected = { selected ->
                onIconSelected(selected)
            },
        )
    }
}

@Composable
fun SearchExamples(
    isPortrait: Boolean,
) {
    val colorDark = Token.AccentWeakDark
    val colorLight = Token.AccentWeakLight
    val colorStart = Color.backgroundTransparent
    val gradient =
        if (isSystemInDarkTheme()) {
            Brush.linearGradient(
                colors = listOf(
                    colorStart,
                    colorDark,
                    colorLight,
                )
            )
        } else {
            Brush.linearGradient(
                colors = listOf(
                    colorStart,
                    colorLight,
                    colorDark,
                )
            )
        }

    var isExpanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    val paddingMediumToZero by animateDpAsState(
        label = "Padding Medium to Zero",
        targetValue = if (isExpanded) 0.dp else Token.SpacingMedium,
    )

    BackHandler(
        enabled = isExpanded,
    ) {
        isExpanded = false
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.weight(
                weight = Constant.WeightColumnStart,
            ),
            horizontalAlignment = if (isPortrait) Alignment.CenterHorizontally else Alignment.Start,
        ) {
            TextSection(
                modifier = Modifier.padding(
                    horizontal = Token.SpacingMedium,
                ),
                textSubtitle = stringResource(R.string.examples_search_description),
                textTitle = stringResource(R.string.examples),
            )

            SpacerLarge()

            Box(
                modifier = Modifier
                    .background(
                        brush = gradient,
                    )
                    .padding(
                        horizontal = paddingMediumToZero,
                    ),
            ) {
                Search(
                    colorCollapsed = Color.backgroundTranslucent,
                    colorExpanded = Color.backgroundTransparent,
                    descriptionClear = stringResource(R.string.action_clear),
                    descriptionCollapsed = stringResource(R.string.action_search),
                    descriptionExpanded = stringResource(R.string.action_back),
                    isExpanded = isExpanded,
                    isMenu = false,
                    onFocusChange = { hasFocus ->
                        if (hasFocus) {
                            isExpanded = true
                        }
                    },
                    onIconClicked = {
                        isExpanded = isExpanded.not()
                    },
                    onQueryChange = { queryChange ->
                        query = queryChange
                    },
                    placeholder = stringResource(
                        if (isExpanded) {
                            R.string.examples_search_placeholder_expanded
                        } else {
                            R.string.examples_search_placeholder_collapsed
                        }
                    ),
                    query = query,
                )
            }

            if (isPortrait) {
                SpacerXs()

                SearchExamplesComponentList(
                    query = query,
                )
            }
        }

        if (isPortrait.not()) {
            Column(
                modifier = Modifier
                    .padding(
                        start = Token.SpacingLarge,
                    )
                    .weight(
                        weight = Constant.WeightColumnEnd,
                    ),
            ) {
                // Spacer3xl and SpacerXl are used to align list with search field in landscape.
                Spacer3xl()
                SpacerXl()

                SearchExamplesComponentList(
                    query = query,
                )
            }
        }
    }
}

@Composable
private fun SearchExamplesComponentList(
    query: String,
) {
    val context = LocalContext.current

    var toast: Toast? = null

    games.forEach { game ->
        AnimatedVisibility(
            content = {
                ListItemGame(
                    game = game,
                    onGameClicked = { resource ->
                        toast?.cancel()
                        toast = Toast.makeText(
                            context,
                            context.getString(resource),
                            Toast.LENGTH_SHORT,
                        )
                        toast?.show()
                    },
                )
            },
            enter = expandVertically(),
            exit = shrinkVertically(),
            visible =
                game.console.text.matchStringWithQueryIgnoringCase(
                    query = query,
                ) ||
                game.title.matchStringWithQueryIgnoringCase(
                    query = query,
                ),
        )
    }
}

@PreviewDarkLightPhonePortrait
@PreviewDarkLightPhoneLandscape
@Composable
fun PreviewLayoutSearch() {
    StorybookTheme {
        LayoutSearch(
            title = NavigationRoute.Search.name,
        )
    }
}
