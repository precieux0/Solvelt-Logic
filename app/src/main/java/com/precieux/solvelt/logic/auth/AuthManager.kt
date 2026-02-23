package com.precieux.solvelt.logic.auth

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.precieux.solvelt.logic.BuildConfig
import com.precieux.solvelt.logic.data.entities.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    private val context: Context
) {
    private val gso: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()
    }
    
    val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(context, gso)
    }
    
    fun isUserSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account != null
    }
    
    fun getSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }
    
    suspend fun handleGoogleSignInResult(data: Intent?): Result<GoogleSignInAccount> {
        return try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            Result.success(account)
        } catch (e: ApiException) {
            Result.failure(e)
        }
    }
    
    fun getCurrentUser(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }
    
    fun createGuestUser(): User {
        val guestId = "guest_${System.currentTimeMillis()}"
        return User(
            id = guestId,
            email = "guest@solvelt.logic",
            displayName = "Guest User",
            photoUrl = null,
            isGuest = true
        )
    }
    
    fun googleAccountToUser(account: GoogleSignInAccount): User {
        return User(
            id = account.id ?: account.email ?: "",
            email = account.email ?: "",
            displayName = account.displayName,
            photoUrl = account.photoUrl?.toString(),
            isGuest = false
        )
    }
    
    suspend fun signOut() {
        googleSignInClient.signOut().await()
    }
    
    fun getCurrentUserId(): String? {
        return getCurrentUser()?.id
    }
}