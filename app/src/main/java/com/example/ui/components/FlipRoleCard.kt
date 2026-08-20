package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import com.example.data.model.Role

/**
 * بطاقة دور أسطورية ثلاثية الأبعاد تنقلب 3D عند اللمس مع الحفاظ على القالب الحجري
 */
@Composable
fun FlipRoleCard(
    role: Role,
    isFlipped: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current.density

    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ),
        label = "MasterStoneCardFlip"
    )

    val isFrontVisible = rotationY > 90f

    Box(
        modifier = modifier
            .testTag("flip_role_card")
            .graphicsLayer {
                this.rotationY = rotationY
                this.cameraDistance = 18f * density
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onCardClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isFrontVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        this.rotationY = 180f
                    }
            ) {
                MasterStoneRoleCard(
                    role = role,
                    modifier = Modifier.fillMaxSize(),
                    onCardClick = onCardClick
                )
            }
        } else {
            MasterStoneCardBackView(
                modifier = Modifier.fillMaxSize(),
                onCardClick = onCardClick
            )
        }
    }
}
