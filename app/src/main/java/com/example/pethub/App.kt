package com.example.pethub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pethub.data.di.Injection
import com.example.pethub.ui.common.MenuItem
import com.example.pethub.ui.common.ViewModelFactory
import com.example.pethub.ui.navigation.Screen
import com.example.pethub.ui.screen.about.AboutScreen
import com.example.pethub.ui.screen.detail.DetailScreen
import com.example.pethub.ui.screen.home.HomeScreen
import com.example.pethub.ui.screen.list.ListScreen
import com.example.pethub.ui.screen.login.LoginScreen
import com.example.pethub.ui.screen.welcome.WelcomeScreen
import com.example.pethub.ui.utils.showTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    isDarkMode: Boolean,
    onToggleTheme : () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItem = listOf(
        MenuItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        MenuItem(
            title = "My Adopt",
            icon = Icons.Default.ThumbUp,
            route = Screen.List.route
        ),
        MenuItem(
            title = "About",
            icon = Icons.Default.Info,
            route = Screen.About.route
        ),
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            if (currentRoute.showTopBar()) {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    title = {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(stringResource(R.string.app_name))
                            Row {
                                IconButton(
                                    onClick = onToggleTheme
                                ) {
                                    val iconResId = if (isDarkMode) {
                                        R.drawable.baseline_light_mode_24
                                    } else {
                                        R.drawable.baseline_nightlight_24
                                    }
                                    val image = painterResource(id = iconResId)
                                    Icon(
                                        painter = image,
                                        contentDescription = "Dark Mode Toggle",
                                        tint = Color.White
                                    )
                                }
                                IconButton(onClick = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(navController.graph.id) {
                                            inclusive = true
                                        }
                                    }
                                    viewModel.logout()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ExitToApp,
                                        contentDescription = "Log Out",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    },
                )
            }
        },
    ) { innerPadding ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(innerPadding),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    menuItem.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.title) },
                            selected = currentRoute == item.route,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            modifier = Modifier.padding(horizontal = 12.dp),
                        )
                    }
                }
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Welcome.route,
                ) {
                    composable(Screen.Welcome.route) {
                        WelcomeScreen(
                            onNavigateToLoginScreen = {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable(Screen.Login.route) {
                        LoginScreen (
                            onNavigateToHomeScreen = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onNavigateToDetailScreen = { id ->
                                navController.navigate(Screen.Detail.createRoute(id))
                            },
                        )
                    }
                    composable(Screen.List.route) {
                        ListScreen(
                            onNavigateToDetailScreen = { id ->
                                navController.navigate(Screen.Detail.createRoute(id))
                            },
                        )
                    }
                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("id") { type = NavType.StringType })
                    ) {
                        val id = it.arguments?.getString("id") ?: ""
                        DetailScreen(id)
                    }
                    composable(Screen.About.route) {
                        AboutScreen()
                    }
                }
            }
        )
    }
}