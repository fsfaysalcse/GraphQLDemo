package com.faysal.graphqlspacex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo3.ApolloClient
import com.faysal.episode.EpisodesQuery
import com.faysal.episode.LocationsQuery
import com.faysal.graphqlspacex.ui.theme.GraphQLSpaceXTheme
import kotlinx.coroutines.launch

private const val TAG = "MainActivityGraphQL"

class MainActivity : ComponentActivity() {

    lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()


        setContent {
            val scope = rememberCoroutineScope()
            GraphQLSpaceXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scope = rememberCoroutineScope()

                    LaunchedEffect(key1 = true){
                        scope.launch {
                            val response = apolloClient.query(LocationsQuery()).execute()

                            Log.d(TAG, "onCreate: "+response.data.toString())
                        }
                    }
                }
            }
        }
    }
}


