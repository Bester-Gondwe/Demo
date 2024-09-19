package com.example.git

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme // Use MaterialTheme from Material3
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Song(val title: String, val artist: String, val album: String, val duration: Int)

class MainActivity : ComponentActivity() {

    private val songs = listOf(
        Song("song1.mp3", "Artist 1", "Album 1", 210),
        Song("Song Title 2", "Artist 2", "Album 2", 180),
        Song("Song Title 3", "Artist 3", "Album 3", 240),
        Song("Song Title 4", "Artist 4", "Album 4", 200),
        Song("Song Title 5", "Artist 5", "Album 5", 215)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicApp(songs)
        }
    }
}

@Composable
fun MusicApp(songs: List<Song>) {
    var currentSongIndex by remember { mutableStateOf(0) }
    var seekPosition by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Now Playing: ${songs[currentSongIndex].title}", style = MaterialTheme.typography.titleMedium)
        Text(text = "Artist: ${songs[currentSongIndex].artist}", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        SeekBar(seekPosition) { newPosition ->
            seekPosition = newPosition
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { if (currentSongIndex > 0) currentSongIndex-- }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { if (currentSongIndex < songs.size - 1) currentSongIndex++ }) {
                Text("Next")
            }
        }


    }
}

@Composable
fun SeekBar(position: Int, onValueChange: (Int) -> Unit) {
    Slider(
        value = position.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        valueRange = 0f..300f, // Adjust according to your needs
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMusicApp() {
    MusicApp(songs = listOf(Song("Sample Song", "Sample Artist", "Sample Album", 180)))
}
