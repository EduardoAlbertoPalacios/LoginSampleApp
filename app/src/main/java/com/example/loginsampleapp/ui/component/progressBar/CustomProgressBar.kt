package com.example.loginsampleapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.loginsampleapp.R
import com.example.loginsampleapp.ui.component.progressBar.ProgressBarLogoType
import com.example.loginsampleapp.ui.theme.grayTransparency

@Composable
fun CustomProgressBar(typeComposition: ProgressBarLogoType) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (typeComposition == ProgressBarLogoType.SPIRAL)
                R.raw.loader_animation
            else -1
        )
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { Unit }
            .background(grayTransparency)
            .testTag(stringResource(id = R.string.baubap_loader)),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress
        )
    }
}
