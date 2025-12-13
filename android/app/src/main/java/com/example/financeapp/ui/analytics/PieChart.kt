package com.example.financeapp.ui.analytics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PieChartData(
    val category: String,
    val amount: Float,
    val color: Long
)

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<PieChartData> = emptyList(),
    strokeWidth: Dp = 8.dp
) {
    val total = data.sumOf { it.amount.toDouble() }.toFloat()

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val stroke = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
            var startAngle = -90f
            val gapAngle = 8f
            data.forEach { slice ->
                val sliceAngle = (slice.amount / total) * 360f
                val sweepAngle = sliceAngle - gapAngle
                if (sweepAngle > 0f) {
                    drawArc(
                        color = Color(slice.color),
                        startAngle = startAngle + gapAngle / 2f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = stroke
                    )
                }
                startAngle += sliceAngle
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "£${String.format("%.2f", total)}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun PieChartPreview() {
    val sampleData = listOf(
        PieChartData("Food", 150f, 0xFFE57373),
        PieChartData("Transport", 80f, 0xFF64B5F6),
        PieChartData("Entertainment", 120f, 0xFFFFB74D),
        PieChartData("Utilities", 100f, 0xFF81C784)
    )
    PieChart(data = sampleData)
}