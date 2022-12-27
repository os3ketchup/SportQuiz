package com.bumpy.sport

object Constants {
    const val USER_NAME = "user_name"

    fun getQuestion():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val que1 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.tshabalala,
            "Makalele",
            "Terezegue",
            "Tshabalala",
            "Davids",
            3,"The midfielder registered the first goal of the competition in 2010 World Cup"
        )
        questionList.add(que1)
        val que2 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.canavaro,
            "Carlos",
            "Guti",
            "Bonucci",
            "Cannavaro",
            4,
            "The only defender in the last two decades to win the Ballon d'Or"
        )
        questionList.add(que2)
        val que3 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.batistuta,
            "Batistuta",
            "Kruiff",
            "Ederson",
            "Anderson",
            1,
            " he was named by Pelé in the FIFA 100 list of the world's greatest living players"
        )
        questionList.add(que3)
        val que4 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.makalele,
            "Kante",
            "Makalele",
            "Lamela",
            "Seedorf",
            2,
            "He is a French football manager and former professional player who played as a defensive midfielder"
        )
        questionList.add(que4)
        val que5 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.johan_cruif,
            "Cruiff",
            "Platini",
            "Pushkash",
            "Modrich",
            1,
            "He won the Ballon d'Or three times, in 1971, 1973 and 1974"
        )
        questionList.add(que5)
        val que6 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.diego_milito,
            "Pacheto",
            "Alex",
            "Cambiaso",
            "Milito",
            4,
            "He is scored double on the match against Bayern Munchen"
        )
        questionList.add(que6)
        val que7 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.van_persie,
            "Van Nistelroy",
            "Van Persie",
            "Pires",
            "Morientes",
            2,
            "Nominated for the Pushkash award"
        )
        questionList.add(que7)
        val que8 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.edgor_davids,
            "Emilanho",
            "Gatusso",
            "Davids",
            "Zambrotta",
            3,
            "Famous player who wears glasses"
        )
        questionList.add(que8)
        val que9 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.rafael_da_silva,
            "Rafael",
            "Fabio",
            "Lopes",
            "Edgor",
            1,
            "One of the brothers who played in MU"
        )
        questionList.add(que9)
        val que10 = Question(
            1,
            "Who does describe on this image?",
            R.drawable.rene_higuita,
            "Marsel",
            "Higuita",
            "Lusiano",
            "Gabriel",
            2,
            "Goalkeeper - scorpion"
        )
        questionList.add(que10)
        return questionList
    }
}