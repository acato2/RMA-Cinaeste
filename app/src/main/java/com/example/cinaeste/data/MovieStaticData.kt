package com.example.cinaeste.data

fun favoriteMovies() : List<Movie>{
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama"),
        Movie(2,"Taken","When his estranged daughter is kidnapped in Paris, a former spy (Neeson) sets out to find her at any cost. Relying on his special skills, he tracks down the ruthless gang that abducted her and launches a one-man war to bring them to justice and rescue his daughter.",
            "27.02.2008","https://www.imdb.com/title/tt0936501/","drawable/action"),
        Movie(3,"Fatherhood","A father brings up his baby girl as a single dad after the unexpected death of his wife who died a day after their daughter's birth.","18.06.2021","https://www.imdb.com/title/tt4733624/","comedy"),
        Movie(4,"The Conjuring","In 1970, paranormal investigators and demonologists Lorraine (Vera Farmiga) and Ed (Patrick Wilson) Warren are summoned to the home of Carolyn (Lili Taylor) and Roger (Ron Livingston) Perron. The Perrons and their five daughters have recently moved into a secluded farmhouse, where a supernatural presence has made itself known.","4.06.2021","https://www.imdb.com/title/tt1457767/","horror"),
        Movie(5,"The Royal Treatment","New York hairdresser Izzy seizes the chance to work at the wedding of a charming prince. When sparks start to fly between the two of them, love and duty are put to the test as the time of the wedding draws closer.","20.01.2022","https://www.imdb.com/title/tt13989030/","romance")

    )

}

fun recentMovies():List<Movie>{
    return listOf(
        Movie(1,"The Contractor",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "drawable/action"),
        Movie(2,"Love Tactics",
            "An ad executive and a fashion designer-blogger don't believe in love, so they place a bet to make the other fall head over heels -- with unusual tactics.",
            "11.02.2022.","https://www.imdb.com/title/tt14486678/",
            "romance"),
        Movie(3,"Free Guy","When a bank teller discovers he's actually a background player in an open-world video game, he decides to become the hero of his own story -- one that he can rewrite himself.  ","13.08.2021","https://www.imdb.com/title/tt6264654/","scifi"),
        Movie(4,"Master","Two African American women begin to share disturbing experiences at a predominantly white college in New England.","18.03.2022","https://www.imdb.com/title/tt11286210/","drama"),
        Movie(5,"Texas Chainsaw Massacre","When Sally (Marilyn Burns) hears that her grandfather's grave may have been vandalized, she and her paraplegic brother, Franklin (Paul A. Partain), set out with their friends to investigate.","18.02.2022","https://www.imdb.com/title/tt11755740/","horror")

    )
}
fun movieActors():Map<String,List<String>>{
    return mapOf(
        "Pride and prejudice" to listOf("Keira Knightley","Matthew Macfadyen","Rosamund Pike"),
        "Taken" to listOf("Liam Neeson","Maggie Grace","Jon Gries"),
        "Fatherhood" to listOf("Kevin Hart","Anthony Carrigan","Melody Hurd"),
        "The Conjuring" to listOf("Vera Farmiga","Patrick Wilson","Sterling Jerins"),
        "The Royal Treatment" to listOf("Laura Marano","Mena Massoud","Chelsie Preston Crayford"),
        "The Contractor" to listOf("Eliza Bennett","Wesley Snipes","Lena Headey"),
        "Love Tactics" to listOf("Demet Özdemir","Şükrü Özyıldız","Yasemin Yazıcı"),
        "Free Guy" to listOf("Ryan Reynolds","Jodie Comer","Dwayne Johnson"),
        "Master" to listOf("Thalapathy Vijay","Malavika Mohanan","Vijay Sethupathi"),
        "Texas Chainsaw Massacre" to listOf("Sarah Yarkin","Mark Burnham","Olwen Fouéré")
    )
}
fun movieSimilar():Map<String,List<String>>{
    return mapOf(
        "Pride and prejudice" to listOf("Atonement","Anna Karenina","Sense and Sensibility"),
        "Taken" to listOf("Salt","Unknown","Peppermint"),
        "Fatherhood" to listOf("Yes Day","The Starling","The Unforgivable"),
        "The Conjuring" to listOf("The Curse of La Llorona","The Nun","Annabelle"),
        "The Royal Treatment" to listOf("Through My Window","Love Tactics"),
        "The Contractor" to listOf("All the Old Knives","Infinite","Without Remorse"),
        "Love Tactics" to listOf("UFO","Last Summer","Hot Sweet Sour"),
        "Free Guy" to listOf("Reminiscence","Infinite","6 Underground"),
        "Master" to listOf("Magnolia","Inherent Vice","Phantom Thread"),
        "Texas Chainsaw Massacre" to listOf("Leatherface","Psycho","Halloween")

    )

}