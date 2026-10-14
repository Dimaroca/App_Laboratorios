package plat.labs.rickymorty.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "pref")

class Preferences(private val context: Context) {

    companion object {
        private val NAME_KEY = stringPreferencesKey("name")
    }

    val user: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[NAME_KEY]
    }

    suspend fun save(name: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
        }
    }

    suspend fun clear() {
        context.dataStore.edit { prefs ->
            prefs.remove(NAME_KEY)
        }
    }
}