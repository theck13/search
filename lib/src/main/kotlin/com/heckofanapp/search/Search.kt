package com.heckofanapp.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heckofanapp.search.component.Spacer2xs
import com.heckofanapp.search.component.TooltipBox
import com.heckofanapp.search.component.TooltipBoxPosition
import com.heckofanapp.search.component.Typography
import com.heckofanapp.search.extension.isNewline
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.theme.RippleConfiguration
import kotlinx.coroutines.launch
import androidx.compose.material3.Card as MaterialCard
import androidx.compose.material3.IconButton as MaterialIconButton
import androidx.compose.ui.graphics.Color as ComposeColor

/**
 * [MaterialCard] with [MaterialIconButton]s and [OutlinedTextField] to use for searching.
 *
 * @param modifier optional [Modifier] to apply to [MaterialCard]
 * @param avatar optional [SearchAvatar] to set as image content at end of layout
 * @param colorCollapsed optional [Color] to set background when search field is collapsed
 * @param colorExpanded optional [Color] to set background when search field is expanded
 * @param descriptionClear required [String] to describe clear icon for accessibility services
 * @param descriptionCollapsed required [String] to describe collapsed icon for accessibility services
 * @param descriptionExpanded required [String] to describe expanded icon for accessibility services
 * @param focusManager optional [FocusManager] to manage focus of [OutlinedTextField]
 * @param focusRequester optional [FocusRequester] to request focus for [OutlinedTextField]
 * @param isExpanded optional [Boolean] to set search field expanded or collapsed
 * @param isMenu optional [Boolean] to set collapsed icon as menu
 * @param keyboardActions optional [KeyboardActions] to set callbacks for keyboard actions
 * @param keyboardOptions optional [KeyboardOptions] to set keyboard configuration
 * @param onFocusChange required [Unit] callback triggered when search field focus changed with [Boolean] as argument
 * @param onIconClicked required [Unit] callback triggered when icon is clicked
 * @param onQueryChange optional [Unit] callback triggered when query value changed with [String] as argument
 * @param placeholder optional [String] in place of query when input is empty
 * @param query optional [String] to set as text in [OutlinedTextField]
 */
@Composable
fun Search(
    modifier: Modifier = Modifier,
    avatar: SearchAvatar? = null,
    colorCollapsed: ComposeColor = Color.backgroundNeutralHigh,
    colorExpanded: ComposeColor = Color.backgroundNeutralHigh,
    descriptionClear: String,
    descriptionCollapsed: String,
    descriptionExpanded: String,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = remember { FocusRequester() },
    isExpanded: Boolean = false,
    isMenu: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search,
        keyboardType = KeyboardType.Text,
    ),
    onFocusChange: (Boolean) -> Unit,
    onIconClicked: () -> Unit,
    onQueryChange: ((String) -> Unit)? = null,
    placeholder: String? = null,
    query: String = "",
) {
    val atEnd by rememberSaveable(isExpanded) {
        mutableStateOf(isExpanded)
    }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val descriptionCollapsedExpanded = if (isExpanded) descriptionExpanded else descriptionCollapsed
    val drawable by remember(isMenu) {
        mutableIntStateOf(if (isMenu) SearchType.Menu.drawable else SearchType.Search.drawable)
    }
    val painter = key(drawable) {
        rememberAnimatedVectorPainter(
            animatedImageVector = AnimatedImageVector.animatedVectorResource(drawable),
            atEnd = atEnd,
        )
    }
    val spacing = Token.Spacing2xs * 2 // Vertical padding of text field.  Multiplied by 2 for bottom and top.
    val textFieldValue = rememberSaveable(
        stateSaver = TextFieldValue.Saver,
    ) {
        mutableStateOf(
            TextFieldValue(
                text = query,
            )
        )
    }
    val text = textFieldValue.value.copy(
        text = query,
    )

    val colorBackground by animateColorAsState(
        label = "Color Background",
        targetValue = if (isExpanded) colorExpanded else colorCollapsed,
    )
    val colorPlaceholder by animateColorAsState(
        label = "Color Placeholder",
        targetValue = if (isExpanded) Color.textIconDisabled else Color.textIconNeutral
    )

    val height by animateDpAsState(
        label = "Height",
        targetValue = if (isExpanded) Token.Spacing3xl + spacing else Token.Spacing3xl - spacing,
    )

    val padding by animateDpAsState(
        label = "Padding",
        targetValue = if (isExpanded) 0.dp else spacing,
    )

    val radius by animateDpAsState(
        label = "Radius",
        targetValue = if (isExpanded) Token.CornerRadiusNone else height / 2, // Divided by 2 for circular end and start.
    )

    val shape = RoundedCornerShape(
        size = radius,
    )

    val setQueryChange = { queryChange: TextFieldValue ->
        textFieldValue.value = queryChange
        onQueryChange?.invoke(queryChange.text)
    }

    RippleConfiguration {
        MaterialCard(
            modifier = modifier.padding(
                vertical = padding,
            ),
            colors = CardDefaults.cardColors(
                containerColor = colorBackground,
                contentColor = colorBackground,
                disabledContainerColor = Color.backgroundNeutral,
                disabledContentColor = Color.backgroundNeutral,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Elevation.Level0,
                disabledElevation = Elevation.Level0,
                draggedElevation = Elevation.Level0,
                focusedElevation = Elevation.Level0,
                hoveredElevation = Elevation.Level0,
                pressedElevation = Elevation.Level0,
            ),
            shape = shape,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .defaultMinSize(
                        minHeight = height,
                    )
                    .fillMaxWidth()
                    .focusRequester(
                        focusRequester = focusRequester,
                    )
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }

                        onFocusChange(focusState.hasFocus)
                    }
                    .semantics {
                        contentDescription = query.ifEmpty { placeholder.orEmpty() }
                    },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.textIconNeutral,
                    disabledBorderColor = ComposeColor.Transparent,
                    disabledPlaceholderColor = colorPlaceholder,
                    disabledTextColor = Color.textIconDisabled,
                    errorBorderColor = ComposeColor.Transparent,
                    errorCursorColor = Color.borderCritical,
                    focusedBorderColor = ComposeColor.Transparent,
                    focusedContainerColor = ComposeColor.Transparent,
                    focusedPlaceholderColor = colorPlaceholder,
                    focusedTextColor = Color.textIconNeutral,
                    unfocusedBorderColor = ComposeColor.Transparent,
                    unfocusedContainerColor = ComposeColor.Transparent,
                    unfocusedPlaceholderColor = colorPlaceholder,
                    unfocusedTextColor = Color.textIconNeutral,
                ),
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                leadingIcon = {
                    TooltipBox(
                        position = TooltipBoxPosition.Below,
                        text = descriptionCollapsedExpanded,
                    ) {
                        MaterialIconButton(
                            onClick = {
                                onIconClicked()
                            }
                        ) {
                            Image(
                                colorFilter = ColorFilter.tint(
                                    color = Color.textIconNeutral,
                                ),
                                contentDescription = descriptionCollapsedExpanded,
                                painter = painter,
                            )
                        }
                    }
                },
                onValueChange = {
                    setQueryChange(
                        it.copy(
                            text = it.text.filter { char ->
                                char.isNewline().not()
                            }
                        )
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier.clearAndSetSemantics {},
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = Typography.BodyLarge.Default,
                        text = placeholder.orEmpty(),
                    )
                },
                singleLine = true,
                textStyle = Typography.BodyLarge.Default,
                trailingIcon = {
                    Row {
                        AnimatedVisibility(
                            content = {
                                TooltipBox(
                                    position = TooltipBoxPosition.Below,
                                    text = descriptionClear,
                                ) {
                                    MaterialIconButton(
                                        modifier = Modifier.size(
                                            size = Token.TouchTargetMinimum,
                                        ),
                                        onClick = {
                                            setQueryChange(
                                                TextFieldValue(
                                                    text = "",
                                                )
                                            )
                                        },
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(
                                                size = Token.IconSizeMedium,
                                            ),
                                            contentDescription = descriptionClear,
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_clear_24),
                                            tint = Color.textIconNeutral,
                                        )
                                    }
                                }
                            },
                            enter = fadeIn(),
                            exit = fadeOut(),
                            visible = text.text.isNotEmpty(),
                        )

                        avatar?.let {
                            TooltipBox(
                                position = TooltipBoxPosition.Below,
                                text = avatar.description,
                            ) {
                                MaterialIconButton(
                                    modifier = Modifier.size(
                                        size = Token.TouchTargetMinimum,
                                    ),
                                    onClick = {
                                        avatar.onClick()
                                    },
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(
                                                size = 32.dp,
                                            )
                                            .semantics {
                                                contentDescription = avatar.description
                                            },
                                    ) {
                                        avatar.image()
                                    }

                                    Spacer2xs()
                                }
                            }
                        }
                    }
                },
                value = text,
            )
        }
    }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
        }
    }
}

data class SearchAvatar(
    val description: String,
    val image: @Composable () -> Unit,
    val onClick: () -> Unit,
)

enum class SearchType(
    @param:DrawableRes val drawable: Int,
    @param:StringRes val string: Int,
) {
    Menu(
        drawable = R.drawable.av_from_menu_to_arrow_start_24,
        string = R.string.icon_menu,
    ),
    Search(
        drawable = R.drawable.av_from_search_to_arrow_start_24,
        string = R.string.icon_search,
    ),
}

@Composable
fun PreviewSearch(
    isMenu: Boolean,
    width: Dp,
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
    val list = listOf(
        Triple(
            "Default",
            null,
            Triple(
                SolidColor(Color.backgroundTransparent),
                Color.backgroundNeutralHigh,
                Color.backgroundNeutralHigh,
            ),
        ),
        Triple(
            "Custom",
            SearchAvatar(
                description = "",
                image = {
                    Image(
                        modifier = Modifier.clip(
                            shape = CircleShape,
                        ).size(
                            size = 32.dp,
                        ),
                        contentDescription = "",
                        painter = ColorPainter(
                            color = Color.textIconNeutral,
                        ),
                    )
                },
                onClick = {},
            ),
            Triple(
                gradient,
                Color.backgroundTranslucent,
                Color.backgroundTransparent,
            ),
        ),
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = Token.SpacingMedium,
        ),
    ) {
        list.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = Token.SpacingMedium,
                ),
            ) {
                Spacer2xs()

                Text(
                    color = Color.textIconNeutral,
                    style = Typography.HeadingMedium.Default,
                    text = item.first,
                )

                PreviewSearchBox(
                    isExpanded = false,
                    isMenu = isMenu,
                    item = item,
                    placeholder = "Search",
                    query = "",
                    width = width,
                )

                PreviewSearchBox(
                    isExpanded = true,
                    isMenu = isMenu,
                    item = item,
                    placeholder = "Enter search query",
                    query = "",
                    width = width,
                )

                PreviewSearchBox(
                    isExpanded = true,
                    isMenu = isMenu,
                    item = item,
                    placeholder = "Enter search query",
                    query = "query",
                    width = width,
                )
            }
        }
    }
}

@Composable
fun PreviewSearchBox(
    isExpanded: Boolean,
    isMenu: Boolean,
    item: Triple<String, SearchAvatar?, Triple<Brush, ComposeColor, ComposeColor>>,
    placeholder: String,
    query: String,
    width: Dp,
) {
    val descriptionClear = "Clear"
    val descriptionCollapsed = "Search"
    val descriptionExpanded = "Back"
    val paddingMediumToZero by animateDpAsState(
        label = "Padding Medium to Zero",
        targetValue = if (isExpanded) 0.dp else Token.SpacingMedium,
    )
    val modifier = Modifier.width(
        width = if (isExpanded) (Token.SpacingMedium * 2) + width else width, // Multiplied by 2 for end and start.
    )

    Box(
        modifier = Modifier
            .background(
                brush = item.third.first,
            )
            .padding(
                horizontal = paddingMediumToZero,
            ),
    ) {
        Search(
            modifier = modifier,
            avatar = item.second,
            colorCollapsed = item.third.second,
            colorExpanded = item.third.third,
            descriptionClear = descriptionClear,
            descriptionCollapsed = descriptionCollapsed,
            descriptionExpanded = descriptionExpanded,
            isExpanded = isExpanded,
            isMenu = isMenu,
            onFocusChange = {},
            onIconClicked = {},
            placeholder = placeholder,
            query = query,
        )
    }
}

@PreviewDarkLight
@Composable
fun PreviewSearchPhone() {
    PreviewSearch(
        isMenu = false,
        width = 320.dp,
    )
}

@PreviewDarkLight
@Composable
fun PreviewSearchTablet() {
    PreviewSearch(
        isMenu = true,
        width = 640.dp,
    )
}
