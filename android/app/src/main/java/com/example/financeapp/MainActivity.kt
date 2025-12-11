package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.data.local.AppDatabase
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.navigation.BottomNavItem
import com.example.financeapp.ui.navigation.bottomNavItems
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.transaction.TransactionForm
import com.example.financeapp.ui.transaction.TransactionList
import com.example.financeapp.ui.transaction.TransactionViewModel
import com.example.financeapp.ui.transaction.TransactionViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val repo = TransactionRepository(db.transactionDao())
        val factory = TransactionViewModelFactory(repo)

        viewModel = ViewModelProvider(this, factory)[TransactionViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() } // <-- here, inside Composable
            FinanceAppTheme {

                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Dave's money") })
                    },
                    bottomBar = {
                        NavigationBar {
                            val currentRoute = navController.currentBackStackEntry
                                ?.destination?.route

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
                                        item.icon
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
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomNavItem.Form.route) {
                            TransactionForm(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel,
                                snackbarHostState = snackbarHostState
                            )
                        }
                        composable(BottomNavItem.List.route) {
                            TransactionList(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel,
                            )
                        }
                    }
                }
            }
        }
    }
}