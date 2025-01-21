package eu.tutorials.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import eu.tutorials.musicappui.R

@Composable
fun SubscriptionView() {



    Card(
        modifier = Modifier
            .fillMaxWidth() // Makes the card fill the available width
            .height(200.dp)
            .padding(16.dp), // Adds outer spacing around the card
        elevation = CardDefaults.cardElevation(8.dp), // Adds shadow for elevation
        shape = RoundedCornerShape(10.dp), // Optional: Rounded corners
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEEEF4) // Optional: Background color
        )
    ) {
        Column {
            Text(
                text = "Manage Subscription", style = MaterialTheme.typography.body1.copy(
                    color = Color(0xFF7E57C2) // Purple text color
                ),
                modifier = Modifier.padding(10.dp).padding(horizontal = 8.dp)
            )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Column {
                        Text(text = "Musical")
                        Text(text = "Free Tier")
                    }

                }
                IconButton(onClick = { /*TODO*/ }) {
                    Row {
                        Text(
                            "See All Plans",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color(0xFF7E57C2) // Purple text color
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
            Divider()

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account),
                    contentDescription = "Get a plan",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Get a Plan", modifier = Modifier.padding(top = 4.dp))
            }
            Divider()
        }
    }
}
}
