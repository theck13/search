# Search

A custom input field to use for searching.  Search allows users to enter a keyword or phrase and get relevant information.  It’s an alternative to other forms of filtering and navigation.  Search has two states; collapsed and expanded.

## Setting

To use the Search component, add the library as a dependency to the project build configuration.

### Groovy

To use Groovy, add the following to the `dependencies` block in the `build.gradle` file.

```groovy
implementation "com.heckofanapp:search:1.1.1"
```

### Kotlin

To use Kotlin, add the following to the `dependencies` block in the `build.gradle.kts` file.

```kotlin
implementation("com.heckofanapp:search:1.1.1")
```

### TOML

To use Kotlin and TOML, add the following to the `dependencies` block in the `build.gradle.kts` file and the `[libraries]` block in the `libs.versions.toml` file.

```kotlin
implementation(libs.heckofanapp.search)
```

```toml
heckofanapp-search = { group = "com.heckofanapp", name = "search", version = "1.1.1" }
```

## Specification

| Name                   | Type                                                                                                                                                          | Default                                                | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `modifier`             | [`Modifier`](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier) (optional)                                                          | `Modifier`                                             | to apply to Material [`Card`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Card(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.BorderStroke,androidx.compose.ui.unit.Dp,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0))                                                                                                                                                                                                                                                       |
| `avatar`               | `SearchAvatar` (optional)                                                                                                                                     | `null`                                                 | to set as image content at end of layout                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| `colorCollapsed`       | [`Color`](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color) (optional)                                                       | `Color.backgroundNeutralHigh`                          | to set background when search field is collapsed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| `colorExpanded`        | [`Color`](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color) (optional)                                                       | `Color.backgroundNeutralHigh`                          | to set background when search field is expanded                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| `descriptionClear`     | [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) (required)                                                                            |                                                        | to describe clear icon for accessibility services                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| `descriptionCollapsed` | [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) (required)                                                                            |                                                        | to describe collapsed icon for accessibility services                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `descriptionExpanded`  | [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) (required)                                                                            |                                                        | to describe expanded icon for accessibility services                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| `focusManager`         | [`FocusManager`](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusManager) (optional)                                            | `LocalFocusManager.current`                            | to manage focus of [`OutlinedTextField`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#OutlinedTextField(kotlin.String,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material.TextFieldColors))   |
| `focusRequester`       | [`FocusRequester`](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester) (optional)                                        | `remember { FocusRequester() }`                        | to request focus for [`OutlinedTextField`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#OutlinedTextField(kotlin.String,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material.TextFieldColors)) |
| `isExpanded`           | [`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean) (optional)                                                                          | `false`                                                | to set search field expanded or collapsed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| `isMenu`               | [`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean) (optional)                                                                          | `false`                                                | to set collapsed icon as menu                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `keyboardActions`      | [`KeyboardActions`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/text/KeyboardActions) (optional)                               | `KeyboardActions.Default`                              | to set callbacks for keyboard actions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `keyboardOptions`      | [`KeyboardOptions`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/text/KeyboardOptions) (optional)                               | `KeyboardOptions(ImeAction.Search, KeyboardType.Text)` | to set keyboard configuration                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `onFocusChange`        | ([`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean)) -> [`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit) (required) |                                                        | callback triggered when search field focus changed with `hasFocus` [`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean) as argument                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| `onIconClicked`        | () -> [`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit) (required)                                                                          |                                                        | callback triggered when icon is clicked                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `onQueryChange`        | ([`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string)) -> [`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit) (optional)   | `null`                                                 | callback triggered when query value changed with `queryChange` [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) as argument                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `placeholder`          | [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) (optional)                                                                            | `null`                                                 | in place of query when input is empty                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `query`                | [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string) (optional)                                                                            | `""`                                                   | to set as text in [`OutlinedTextField`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#OutlinedTextField(kotlin.String,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material.TextFieldColors))    |

```kotlin
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
)

data class SearchAvatar(
    val description: String,
    val image: @Composable () -> Unit,
    val onClick: () -> Unit,
)
```

## Sample

See the snippets below for samples of how to use `Search` component in Kotlin.

```kotlin
Search(
    descriptionClear = "Clear",
    descriptionCollapsed = "Collapsed",
    descriptionExpanded = "Expanded",
    onFocusChange = {},
    onIconClicked = {},
)
```

<picture>
    <source
        media="(prefers-color-scheme: dark)"
        srcset="https://github.com/user-attachments/assets/1d4ef829-fb5b-410d-a018-b5855f2551f0"
    />
    <source
        media="(prefers-color-scheme: light), (prefers-color-scheme: no-preference)"
        srcset="https://github.com/user-attachments/assets/9edab995-fcdc-40bd-a75e-bcc51afc1b24"
    />
    <img
        align="center"
        alt="Search Sample with Required Parameters"
        src="https://github.com/user-attachments/assets/1d4ef829-fb5b-410d-a018-b5855f2551f0"
        style="width:100%;"
    />
</picture>
<br>
<br>
<br>
<br>

```kotlin
Search(
    colorCollapsed = Color.backgroundTranslucent,
    colorExpanded = Color.backgroundTransparent,
    descriptionClear = stringResource(R.string.action_clear),
    descriptionCollapsed = stringResource(
        if (isTablet) {
            R.string.action_menu
        } else {
            R.string.action_search
        }
    ),
    descriptionExpanded = stringResource(R.string.action_back),
    isExpanded = isExpanded,
    isMenu = isTablet,
    onFocusChange = { hasFocus ->
        if (hasFocus) {
            isExpanded = true
        } else {
            isExpanded = false
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
            R.string.placeholder_expanded
        } else {
            R.string.placeholder_collapsed
        }
    ),
    query = query,
)
```

<picture>
    <source
        media="(prefers-color-scheme: dark)"
        srcset="https://github.com/user-attachments/assets/f440efe1-1baa-4bb8-8d4c-07fcfbee0c73"
    />
    <source
        media="(prefers-color-scheme: light), (prefers-color-scheme: no-preference)"
        srcset="https://github.com/user-attachments/assets/d808d8ab-34d2-4b54-abb8-a98a2851070a"
    />
    <img
        align="center"
        alt="Search Sample with Optional Parameters"
        src="https://github.com/user-attachments/assets/f440efe1-1baa-4bb8-8d4c-07fcfbee0c73"
        style="width:100%;"
    />
</picture>
<br>

## Storybook

To showcase the Search component, the Storybook app is released alongside each library version.  In other words, Search library version 1.1.1 corresponds to Storybook app version 1.1.0, which uses Search library version 1.1.0.  See the animation below of the Storybook app using the Search component.

<picture>
    <source
        media="(prefers-color-scheme: dark)"
        srcset="https://github.com/user-attachments/assets/74f94184-a8bb-4f35-8949-dd181e5bc22f"
    />
    <source
        media="(prefers-color-scheme: light), (prefers-color-scheme: no-preference)"
        srcset="https://github.com/user-attachments/assets/94e7b2ba-15a6-42a6-bf5d-eaab92bec5c5"
    />
    <img
        align="center"
        alt="Storybook App with Search Component Filtering Video Game List"
        src="https://github.com/user-attachments/assets/74f94184-a8bb-4f35-8949-dd181e5bc22f"
        style="width:33%;"
    />
</picture>
<br>
