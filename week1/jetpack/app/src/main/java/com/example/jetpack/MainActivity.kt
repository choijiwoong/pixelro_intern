package com.example.jetpack

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by remember{ mutableStateOf(false) }
    val extraPadding by animateDpAsState(//에니메이션 맞춤 설정
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface( color=MaterialTheme.colorScheme.primary
        , modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    {
        Row(modifier=Modifier.padding(24.dp)){
            Column(modifier= Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))){
                Text(text="Hello ")
                Text(text=name, style=MaterialTheme.typography.headlineMedium.copy(
                    fontWeight= FontWeight.ExtraBold
                ))
            }
            ElevatedButton( onClick={ expanded= ! expanded } ){
                Text(if ( expanded ) "Show less" else "Show more")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp=320,
    uiMode= Configuration.UI_MODE_NIGHT_YES,
    name="GreetingPreviewDart"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    JetpackTheme {
        Greeting("Android")
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnBoarding by remember { mutableStateOf(true) }
    var shouldShowOnBoarding2 by rememberSaveable{ mutableStateOf(true) }

    Surface(modifier){
        if (shouldShowOnBoarding){
            OnboardingScreen( onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
private fun Greetings( modifier: Modifier=Modifier, names: List<String> = listOf("World", "Compose")){
    Column(modifier=modifier.padding(vertical=4.dp)){
        for (name in names){
            Greeting("hi")
        }
    }
}

@Preview(showBackground = true, widthDp=320)
@Composable
fun GreetingsPreview(){
    JetpackTheme {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked:()->Unit, modifier: Modifier = Modifier){
    Column (
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text("Welcome to the Basices Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground=true, widthDp=320, heightDp=320)
@Composable
fun OnboadingPreview(){
    JetpackTheme{
        OnboardingScreen(onContinueClicked = {})
    }
}