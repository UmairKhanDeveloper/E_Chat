import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {
    val context = LocalContext.current
    val prefManager = remember { PrefManager(context) }

    val titleFont = FontFamily(Font(R.font.robot_black))

    var startAnim by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        startAnim = true

        delay(1500)

        if (prefManager.isFirstTime()) {
            navController.navigate(Screens.OnBoarding.route) {
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
        }
    }

    val logoScale by animateFloatAsState(
        targetValue = if (startAnim) 1f else 0.6f,
        animationSpec = tween(900, easing = FastOutSlowInEasing),
        label = ""
    )

    val logoAlpha by animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(900),
        label = ""
    )

    val logoOffsetY by animateDpAsState(
        targetValue = if (startAnim) 0.dp else (-30).dp,
        animationSpec = tween(900, easing = FastOutSlowInEasing),
        label = ""
    )

    val centerAlpha by animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(1000),
        label = ""
    )

    val centerOffsetY by animateDpAsState(
        targetValue = if (startAnim) 0.dp else 40.dp,
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = ""
    )

    val versionAlpha by animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(1200),
        label = ""
    )

    val versionOffsetY by animateDpAsState(
        targetValue = if (startAnim) 0.dp else 20.dp,
        animationSpec = tween(1200),
        label = ""
    )

    val version = try {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        pInfo.versionName
    } catch (e: Exception) {
        "N/A"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_e_chat),
            contentDescription = "App Logo",
            modifier = Modifier
                .padding(top = 60.dp)
                .scale(logoScale)
                .alpha(logoAlpha)
                .offset(y = logoOffsetY)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .alpha(centerAlpha)
                .offset(y = centerOffsetY)
        ) {

            Image(
                painter = painterResource(id = R.drawable.chat_round),
                contentDescription = "Chat Background"
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Stay Connected",
                    fontFamily = titleFont,
                    color = colorResource(id = R.color.blue),
                    fontSize = 22.sp
                )
                Text(
                    text = "Stay Chatting",
                    fontFamily = titleFont,
                    color = colorResource(id = R.color.blue),
                    fontSize = 22.sp
                )
            }
        }

        Text(
            text = "Version $version",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = colorResource(id = R.color.blue),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .alpha(versionAlpha)
                .offset(y = versionOffsetY)
        )
    }
}


class PrefManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun isFirstTime(): Boolean {
        return prefs.getBoolean("is_first_time", true)
    }

    fun setFirstTime(value: Boolean) {
        prefs.edit().putBoolean("is_first_time", value).apply()
    }
}