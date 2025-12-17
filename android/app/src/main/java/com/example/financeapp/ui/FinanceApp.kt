import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.analytics.AnalyticsScreen
import com.example.financeapp.ui.analytics.AnalyticsViewModel
import com.example.financeapp.ui.analytics.preview.FakeAnalyticsViewModel
import com.example.financeapp.ui.navigation.BottomNavItem
import com.example.financeapp.ui.navigation.bottomNavItems
import com.example.financeapp.ui.transaction.TransactionForm
import com.example.financeapp.ui.transaction.TransactionList
import com.example.financeapp.ui.transaction.TransactionViewModel
import com.example.financeapp.ui.transaction.preview.FakeTransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceApp(
    transactionViewModel: TransactionViewModel,
    analyticsViewModel: AnalyticsViewModel
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dave's money") })
        },
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = item.label)
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Form.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(
                BottomNavItem.Form.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                TransactionForm(
                    viewModel = transactionViewModel,
                    snackbarHostState = snackbarHostState
                )
            }
            composable(BottomNavItem.List.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                TransactionList(viewModel = transactionViewModel)
            }

            composable(BottomNavItem.Analytics.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                AnalyticsScreen(viewModel = analyticsViewModel)
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewFinanceApp() {
    FinanceApp(
        transactionViewModel = FakeTransactionViewModel(),
        analyticsViewModel = FakeAnalyticsViewModel()
    )
}