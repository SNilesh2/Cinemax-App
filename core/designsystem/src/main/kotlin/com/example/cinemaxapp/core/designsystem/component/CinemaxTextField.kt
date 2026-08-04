package com.example.cinemaxapp.core.designsystem.component

import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CinemaxTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            label = label?.let {
                {
                    Text(
                        text = it,
                        style = CinemaxTheme.typography.h6Medium,
                        color = CinemaxTheme.colors.whiteGrey
                    )
                }
            },
            placeholder = placeholder?.let {
                {
                    Text(
                        text = it,
                        style = CinemaxTheme.typography.h5Medium,
                        color = CinemaxTheme.colors.grey,
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(24.dp),
//            textStyle = CinemaxTheme.typography.h5Regular.copy(
//                color = CinemaxTheme.colors.white
//            ),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = CinemaxTheme.colors.soft,
//                unfocusedContainerColor = CinemaxTheme.colors.soft,
//                disabledContainerColor = CinemaxTheme.colors.soft,
//                errorContainerColor = CinemaxTheme.colors.soft,
//                focusedBorderColor = CinemaxTheme.colors.blueAccent,
//                unfocusedBorderColor = CinemaxTheme.colors.soft,
//                disabledBorderColor = CinemaxTheme.colors.soft,
//                errorBorderColor = CinemaxTheme.colors.red,
//                focusedLabelColor = CinemaxTheme.colors.blueAccent,
//                unfocusedLabelColor = CinemaxTheme.colors.grey,
//                disabledLabelColor = CinemaxTheme.colors.darkGrey,
//                errorLabelColor = CinemaxTheme.colors.red,
//                focusedLeadingIconColor = CinemaxTheme.colors.blueAccent,
//                unfocusedLeadingIconColor = CinemaxTheme.colors.grey,
//                focusedTrailingIconColor = CinemaxTheme.colors.blueAccent,
//                unfocusedTrailingIconColor = CinemaxTheme.colors.grey,
//            ),
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                style = CinemaxTheme.typography.h7Regular,
                color = CinemaxTheme.colors.red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            )
        }
    }
}






