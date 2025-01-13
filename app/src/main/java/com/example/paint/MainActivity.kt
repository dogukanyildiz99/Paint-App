package com.example.paint

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.paint.ui.theme.PaintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaintTheme {

            }
        }
    }
}

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit){
    val context = LocalContext.current.applicationContext
    val colorMap = mapOf(
        Color.Red to "Red",
        Color.Green to "Green",
        Color.Blue to "Blue",
        Color.Black to "Black")
    Row {
        colorMap.forEach{ (color, name) ->
            Box(
                Modifier
                    .size(40.dp)
                    .background(color, CircleShape)
                    .padding(4.dp)
                    .clickable {
                        onColorSelected(color)
                        Toast
                            .makeText(context, name, Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
    }
}

@Composable
fun BrushSizeSelector(currentSize: Float, onSizeSelected: (Float) -> Unit,
                      isEraser: Boolean, keepMode: (Boolean) -> Unit) {
    var sizeText by remember { mutableStateOf(currentSize.toString()) }
    Row {
        BasicTextField(
            value = sizeText,
            onValueChange = {
                sizeText = it
                val newSize = it.toFloatOrNull() ?: currentSize
                onSizeSelected(newSize)
                keepMode(isEraser)
            },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .width(60.dp)
                .background(Color.LightGray, CircleShape)
                .padding(8.dp)
        )
        Text("px", Modifier.align(Alignment.CenterVertically))
    }
}