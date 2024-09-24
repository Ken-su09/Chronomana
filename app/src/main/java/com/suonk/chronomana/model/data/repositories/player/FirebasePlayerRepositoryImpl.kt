package com.suonk.chronomana.model.data.repositories.player

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.suonk.chronomana.model.data.entities.PlayerEntity
import com.suonk.chronomana.model.data.repositories.players_list.FirebasePlayersListRepositoryImpl
import com.suonk.chronomana.model.data.repositories.players_list.FirebasePlayersListRepositoryImpl.Companion
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebasePlayerRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : FirebasePlayerRepository {

    override fun addNewUserToFirebaseFirestore(player: PlayerEntity, userId: String) {
        firebaseFirestore.collection(ALL_USERS)
            .whereEqualTo(ID, userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.result.isEmpty) {
                    firebaseFirestore.collection(ALL_USERS).document(userId).set(player)
                }
            }
    }

    override fun getPlayerDataFirebaseFirestoreByUserId(userId: String): Flow<PlayerEntity> = callbackFlow {
        val listenerRegistration =
            firebaseFirestore.collection(ALL_USERS).document().addSnapshotListener { querySnapshot, error ->
                if (querySnapshot != null) {
                    try {
                        querySnapshot.toObject(PlayerEntity::class.java)?.let { trySend(it) }
                    } catch (e: Exception) {
                        Log.i("FirebaseError", "Exception : $e")
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    companion object {
        private const val ALL_USERS = "ALL_USERS"
        private const val ID = "ID"
    }
}