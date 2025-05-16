package com.yeabsira.todolist.ui.theme


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yeabsira.todolist.viewmodel.TodoDetailViewModel
import com.yeabsira.todolist.viewmodel.TodoListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    listViewModel: TodoListViewModel,
    detailViewModel: TodoDetailViewModel
) {
    NavHost(navController = navController, startDestination = "todo_list") {
        composable("todo_list") {
            TodoListScreen(
                viewModel = listViewModel,
                onTodoClick = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }
        composable("todo_detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            TodoDetailScreen(viewModel = detailViewModel, todoId = todoId, onBack = {
                navController.popBackStack()
            })
        }
    }
}
