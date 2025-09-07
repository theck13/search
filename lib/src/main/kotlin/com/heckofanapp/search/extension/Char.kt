package com.heckofanapp.search.extension

/**
 * Is [Char] newline character.
 *
 * @return [Boolean] true if [Char] is newline, false otherwise
 */
fun Char.isNewline(): Boolean {
    return this == '\n'
}
