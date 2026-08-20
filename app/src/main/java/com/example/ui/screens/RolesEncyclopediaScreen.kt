package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.Role
import com.example.data.model.RoleCategory
import com.example.data.model.RolesRegistry
import com.example.ui.components.FlipRoleCard
import com.example.ui.components.MasterStoneRoleCard
import com.example.ui.components.NeoBadge
import com.example.ui.components.NeoCard
import com.example.ui.components.WerewolfBackground
import com.example.ui.theme.InkBlack
import com.example.ui.theme.InkCharcoal
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkWhite
import com.example.ui.theme.PaperAged
import com.example.ui.theme.PaperBorder
import com.example.ui.theme.PaperCard
import com.example.ui.theme.PaperDark
import com.example.ui.theme.PaperOchre
import com.example.ui.theme.PaperPrussianBlue
import com.example.ui.theme.PaperRustRed
import com.example.ui.theme.PaperSageGreen
import com.example.ui.theme.PaperSand
import com.example.ui.theme.PaperVintagePurple

import com.example.data.RoleCustomImageStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxHeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.collectAsState

@Composable
fun RolesEncyclopediaScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<RoleCategory?>(null) }
    var isGridView by remember { mutableStateOf(true) }
    var inspectingRole by remember { mutableStateOf<Role?>(null) }
    var isInspectingCardFlipped by remember { mutableStateOf(true) }
    var showPhotoManagerDialog by remember { mutableStateOf(false) }

    val allRoles = RolesRegistry.ALL_ROLES_INCLUDING_LEADERSHIP
    val customImages by RoleCustomImageStore.customImages.collectAsState()

    val filteredRoles = allRoles.filter { role ->
        val matchesCategory = selectedCategory == null || role.category == selectedCategory
        val matchesQuery = searchQuery.isBlank() ||
                role.nameArabic.contains(searchQuery, ignoreCase = true) ||
                role.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                role.description.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesQuery
    }

    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // شريط العنوان العلوي
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(PaperCard, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                        .clickable { onBack() }
                        .testTag("back_from_encyclopedia_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "رجوع",
                        tint = InkBlack
                    )
                }

                NeoBadge(
                    text = "موسوعة البطاقات (${allRoles.size} بطاقة)",
                    backgroundColor = PaperSand,
                    textColor = InkBlack,
                    shadowOffset = 2.dp
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    // زر فتح مدير الصور المخصصة للبطاقات
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(PaperRustRed, shape = RoundedCornerShape(8.dp))
                            .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                            .clickable { showPhotoManagerDialog = true }
                            .testTag("open_photo_manager_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoLibrary,
                            contentDescription = "تعيين صور البطاقات",
                            tint = InkWhite
                        )
                    }

                    // زر التبديل بين وضع المعرض الحجري والقائمة
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(if (isGridView) PaperOchre else PaperCard, shape = RoundedCornerShape(8.dp))
                            .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                            .clickable { isGridView = !isGridView }
                            .testTag("toggle_view_mode_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isGridView) Icons.Default.List else Icons.Default.GridView,
                            contentDescription = "تبديل طريقة العرض",
                            tint = InkBlack
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // حقل البحث
            NeoCard(
                backgroundColor = PaperCard,
                shadowOffset = 3.dp,
                borderWidth = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_roles_input"),
                    placeholder = { Text("ابحث عن بطاقة، دور، أو قدرة بالعربية...", color = InkMuted, fontSize = 13.sp, fontWeight = FontWeight.Medium) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = InkBlack)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(PaperDark, CircleShape)
                                    .border(1.dp, PaperBorder, CircleShape)
                                    .clickable { searchQuery = "" },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "مسح", tint = InkBlack, modifier = Modifier.size(14.dp))
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = InkBlack,
                        unfocusedTextColor = InkBlack,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // فلاتر الفئات
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    PaperCategoryFilterChip(
                        text = "الكل (${allRoles.size})",
                        isSelected = selectedCategory == null,
                        color = PaperAged,
                        onClick = { selectedCategory = null }
                    )
                }
                items(RoleCategory.entries) { cat ->
                    val count = allRoles.count { it.category == cat }
                    PaperCategoryFilterChip(
                        text = "${cat.titleArabic} ($count)",
                        isSelected = selectedCategory == cat,
                        color = cat.color,
                        onClick = { selectedCategory = cat }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // عرض البطاقات إما في شبكة معرض البطاقات أو في قائمة سريعة
            if (isGridView) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredRoles) { role ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.56f)
                                .clickable {
                                    inspectingRole = role
                                    isInspectingCardFlipped = true
                                }
                        ) {
                            MasterStoneRoleCard(
                                role = role,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredRoles) { role ->
                        PaperRoleDetailCard(
                            role = role,
                            onClick = {
                                inspectingRole = role
                                isInspectingCardFlipped = true
                            }
                        )
                    }
                }
            }
        }

        // نافذة المعاينة التفاعلية ثلاثية الأبعاد للبطاقة عند الضغط عليها
        inspectingRole?.let { role ->
            Dialog(onDismissRequest = { inspectingRole = null }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .aspectRatio(0.64f)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            FlipRoleCard(
                                role = role,
                                isFlipped = isInspectingCardFlipped,
                                onCardClick = { isInspectingCardFlipped = !isInspectingCardFlipped }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .background(PaperCard, shape = RoundedCornerShape(8.dp))
                                .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                                .clickable { inspectingRole = null }
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = InkBlack, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("إغلاق المعاينة", color = InkBlack, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // نافذة مدير صور البطاقات (27 بطاقة)
        if (showPhotoManagerDialog) {
            val context = LocalContext.current
            var targetRoleIdForPicker by remember { mutableStateOf<String?>(null) }
            val singlePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let { selectedUri ->
                    targetRoleIdForPicker?.let { rId ->
                        RoleCustomImageStore.setImageUri(rId, selectedUri)
                    }
                }
            }

            Dialog(onDismissRequest = { showPhotoManagerDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFF131722),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.88f)
                        .border(2.dp, Color(0xFFD97706), RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "🖼️ مدير صور البطاقات (27 دور)",
                                    color = Color(0xFFFDE68A),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "اختر لكل دور الصورة المرفقة الخاصة به",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 11.sp
                                )
                            }
                            IconButton(onClick = { showPhotoManagerDialog = false }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "إغلاق",
                                    tint = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(allRoles) { role ->
                                val hasCustom = customImages[role.id] != null
                                val customUri = customImages[role.id]

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF1E2430), RoundedCornerShape(10.dp))
                                        .border(
                                            width = if (hasCustom) 1.5.dp else 1.dp,
                                            color = if (hasCustom) Color(0xFF22C55E) else Color(0xFF334155),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            // صورة مصغرة
                                            Box(
                                                modifier = Modifier
                                                    .size(46.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Color(0xFF0F172A))
                                                    .border(1.dp, Color(0xFFD97706), RoundedCornerShape(8.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                if (customUri != null) {
                                                    AsyncImage(
                                                        model = ImageRequest.Builder(context)
                                                            .data(customUri)
                                                            .crossfade(true)
                                                            .build(),
                                                        contentDescription = role.nameArabic,
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                } else {
                                                    Text(
                                                        text = role.displayEmoji,
                                                        fontSize = 20.sp
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(10.dp))

                                            Column {
                                                Text(
                                                    text = role.nameArabic,
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                                Text(
                                                    text = if (hasCustom) "تم تعيين صورة مخصصة ✅" else "الصورة الافتراضية",
                                                    color = if (hasCustom) Color(0xFF4ADE80) else Color(0xFF94A3B8),
                                                    fontSize = 11.sp
                                                )
                                            }
                                        }

                                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            Button(
                                                onClick = {
                                                    targetRoleIdForPicker = role.id
                                                    singlePickerLauncher.launch("image/*")
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(0xFFD97706)
                                                ),
                                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                                modifier = Modifier.height(34.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.AddPhotoAlternate,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(14.dp),
                                                    tint = Color.White
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = if (hasCustom) "تغيير" else "اختيار",
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                            }

                                            if (hasCustom) {
                                                IconButton(
                                                    onClick = { RoleCustomImageStore.resetImage(role.id) },
                                                    modifier = Modifier.size(34.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = "حذف الصورة المخصصة",
                                                        tint = Color(0xFFF87171),
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { showPhotoManagerDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("تم وحفظ التغييرات", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PaperCategoryFilterChip(
    text: String,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(if (isSelected) color else PaperCard, shape = RoundedCornerShape(8.dp))
            .border(if (isSelected) 2.dp else 1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected && color != PaperAged && color != PaperSand) InkWhite else InkBlack,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PaperRoleDetailCard(
    role: Role,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeoCard(
        backgroundColor = PaperCard,
        shadowOffset = 2.dp,
        borderWidth = 2.dp,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(role.category.color)
                            .border(1.5.dp, PaperBorder, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        com.example.ui.components.RoleCardPhotoView(
                            role = role,
                            enableImagePicker = false,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = role.nameArabic,
                            color = InkBlack,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "${role.nameEnglish} • ${role.category.titleArabic}",
                            color = InkMuted,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                if (role.hasNightAction) {
                    NeoBadge(
                        text = "ليل ${role.nightPriority}",
                        backgroundColor = PaperVintagePurple,
                        textColor = InkWhite,
                        icon = Icons.Default.Nightlight,
                        shadowOffset = 1.dp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PaperDark, shape = RoundedCornerShape(6.dp))
                    .border(1.dp, PaperBorder, shape = RoundedCornerShape(6.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = role.description,
                    color = InkCharcoal,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
