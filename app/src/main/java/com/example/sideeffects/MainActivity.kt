package com.example.sideeffects

import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sideeffects.ui.theme.SideEffectsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutineScopeComposable()
        }
    }
}

@Composable
fun LaunchEffectComposable(){
    val counter= remember { mutableStateOf(0) }

    LaunchedEffect(key1 = Unit) {
        Log.d("LaunchedEffectComposable","Started....")
        try{
            for(i in 1..10){
                counter.value++

                delay(3000)
            }
        }
        catch (e:Exception){
            Log.d("LaunchedEffectComposable",e.message.toString())
        }
    }
    var text="Counter is running ${counter.value}"
    if(counter.value==4){
        text="Counter stopped"
    }
    Text(text = text, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp))
}

@Preview(showSystemUi = true)
@Composable
fun CoroutineScopeComposable(){
    val counter= remember { mutableStateOf(0) }
    val scope= rememberCoroutineScope()


    var text="Counter is running ${counter.value}"
    if(counter.value==4){
        text="Counter stopped"
    }

    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = text)
        Button(onClick = {
            scope.launch {
                try {
                    for (i in 1 .. 10){
                        counter.value++
                        delay(1500)
                    }
                }
                catch (e:Exception){

                }
            }
        }) {
            Text(text = "Start")
        }
    }
}
