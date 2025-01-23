package eu.tutorials.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.tutorials.musicappui.MainViewModel
import eu.tutorials.musicappui.Screen
import eu.tutorials.musicappui.screensInBottom
import eu.tutorials.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val viewModel: MainViewModel = viewModel()
    // allows to determine which Screen we are on currently
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isDialogOpen = remember {
        mutableStateOf(false)
    }
    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember{
        mutableStateOf(currentScreen.title)
    }

    val bottomBar: @Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(Modifier.wrapContentSize()) {
                screensInBottom.forEach{
                    item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = {
                                  controller.navigate(item.bRoute)
                        },
                        icon = {
                            Icon(contentDescription = item.bTitle,
                                painter = painterResource(id = item.icon))
                        },
                        label = { Text(text = item.bTitle)},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black

                        )

                }
            }
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(title = { Text(title.value) },
            navigationIcon = {
                IconButton(onClick = {
                    // open the drawer
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu" )
                }
            })
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp) ) {
                items(screensInDrawer) {
                    item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if(item.dRoute == "add_account") {
                            //open dialog
                            isDialogOpen.value = true
                        } else {
                            controller.navigate(item.dRoute)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }
    ) {
       Navigation(navController = controller, viewModel = viewModel, pd = it)
       AccountDialog(isDialogOpen = isDialogOpen)
    }
}


@Composable
fun DrawerItem(
    selected:Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if(selected) Color.LightGray else Color.White

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .background(background, shape = RoundedCornerShape(25.dp))
            .clickable {
                onDrawerItemClicked()
            }

    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp)

        )

        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController ,
        startDestination = Screen.DrawerScreen.Account.route ,
        modifier = Modifier.padding(pd)) {

            composable(Screen.BottomScreen.Home.bRoute) {
                HomeScreen()
            }
            composable(Screen.BottomScreen.Browse.bRoute) {
                BrowseScreen()
            }
            composable(Screen.BottomScreen.Library.bRoute) {
                LibraryScreen()
            }

            composable(Screen.DrawerScreen.AddAccount.route) {}

            composable(Screen.DrawerScreen.Subscription.route) {
                SubscriptionView()
            }

            composable(Screen.DrawerScreen.Account.route) {
                AccountView()
            }
    }
}