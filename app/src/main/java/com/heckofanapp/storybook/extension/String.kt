package com.heckofanapp.storybook.extension

import java.util.Locale

/**
 * Convert [String] from Pascal to Title case.
 */
fun String.fromPascalToTitleCase() = mapIndexed { index, char ->
    if (char.isUpperCase() && index != 0) {
        " $char"
    } else {
        char
    }
}.joinToString(
    separator = "",
)

/**
 * Match [String] with query parameter ignoring case.
 *
 * @param query required [String] to match receiver [String] ignoring case
 *
 * @return [Boolean] true if receiver [String] matches query [String], false otherwise
 */
fun String.matchStringWithQueryIgnoringCase(
    query: String,
): Boolean {
    return (
        // Match string.
        fromPascalToTitleCase().contains(
            ignoreCase = true,
            other = query,
        ) ||
        // Match string without spaces.
        contains(
            ignoreCase = true,
            other = query,
        ) ||
        // Match parts of string.
        fromPascalToTitleCase()
            .lowercase(
                locale = Locale.getDefault(),
            )
            .split(" ").containsAll(
                elements = query.split(
                    " ", ".", "_", "-", "+", "*", "/", "\\",
                    ignoreCase = true,
                ).map { char ->
                    char.lowercase(
                        locale = Locale.getDefault(),
                    )
                }
            )
    )
}
