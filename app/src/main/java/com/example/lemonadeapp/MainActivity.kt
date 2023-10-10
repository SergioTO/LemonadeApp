package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade("Android")
                }
            }
        }
    }
}

@Composable
fun Lemonade(name: String, modifier: Modifier = Modifier) {
    val randomNumberNeededTap = (2..4).random()
    var currentNumberTapInSecondImage by remember { mutableStateOf( 0) }
    var currentImageText by remember { mutableStateOf( 0) }

    val image = ArrayList<Int>()
    val texto = ArrayList<String>()



    image.add(R.drawable.lemon_tree)
    image.add(R.drawable.lemon_squeeze)
    image.add(R.drawable.lemon_drink)
    image.add(R.drawable.lemon_restart)
    texto.add(stringResource(R.string.lemon_tree))
    texto.add(stringResource(R.string.lemon_squeeze))
    texto.add(stringResource(R.string.lemon_drink))
    texto.add(stringResource(R.string.lemon_restart))

    var currentText = texto[0]
    var currentImage = image[0]

     when(currentImageText) {
         else -> {
             if(currentImageText==1){
                 currentText = texto[1] + " ($currentNumberTapInSecondImage squeeze of $randomNumberNeededTap)"
             }
             else{          currentText=texto[currentImageText]}

             currentImage=image[currentImageText]
         }
    }

    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    if(currentImageText==0){
                        currentImageText=1
                        currentText += " (0 squeeze)"
                    }
                    else if (currentImageText==1){
                        currentNumberTapInSecondImage += 1
                        if(randomNumberNeededTap<=currentNumberTapInSecondImage)
                        {
                            currentImageText=2
                            currentNumberTapInSecondImage=0
                        }
                    }
                    else if (currentImageText==2){
                        currentImageText=3
                    }
                    else if (currentImageText==3){
                        currentImageText=0
                    }
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(currentImage),
                    contentDescription = null,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = currentText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeAppTheme {
        Lemonade("Android")
    }
}