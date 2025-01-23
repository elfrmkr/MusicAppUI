package eu.tutorials.musicappui

import androidx.annotation.DrawableRes

data class DummyLibraryData(@DrawableRes val icon: Int, val name: String)

val libraries = listOf<DummyLibraryData>(
    DummyLibraryData(R.drawable.ic_playlist_green, "Playlist"),
    DummyLibraryData(R.drawable.ic_microphone, "Artists"),
    DummyLibraryData(R.drawable.ic_baseline_album_24, "Album"),
    DummyLibraryData(R.drawable.ic_baseline_music_note_24, "Songs"),
    DummyLibraryData(R.drawable.ic_genre, "Genre"),
    )