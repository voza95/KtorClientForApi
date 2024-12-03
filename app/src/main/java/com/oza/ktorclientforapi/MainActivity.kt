package com.oza.ktorclientforapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oza.ktorclientforapi.data.remote.PostsService
import com.oza.ktorclientforapi.data.remote.dto.PostResponse
import com.oza.ktorclientforapi.ui.theme.KtorClientForApiTheme

class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                }
            )
            KtorClientForApiTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
