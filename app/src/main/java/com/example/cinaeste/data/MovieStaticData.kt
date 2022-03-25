package com.example.cinaeste.data

fun favoriteMovies() : List<Movie>{
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama"),
        Movie(2,"Taken","When his estranged daughter is kidnapped in Paris, a former spy (Neeson) sets out to find her at any cost. Relying on his special skills, he tracks down the ruthless gang that abducted her and launches a one-man war to bring them to justice and rescue his daughter.",
               "27.02.2008","https://www.imdb.com/title/tt0936501/","action"),
        Movie(3,"Fatherhood","A father brings up his baby girl as a single dad after the unexpected death of his wife who died a day after their daughter's birth.","18.06.2021","https://www.imdb.com/title/tt4733624/","comedy")
    )

}

fun recentMovies():List<Movie>{
    return listOf(
        Movie(1,"The Contractor",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "thriller"),
        Movie(2,"Deep water","Deep Water is about the writer's journey of overcoming the fear of water, which is deeply rooted in him since childhood. ","18.03.2022","https://www.imdb.com/title/tt2180339/","thriller"),
        Movie(3,"Master","Two African American women begin to share disturbing experiences at a predominantly white college in New England.","18.03.2022","https://www.imdb.com/title/tt11286210/","drama")

    )
}