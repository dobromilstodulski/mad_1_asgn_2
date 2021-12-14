package org.dobromilstodulski.venues.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import org.dobromilstodulski.venues.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.button_venue_image.toString())
    intentLauncher.launch(chooseFile)
}