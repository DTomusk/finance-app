package com.example.financeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem (
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Form : BottomNavItem(
        route = "transaction_form",
        label = "Add transaction",
        icon = Icons.Default.Add
    )

    object List : BottomNavItem(
        route = "transaction_list",
        label = "History",
        icon = Icons.AutoMirrored.Filled.List
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Form,
    BottomNavItem.List
)