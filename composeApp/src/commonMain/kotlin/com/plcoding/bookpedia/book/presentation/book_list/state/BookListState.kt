package com.plcoding.bookpedia.book.presentation.book_list.state

import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.utill.UiScreenState


data class BookListState(
    val listBooks: List<Book> = emptyList(),
    val uiState: UiScreenState = UiScreenState(screenState = ScreenState.Loading),
)


val tempImageURl = "https://cdn.vectorstock.com/i/500p/11/48/stack-of-colorful-books-icon-in-cartoon-style-vector-51211148.jpg"
val dummyBooks = listOf(
    Book(
        id = "1",
        title = "Kotlin in Action",
        imageUrl = tempImageURl,
        authors = listOf("Dmitry Jemerov", "Svetlana Isakova"),
        description = "A comprehensive guide to Kotlin programming.",
        languages = listOf("English"),
        firstPublishYear = "2017",
        averageRating = 4.5,
        ratingCount = 1200,
        numPages = 360,
        numEditions = 3
    ),
    Book(
        id = "2",
        title = "Android Programming with Kotlin",
        imageUrl = tempImageURl,
        authors = listOf("John Doe"),
        description = "A hands-on guide to developing Android apps with Kotlin.",
        languages = listOf("English"),
        firstPublishYear = "2019",
        averageRating = 4.2,
        ratingCount = 800,
        numPages = 420,
        numEditions = 2
    ),
    Book(
        id = "3",
        title = "Mastering Kotlin",
        imageUrl = tempImageURl,
        authors = listOf("Nate Ebel"),
        description = "Deep dive into advanced Kotlin programming techniques.",
        languages = listOf("English"),
        firstPublishYear = "2020",
        averageRating = 4.8,
        ratingCount = 600,
        numPages = 500,
        numEditions = 1
    ),
    Book(
        id = "4",
        title = "Kotlin for Beginners",
        imageUrl = tempImageURl,
        authors = listOf("Mark Lewis"),
        description = "A step-by-step introduction to Kotlin for new programmers.",
        languages = listOf("English"),
        firstPublishYear = "2021",
        averageRating = 4.3,
        ratingCount = 500,
        numPages = 320,
        numEditions = 1
    ),
    Book(
        id = "5",
        title = "Effective Kotlin",
        imageUrl = tempImageURl,
        authors = listOf("Marcin Moskala"),
        description = "Best practices and patterns for writing clean Kotlin code.",
        languages = listOf("English"),
        firstPublishYear = "2020",
        averageRating = 4.7,
        ratingCount = 950,
        numPages = 450,
        numEditions = 2
    ),
    Book(
        id = "6",
        title = "Kotlin Coroutines Deep Dive",
        imageUrl = tempImageURl,
        authors = listOf("Jane Smith"),
        description = "An in-depth look at coroutines and concurrency in Kotlin.",
        languages = listOf("English"),
        firstPublishYear = "2022",
        averageRating = 4.6,
        ratingCount = 720,
        numPages = 380,
        numEditions = 1
    ),
    Book(
        id = "7",
        title = "Jetpack Compose Essentials",
        imageUrl = tempImageURl,
        authors = listOf("Chris Johnson"),
        description = "Learn how to build modern UI with Jetpack Compose.",
        languages = listOf("English"),
        firstPublishYear = "2023",
        averageRating = 4.8,
        ratingCount = 1100,
        numPages = 500,
        numEditions = 1
    ),
    Book(
        id = "8",
        title = "Kotlin Data Structures and Algorithms",
        imageUrl = tempImageURl,
        authors = listOf("Alice Brown"),
        description = "A practical guide to DS & Algos in Kotlin.",
        languages = listOf("English"),
        firstPublishYear = "2019",
        averageRating = 4.5,
        ratingCount = 630,
        numPages = 410,
        numEditions = 2
    ),
    Book(
        id = "9",
        title = "Kotlin DSLs in Action",
        imageUrl = tempImageURl,
        authors = listOf("Robert Martin"),
        description = "Discover the power of Kotlin DSLs in software development.",
        languages = listOf("English"),
        firstPublishYear = "2021",
        averageRating = 4.4,
        ratingCount = 780,
        numPages = 290,
        numEditions = 1
    ),
    Book(
        id = "10",
        title = "Android App Architecture with Kotlin",
        imageUrl = tempImageURl,
        authors = listOf("Emily White"),
        description = "Building scalable Android apps using MVVM, MVI, and Clean Architecture.",
        languages = listOf("English"),
        firstPublishYear = "2022",
        averageRating = 4.7,
        ratingCount = 880,
        numPages = 460,
        numEditions = 1
    ),
    Book(
        id = "11",
        title = "Kotlin Game Development",
        imageUrl = tempImageURl,
        authors = listOf("Daniel Green"),
        description = "Learn to build 2D and 3D games using Kotlin.",
        languages = listOf("English"),
        firstPublishYear = "2018",
        averageRating = 4.3,
        ratingCount = 510,
        numPages = 350,
        numEditions = 2
    ),
    Book(
        id = "12",
        title = "Advanced Kotlin Programming",
        imageUrl = tempImageURl,
        authors = listOf("Michael Scott"),
        description = "A deep dive into advanced Kotlin features and metaprogramming.",
        languages = listOf("English"),
        firstPublishYear = "2020",
        averageRating = 4.6,
        ratingCount = 900,
        numPages = 470,
        numEditions = 2
    ),
    Book(
        id = "13",
        title = "The Art of Kotlin",
        imageUrl = tempImageURl,
        authors = listOf("Sophia Clarke"),
        description = "A creative approach to solving real-world problems with Kotlin.",
        languages = listOf("English"),
        firstPublishYear = "2023",
        averageRating = 4.9,
        ratingCount = 1100,
        numPages = 520,
        numEditions = 1
    )
)

val dummyBookListState = BookListState(
    listBooks = dummyBooks,
)
