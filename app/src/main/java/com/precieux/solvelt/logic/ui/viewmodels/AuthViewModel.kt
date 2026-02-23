package com.precieux.solvelt.logic.ui.viewmodels

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.precieux.solvelt.logic.auth.AuthManager
import com.precieux.solvelt.logic.data.entities.User
import com.precieux.solvelt.logic.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    private val _currentUser = MutableLiveData<User?>(null)
    val currentUser: LiveData<User?> = _currentUser

    private val _isGuest = MutableLiveData(false)
    val isGuest: LiveData<Boolean> = _isGuest

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        viewModelScope.launch {
            val googleAccount = authManager.getCurrentUser()
            if (googleAccount != null) {
                val user = authManager.googleAccountToUser(googleAccount)
                userRepository.saveUser(user)
                _currentUser.value = user
                _isGuest.value = false
                _authState.value = AuthState.Authenticated
            } else {
                val localUser = userRepository.getCurrentUser()
                if (localUser?.isGuest == true) {
                    _currentUser.value = localUser
                    _isGuest.value = true
                    _authState.value = AuthState.Guest
                } else {
                    _authState.value = AuthState.Unauthenticated
                }
            }
        }
    }

    fun getSignInIntent(): Intent {
        return authManager.getSignInIntent()
    }

    fun handleGoogleSignInResult(data: Intent?) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authManager.handleGoogleSignInResult(data)
                .onSuccess { googleAccount ->
                    val user = authManager.googleAccountToUser(googleAccount)
                    userRepository.saveUser(user)
                    _currentUser.value = user
                    _isGuest.value = false
                    _authState.value = AuthState.Authenticated
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(error.message ?: "Authentication failed")
                }
        }
    }

    fun signInAsGuest() {
        viewModelScope.launch {
            val guestUser = authManager.createGuestUser()
            userRepository.saveUser(guestUser)
            _currentUser.value = guestUser
            _isGuest.value = true
            _authState.value = AuthState.Guest
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authManager.signOut()
            _currentUser.value?.let { user ->
                if (user.isGuest) {
                    userRepository.deleteUser(user)
                }
            }
            _currentUser.value = null
            _isGuest.value = false
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun upgradeGuestToUser(googleAccount: GoogleSignInAccount) {
        viewModelScope.launch {
            val user = authManager.googleAccountToUser(googleAccount)
            _currentUser.value?.let { guestUser ->
                if (guestUser.isGuest) {
                    userRepository.deleteUser(guestUser)
                }
            }
            userRepository.saveUser(user)
            _currentUser.value = user
            _isGuest.value = false
            _authState.value = AuthState.Authenticated
        }
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        object Authenticated : AuthState()
        object Guest : AuthState()
        object Unauthenticated : AuthState()
        data class Error(val message: String) : AuthState()
    }
}