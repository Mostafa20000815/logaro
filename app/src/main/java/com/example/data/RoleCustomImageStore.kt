package com.example.data

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.runtime.mutableStateMapOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * مدير تخزين واسترجاع الصور الحقيقية المخصصة لكل بطاقة من بطاقات اللعبة
 */
object RoleCustomImageStore {
    private const val PREFS_NAME = "role_card_custom_images"
    private var prefs: SharedPreferences? = null
    
    // خريطة الروابط والمصادر المخصصة لكل دور
    private val _customImages = MutableStateFlow<Map<String, String>>(emptyMap())
    val customImages: StateFlow<Map<String, String>> = _customImages.asStateFlow()

    fun init(context: Context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            loadAll()
        }
    }

    private fun loadAll() {
        val p = prefs ?: return
        val map = mutableMapOf<String, String>()
        p.all.forEach { (key, value) ->
            if (value is String && value.isNotBlank()) {
                map[key] = value
            }
        }
        _customImages.value = map
    }

    fun setImageUri(roleId: String, uri: Uri) {
        val uriStr = uri.toString()
        prefs?.edit()?.putString(roleId, uriStr)?.apply()
        val updated = _customImages.value.toMutableMap()
        updated[roleId] = uriStr
        _customImages.value = updated
    }

    fun setImageUrlOrPath(roleId: String, pathOrUrl: String) {
        prefs?.edit()?.putString(roleId, pathOrUrl)?.apply()
        val updated = _customImages.value.toMutableMap()
        updated[roleId] = pathOrUrl
        _customImages.value = updated
    }

    fun resetImage(roleId: String) {
        prefs?.edit()?.remove(roleId)?.apply()
        val updated = _customImages.value.toMutableMap()
        updated.remove(roleId)
        _customImages.value = updated
    }

    fun resetAll() {
        prefs?.edit()?.clear()?.apply()
        _customImages.value = emptyMap()
    }

    fun getImage(roleId: String): String? {
        return _customImages.value[roleId]
    }
}
