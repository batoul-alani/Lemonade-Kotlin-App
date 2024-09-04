package com.example.lemonadekotlinapp

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadekotlinapp.ui.theme.LemonadeKotlinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeKotlinAppTheme {
                LemonadeKotlinApp(context = this)
            }
        }
    }
}

@Composable
fun LemonadeKotlinApp(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center), context : Activity = Activity()
) {
    var result by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val textResource = when (result) {
        1 -> R.string.lemon_tree_tip
        2 -> R.string.lemon_tip
        3 -> R.string.glass_of_lemonade_tip
        4 -> R.string.empty_glass_tip
        else -> R.string.lemon_tree_tip
    }

    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = {
            if(result == 1){
                result = 2;
                squeezeCount = (2..4).random()
            }
            else if (result == 2){
                squeezeCount--
                if (squeezeCount == 0) {
                    result = 3
                }
            }
            else if (result == 3){
                result = 4;
            }
            else if (result == 4){
                result = 1;
            }


        }, shape = RoundedCornerShape(16),    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
            Image(painter = painterResource(id = imageResource), contentDescription = textResource.toString())
        }
        Spacer(modifier = Modifier.height(32
            .dp))
        Text(text = context.getString(textResource),  fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeKotlinAppTheme() {
    LemonadeKotlinApp ()
}