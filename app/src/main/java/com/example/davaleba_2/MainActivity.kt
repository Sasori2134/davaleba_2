package com.example.davaleba_2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentForm()
        }
    }
}

val backgroundColor = Color(0xFFF6F7F2)
val cardColor = Color(0xFFFFFFFF)
val accentColor = Color(0xFF6B8F71)
val accentLight = Color(0xFFA3C4A8)
val textPrimary = Color(0xFF1F2A22)
val textSecondary = Color(0xFF5F6F64)
val fieldBackground = Color(0xFFE9EFE6)

@Composable
fun StudentForm() {

    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    val directions = listOf("Android", "iOS", "Web")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = "Student Form",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = accentLight,
                letterSpacing = 2.sp
            )
            Text(
                text = "Fill in all fields to continue",
                fontSize = 13.sp,
                color = textSecondary,
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(cardColor)
                    .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                FormTextField(
                    value = nameState,
                    onValueChange = { newValue -> nameState = newValue },
                    label = "First Name"
                )

                FormTextField(
                    value = surnameState,
                    onValueChange = { newValue -> surnameState = newValue },
                    label = "Last Name"
                )

                FormTextField(
                    value = emailState,
                    onValueChange = { newValue -> emailState = newValue },
                    label = "Email"
                )

                SectionLabel("Date of Birth")
                DatePickerField(
                    dateState = dateState,
                    onDateSelected = { newDate -> dateState = newDate }
                )

                SectionLabel("Favorite Direction")
                directions.forEach { direction ->
                    RadioOption(
                        text = direction,
                        selected = selectedOption == direction,
                        onClick = { selectedOption = direction }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(fieldBackground)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ვეთანხმები წესებს და პირობებს",
                        color = textPrimary,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isAgreed,
                        onCheckedChange = { newValue -> isAgreed = newValue },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = accentColor,
                            uncheckedThumbColor = textSecondary,
                            uncheckedTrackColor = fieldBackground
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = {
                        when {
                            nameState.isBlank() || surnameState.isBlank() ||
                                    emailState.isBlank() || dateState.isBlank() ||
                                    selectedOption.isBlank() -> {
                                Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                            }
                            isAgreed == false -> {
                                Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor
                    )
                ) {
                    Text(
                        text = "SUBMIT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp,
                        color = Color.White
                    )
                }

            }

            Spacer(modifier = Modifier.height(32.dp))

        }

    }
}

@Composable
fun FormTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = textSecondary) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = accentColor,
            unfocusedBorderColor = accentColor.copy(alpha = 0.3f),
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
            cursorColor = accentLight,
            focusedContainerColor = fieldBackground,
            unfocusedContainerColor = fieldBackground,
            focusedLabelColor = accentLight
        )
    )
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = accentLight,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
    )
}

@Composable
fun RadioOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) accentColor.copy(alpha = 0.15f) else fieldBackground)
            .border(
                width = 1.dp,
                color = if (selected) accentColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = accentColor,
                unselectedColor = textSecondary
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = if (selected) accentLight else textPrimary,
            fontSize = 15.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun DatePickerField(dateState: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formatted = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
            onDateSelected(formatted)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(fieldBackground)
            .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
            .clickable { datePickerDialog.show() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (dateState.isEmpty()) "DD/MM/YYYY" else dateState,
            color = if (dateState.isEmpty()) textSecondary else textPrimary,
            fontSize = 15.sp
        )
        Icon(
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = "Pick date",
            tint = accentLight
        )
    }
}