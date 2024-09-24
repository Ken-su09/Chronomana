package com.suonk.chronomana.model.data.repositories.players_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.suonk.chronomana.model.data.entities.PlayerEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebasePlayersListRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : FirebasePlayersListRepository {

    override fun getPlayersListFirebaseFirestore(): Flow<List<PlayerEntity>> = callbackFlow {
        val listenerRegistration = firebaseFirestore.collection(ALL_USERS)
            .addSnapshotListener { querySnapshot, _ ->
                if (querySnapshot != null) {
                    Log.i("FirebaseAllPlayers", "QuerySnapshot: $querySnapshot")
                    Log.i("FirebaseAllPlayers", "QuerySnapshot.isEmpty: ${querySnapshot.isEmpty}")

                    if (querySnapshot.isEmpty) {
                        trySend(emptyList()).isSuccess
                    } else {
                        try {
                            val players = querySnapshot.toObjects(PlayerEntity::class.java)
                            trySend(players).isSuccess
                        } catch (e: Exception) {
                            Log.e("FirebaseError", "Exception during parsing: $e")
                            close(e)
                        }
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun getPlayersAvailableFlow(): Flow<List<PlayerEntity>> {
        val allPlayersAvailable = MutableStateFlow<List<PlayerEntity>>(emptyList())

        firebaseFirestore.collection(ALL_USERS).addSnapshotListener { querySnapshot: QuerySnapshot?, error: FirebaseFirestoreException? ->
            if (querySnapshot != null) {
                try {
                    allPlayersAvailable.value = querySnapshot.toObjects(PlayerEntity::class.java)
                } catch (e: java.lang.Exception) {
                    Log.i("", "" + e)
                }
            }
        }

        return allPlayersAvailable
    }


    companion object {
        private const val ALL_USERS = "ALL_USERS"
    }
}