package com.tech.design_system.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tech.design_system.tokens.radius
import com.tech.design_system.tokens.spacing

@Composable
fun RMPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.radius.medium),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.lg,
            vertical = MaterialTheme.spacing.sm
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
