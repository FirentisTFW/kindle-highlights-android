package com.firentistfw.kindlehighlights.ui.addcategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.ui.common.KHAppBar
import com.firentistfw.kindlehighlights.ui.common.KHButton

@Composable
fun AddCategoryScreen(
    buttonEnabled: Boolean,
    onButtonClick: (categoryName: String) -> Unit,
) {
    var textFieldState by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            KHAppBar(title = stringResource(id = R.string.addCategory_appBarTitle))
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
        ) {
            TextField(
                value = textFieldState,
                label = {
                    Text(stringResource(id = R.string.addCategory_categoryNameHint))
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            KHButton(
                onClick = { onButtonClick(textFieldState) },
                text = stringResource(id = R.string.addCategory_addCategoryButton),
                enabled = buttonEnabled,
            )
        }
    }
}
