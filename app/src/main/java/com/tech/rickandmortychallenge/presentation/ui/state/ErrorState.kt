package com.tech.rickandmortychallenge.presentation.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tech.design_system.components.button.RMPrimaryButton
import com.tech.design_system.components.text.RMCaptionText
import com.tech.design_system.components.text.RMTitleText
import com.tech.design_system.tokens.spacing
import com.tech.rickandmortychallenge.R

@Composable
fun RMErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        RMTitleText(
            text = stringResource(R.string.something_went_wrong),
            maxLines = 1
        )

        RMCaptionText(
            text = message,
            modifier = Modifier.padding(top = MaterialTheme.spacing.sm)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))

        RMPrimaryButton(
            text = "Retry",
            onClick = onRetry
        )
    }
}