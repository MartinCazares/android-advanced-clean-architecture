package com.doepiccoding.advancedcleanarchitecture.ui.breeds.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doepiccoding.domain.entity.CatBreed

class CatsListView {

    val data = mutableStateListOf<CatBreed>()

    companion object {
        private val ITEM_SEPARATION_SPACE = 8.dp
        private val BREED_TITLE_SIZE = 24.sp
    }

    @Composable
    fun RenderView() {
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(ITEM_SEPARATION_SPACE),
        ) {
            itemsIndexed(data) { index, item ->
                Text(
                    text = "${index + 1} - ${item.breed}",
                    color = Color.LightGray,
                    fontSize = BREED_TITLE_SIZE,
                    modifier = Modifier.padding(start = ITEM_SEPARATION_SPACE))
            }
        }
    }

}
