package io.github.innofang.jetpackdemo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "word")
    var word: String
)