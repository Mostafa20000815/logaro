package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.RoleCustomImageStore
import com.example.data.model.Role

/**
 * مكون عرض الصورة الحقيقية للبطاقة كما تم رفعها أو اختيارها
 */
@Composable
fun RoleCardPhotoView(
    role: Role,
    modifier: Modifier = Modifier,
    enableImagePicker: Boolean = true,
    showMemeBadge: Boolean = true
) {
    val context = LocalContext.current
    val customImages by RoleCustomImageStore.customImages.collectAsState()
    val customUriString = customImages[role.id]
    
    var showFullPhotoDialog by remember { mutableStateOf(false) }

    // لاقط الصور من معرض الجهاز
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            RoleCustomImageStore.setImageUri(role.id, it)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("role_photo_view_${role.id}"),
        contentAlignment = Alignment.Center
    ) {
        if (!customUriString.isNullOrBlank()) {
            // عرض الصورة المخصصة المختارة من قبل المستخدم بجودة كاملة
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(customUriString)
                    .crossfade(true)
                    .build(),
                contentDescription = role.nameArabic,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showFullPhotoDialog = true }
            )
        } else {
            // عرض العمل الفني والميم الحقيقي المصمم للشخصية
            RoleVectorArtwork(
                role = role,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showFullPhotoDialog = true }
            )
        }

        // تدرج ظلي سينمائي سفلي وعلوي
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.35f),
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.55f)
                        )
                    )
                )
        )

        // زر تغيير / إرفاق الصورة الحقيقية من المعرض في الزاوية
        if (enableImagePicker) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.65f))
                    .border(1.dp, Color(0xFFFDE68A), CircleShape)
                    .clickable {
                        imagePickerLauncher.launch("image/*")
                    }
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = if (customUriString != null) Icons.Default.Edit else Icons.Default.AddPhotoAlternate,
                    contentDescription = "تغيير الصورة",
                    tint = Color(0xFFFDE68A),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    // نافذة استعراض الصورة وتعديلها بحجم كامل
    if (showFullPhotoDialog) {
        Dialog(onDismissRequest = { showFullPhotoDialog = false }) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF131722),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(2.dp, Color(0xFFD97706), RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "صورة بطاقة: ${role.nameArabic}",
                            color = Color(0xFFFDE68A),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        IconButton(onClick = { showFullPhotoDialog = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "إغلاق",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF0A0D14))
                            .border(1.5.dp, Color(0xFF2A323F), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!customUriString.isNullOrBlank()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(customUriString)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = role.nameArabic,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            RoleVectorArtwork(
                                role = role,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                showFullPhotoDialog = false
                                imagePickerLauncher.launch("image/*")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD97706)
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddPhotoAlternate,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (customUriString != null) "تغيير الصورة" else "اختر صورة من جهازك",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (customUriString != null) {
                            OutlinedButton(
                                onClick = {
                                    RoleCustomImageStore.resetImage(role.id)
                                },
                                modifier = Modifier.weight(0.7f)
                            ) {
                                Text(
                                    text = "استعادة الأصلية",
                                    color = Color(0xFFFCA5A5),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
