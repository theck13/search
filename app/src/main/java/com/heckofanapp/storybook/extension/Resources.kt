package com.heckofanapp.storybook.extension

import android.content.res.Resources
import androidx.annotation.IntegerRes

/**
 * Get integer resource value.
 *
 * @param id required [Int] resource identifier
 *
 * @return [Int] number associated with resource
 */
fun Resources.integerResource(
    @IntegerRes id: Int,
): Int {
    return getInteger(id)
}
