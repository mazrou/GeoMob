package com.e.geomob.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.*
import com.e.geomob.data.respository.Repository

class MainViewModel(
    private val repository: Repository
) : ViewModel() {


    init {
        repository.countries.observeForever {
            Log.d(Debug.TAG , it.toString())
            _countries.postValue(it)
        }
    }

    private val _countries = MutableLiveData<List<Country>>()

    val countries : LiveData<List<Country>>
        get() =_countries


    fun initDataBase(){
        Log.d(Debug.TAG , "init the countries")
        initCountries()

        Log.d(Debug.TAG , "init the historical events")
        initHistoricalEvents()

        Log.d(Debug.TAG , "init the personalities")
        initPersonalities()

        Log.d(Debug.TAG , "init the slideShow")
        initSlideShows()

        Log.d(Debug.TAG , "init the Resources")
        initResource()

        Log.d(Debug.TAG , "init the YoutubeVideo")
        initYoutubeVideos()

        Log.d(Debug.TAG , "initDataBase Finished")

    }

    private fun initSlideShows() {
        val images = listOf<SlideItem>(

            SlideItem(1,1,R.raw.sao_paolo),
            SlideItem(2,1,R.raw.rio),
            SlideItem(3,1,R.raw.amazon),

            SlideItem(4,2,R.raw.wall),
            SlideItem(5,2,R.raw.wuhan),
            SlideItem(6,2,R.raw.hong_kong),

            SlideItem(7,3,R.raw.berlin),
            SlideItem(8,3,R.raw.frankfort),
            SlideItem(9,3,R.raw.strasborg),

            SlideItem(10,4,R.raw.roma),
            SlideItem(11,4 , R.raw.milano),
            SlideItem(12,4,R.raw.vinicia),

            SlideItem(13,5,R.raw.cairo),
            SlideItem(14,5,R.raw.haram),
            SlideItem(15,5,R.raw.nile)

        )

        repository.initSlideShow(images)
    }

    private fun initCountries(){
       val list  = ArrayList<Country>()
       list.add(Country(1, "Brazil" , R.raw.brazil , "Brazil (Portuguese: Brasil; Brazilian Portuguese: [bɾaˈziw]),[nt 1] officially the Federative Republic of Brazil (Portuguese: About this soundRepública Federativa do Brasil),[10] is the largest country in both South America and Latin America. At 8.5 million square kilometers (3.2 million square miles)[11] and with over 211 million people, Brazil is the world's fifth-largest country by area and the sixth most populous. Its capital is Brasília, and its most populous city is São Paulo. The federation is composed of the union of the 26 states and the Federal District. It is the largest country to have Portuguese as an official language and the only one in the Americas;[12][13] it is also one of the most multicultural and ethnically diverse nations, due to over a century of mass immigration from around the world.[14]\n" + "\n" + "Bounded by the Atlantic Ocean on the east, Brazil has a coastline of 7,491 kilometers (4,655 mi).[15] It borders all other countries in South America except Ecuador and Chile and covers 47.3% of the continent's land area.[16] Its Amazon River basin includes a vast tropical forest, home to diverse wildlife, a variety of ecological systems, and extensive natural resources spanning numerous protected habitats.[15] This unique environmental heritage makes Brazil one of 17 megadiverse countries, and is the subject of significant global interest and debate regarding deforestation and environmental protection."  , 209.5F ,8.516F , "Brasília", "R$" , "https://twitter.com/search?q=brazil%20brazil%20البرازيل&f=live" ))
       list.add(Country(2, "China" , R.raw.china , "China (simplified Chinese: 中国; traditional Chinese: 中國; pinyin: Zhōngguó; lit.: 'Central State; Middle Kingdom'), officially the People's Republic of China (PRC; simplified Chinese: 中华人民共和国; traditional Chinese: 中華人民共和國; pinyin: Zhōnghuá Rénmín Gònghéguó), is a country in East Asia. It is the world's most populous country, with a population of around 1.4 billion in 2019.[7] Covering approximately 9.6 million square kilometres (3.7 million mi2), it is the world's third or fourth-largest country by area.[j] Governed by the Communist Party of China, the state exercises jurisdiction over 22 provinces,[k] five autonomous regions, four direct-controlled municipalities (Beijing, Tianjin, Shanghai, and Chongqing), and the special administrative regions of Hong Kong and Macau." , 1393F ,9.597F ,"Pékin","元/¥","https://twitter.com/search?q=chine%20china%20الصين&f=live"))
       list.add(Country(3, "Germany" , R.raw.germany , "Germany (German: Deutschland, German pronunciation: [ˈdɔʏtʃlant]), officially the Federal Republic of Germany (German: Bundesrepublik Deutschland, About this soundlisten),[e] is a country in Central and Western Europe. Covering an area of 357,022 square kilometres (137,847 sq mi), it lies between the Baltic and North seas to the north, and the Alps to the south. It borders Denmark to the north, Poland and the Czech Republic to the east, Austria and Switzerland to the south, and France, Luxembourg, Belgium, and the Netherlands to the west." , 83.02F , 0.35F ,"Berlin" , "Euro" , "https://twitter.com/search?q=germany%20germany%20ألمانيا&f=live"))
       list.add(Country(4, "Italy" , R.raw.italy , "Italy (Italian: Italia [iˈtaːlja] (About this soundlisten)), officially the Italian Republic (Italian: Repubblica Italiana [reˈpubblika itaˈljaːna]),[10][11][12][13] is a country consisting of a peninsula delimited by the Alps and surrounded by several islands. Italy is located in south-central Europe,[14][15] and it is also considered a part of western Europe.[16][17] A unitary parliamentary republic with its capital in Rome, the country covers a total area of 301,340 km2 (116,350 sq mi) and shares land borders with France, Switzerland, Austria, Slovenia, and the enclaved microstates of Vatican City and San Marino. Italy has a territorial exclave in Switzerland (Campione) and a maritime exclave in Tunisian waters (Lampedusa). With around 60 million inhabitants, Italy is the third-most populous member state of the European Union." , 60.36F , 0.3F , "Roma", "Euro" , "https://twitter.com/search?q=italy%20italia%20ايطاليا&f=live"))
       list.add(Country(5, "Egypt" , R.raw.egypt , "Egypt (/ˈiːdʒɪpt/ (About this soundlisten) EE-jipt; Arabic: مِصر\u200E Miṣr), officially the Arab Republic of Egypt, is a transcontinental country spanning the northeast corner of Africa and southwest corner of Asia by a land bridge formed by the Sinai Peninsula. Egypt is a Mediterranean country bordered by the Gaza Strip (Palestine) and Israel to the northeast, the Gulf of Aqaba and the Red Sea to the east, Sudan to the south, and Libya to the west. Across the Gulf of Aqaba lies Jordan, across the Red Sea lies Saudi Arabia, and across the Mediterranean lie Greece, Turkey and Cyprus, although none share a land border with Egypt." ,98.42F  ,1.01F, "Cairo", "E£","https://twitter.com/search?q=egypt%20egypt%20مصر&f=live"))
       repository.initDataBase(list)
   }

    private fun initHistoricalEvents(){
        val listHistory = listOf<HistoricalEvent>(

            HistoricalEvent(1, 5,"Yom Kippur War" ,  "Ramadan War, or October War (Hebrew: מלחמת יום הכיפורים‎, Milẖemet Yom HaKipurim, or מלחמת יום כיפור, Milẖemet Yom Kipur; Arabic: حرب أكتوبر‎, Ḥarb ʾUktōbar, or حرب تشرين, Ḥarb Tišrīn), also known as the 1973 Arab–Israeli War, was a war fought from 6 to 25 October 1973, by a coalition of Arab states led by Egypt and Syria against Israel. The war took place mostly in Sinai and the Golan—occupied by Israel during the 1967 Six-Day War—with some fighting in African Egypt and northern Israel.[57][58] Egypt's initial war objective was to use its military to seize a foothold on the east bank of the Suez Canal and use this to negotiate the return of the rest of Sinai.[59][60][61][62]" ),
            HistoricalEvent(2, 5,"French campaign in Egypt" ,  "The French campaign in Egypt and Syria (1798–1801) was Napoleon Bonaparte's campaign in the Ottoman territories of Egypt and Syria, proclaimed to defend French trade interests, seek further direct alliances with Tipu Sultan, weaken Britain's access to India, and to establish scientific enterprise in the region. It was the primary purpose of the Mediterranean campaign of 1798, a series of naval engagements that included the capture of Malta. The campaign ended in defeat for Napoleon, and the withdrawal of French troops from the region"),

            HistoricalEvent(3, 3,"World War II" ,  "World War II (often abbreviated as WWII or WW2), also known as the Second World War, was a global war that lasted from 1939 to 1945. The vast majority of the world's countries—including all the great powers—eventually formed two opposing military alliances: the Allies and the Axis. A state of total war emerged, directly involving more than 100 million people from more than 30 countries. The major participants threw their entire economic, industrial, and scientific capabilities behind the war effort, blurring the distinction between civilian and military resources. World War II was the deadliest conflict in human history, marked by 70 to 85 million fatalities, mostly in the Soviet Union and China. Tens of millions of people died during the conflict due to genocides (including the Holocaust), premeditated death from starvation, massacres, and disease. Aircraft played a major role in the conflict which included the use of terror bombing, strategic bombing and the only use of nuclear weapons in war." ),
            HistoricalEvent(4, 3,"World War I" ,  "World War I (often abbreviated as WWI or WW1), also known as the First World War or the Great War, was a global war that lasted from 28 July 1914 to 11 November 1918. Contemporaneously described as \"the war to end all wars\",[7] it led to the mobilisation of more than 70 million military personnel, including 60 million Europeans, making it one of the largest wars in history.[8][9] It is also one of the deadliest conflicts in history,[10] with an estimated nine million combatant deaths and 13 million civilian deaths as a direct result of the war,[11] while resulting genocides and the related 1918 influenza pandemic caused another 17–100 million deaths worldwide.[12][13]"),

            HistoricalEvent(5, 4,"Roman Italy" ,  "Ramadan War, or October War (Hebrew: מלחמת יום הכ" ),
            HistoricalEvent(6, 4,"Roman Italy" ,  "Le fascisme est un système politique autoritaire qui associe populisme, nationalisme1 et totalitarisme2 au nom d'un idéal collectif suprême. Mouvement révolutionnaire, il s'oppose frontalement à la démocratie parlementaire et au libéralisme traditionnel, et remet en cause l'individualisme codifié par la pensée philosophique des Lumières3,4. Issu de diverses composantes de la philosophie européenne du xixe siècle5, le fascisme a trouvé dans les circonstances économiques et historiques de l'après-première guerre mondiale le contexte qui lui a permis d'accéder au pouvoir, d'abord en Italie dans les années 1920 avec Mussolini, puis sous une variante accentuée, militariste, en Allemagne dans les années 1930 avec le nazisme et Hitler."),

            HistoricalEvent(7, 2,"Great Wall of China" ,  "The Great Wall of China (Chinese: 萬里長城; pinyin: Wànlǐ Chángchéng) is the collective name of a series of fortification systems generally built across the historical northern borders of China to protect and consolidate territories of Chinese states and empires against various nomadic groups of the steppe and their polities. Several walls were being built from as early as the 7th century BC by ancient Chinese states;[2] selective stretches were later joined together by Qin Shi Huang (220–206 BC), the first emperor of China. Little of the Qin wall remains.[3] Later on, many successive dynasties have built and maintained multiple stretches of border walls. The most well-known sections of the wall were built by the Ming dynasty (1368–1644).\n" ),
            HistoricalEvent(8, 2,"Communist Party of China" ,  "The Communist Party of China (CPC)[note 2] is the founding and ruling political party of the People's Republic of China (PRC) and the second largest political party in the world after India's Bharatiya Janata Party. The CPC is the sole governing party within mainland China, permitting only eight other, subordinated parties to co-exist, those making up the United Front. It was founded in 1921, chiefly by Chen Duxiu and Li Dazhao. The party grew quickly, and by 1949 it had driven the Kuomintang (KMT)'s Nationalist Government from mainland China to Taiwan after the Chinese Civil War, leading to the establishment of the People's Republic of China. It also controls the country's armed forces, the People's Liberation Army."),

            HistoricalEvent(9, 1,"Colonial Brazil" , "Colonial Brazil (Portuguese: Brasil Colonial) comprises the period from 1500, with the arrival of the Portuguese, until 1815, when Brazil was elevated to a kingdom in union with Portugal as the United Kingdom of Portugal, Brazil and the Algarves. During the early 300 years of Brazilian colonial history, the economic exploitation of the territory was based first on brazilwood (pau brazil) extraction (16th century), which gave the territory its name;[1] sugar production (16th–18th centuries); and finally on gold and diamond mining (18th century). Slaves, especially those brought from Africa, provided most of the work force of the Brazilian export economy after a brief period of Indian slavery to cut brazilwood." ),
            HistoricalEvent(10, 1,"History of Brazil" ,  "The history of Brazil begins with indigenous people in Brazil. Europeans arrived in Brazil at the opening of the 16th century. The first European to claim sovereignty over Indigenous lands part of what is now the territory of the Federative Republic of Brazil on the continent of South America was Pedro Álvares Cabral (c. 1467/1468 – c. 1520) on April 22, 1500 under the sponsorship of the Kingdom of Portugal. From the 16th to the early 19th century, Brazil was a colony and a part of the Portuguese Empire. The country expanded south along the coast and west along the Amazon and other inland rivers from the original 15 donatary captaincy colonies established on the northeast Atlantic coast east of the Tordesillas Line of 1494 (approximately the 46th meridian west) that divided the Portuguese domain to the east from the Spanish domain to the west, although Brazil was at one time a colony of Spain.[1] The country's borders were only finalized in the early 20th century.")
            )

        repository.initHistory(listHistory)


    }

    private fun initPersonalities(){
        val listPresonality = listOf<Personality>(

            Personality(1,1 , R.raw.pele , "Pelé" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(2,1 , R.raw.ronaldinho , "Ronaldinho" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(3,1 , R.raw.naymar , "Naymar" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),

            Personality(4,2 , R.raw.mao , "Mao Zedong" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(5,2 , R.raw.jinping , "Xi Jinping" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(6,2 , R.raw.qishan , "Wang Qishan" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),

            Personality(7,3 , R.raw.hitler , "Adolf Hitler" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(8,3 , R.raw.eonstein , "Albert Einstein" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(9,3 , R.raw.thomas , "Thomas Müller" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),

            Personality(10,4 , R.raw.mosolini , "Benito Mussolini" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(11,4 , R.raw.bofon , "Gianluigi Buffon" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(12,4 , R.raw.sergio , "Sergio Mattarella" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),

            Personality(13,5 , R.raw.sadat , "Anouar el-Sadate — Wikipédia" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(14,5 , R.raw.jamal , "Gamal Abdel Nasser" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k"),
            Personality(15,5 , R.raw.salah , "Mohamed Salah" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k")


        )
        repository.initPersonality(listPresonality)
    }

    private fun initYoutubeVideos(){
        val  videos = listOf<YoutubeVideo>(
            YoutubeVideo(1 , "https://www.youtube.com/embed/oela7cDoyzY" , 1) ,
            YoutubeVideo(2 , "https://www.youtube.com/embed/sNEeY_gXFBc" , 1) ,
            YoutubeVideo(3 , "https://www.youtube.com/embed/jGFZfqTymEg" , 1) ,
            YoutubeVideo(4 , "https://www.youtube.com/embed/WkveFJ0diSc" , 1) ,

            YoutubeVideo(5 , "https://www.youtube.com/embed/2IqdV5EfByg" , 2) ,
            YoutubeVideo(6 , "https://www.youtube.com/embed/NBVuHhCPFxE" , 2) ,
            YoutubeVideo(7 , "https://www.youtube.com/embed/q29_5MJQbdM" , 2) ,
            YoutubeVideo(8 , "https://www.youtube.com/embed/d_fyOX3L9dw" , 2),

            YoutubeVideo(9 , "https://www.youtube.com/embed/D7e8sv7lFeY" , 3) ,
            YoutubeVideo(10 , "https://www.youtube.com/embed/GwDYOqEJA6Q" , 3) ,
            YoutubeVideo(11, "https://www.youtube.com/embed/IPbzWJNmndY" , 3) ,
            YoutubeVideo(12, "https://www.youtube.com/embed/REUPJITsbDw" , 3) ,

            YoutubeVideo(13 , "https://www.youtube.com/embed/ccX5wmxAL7A" , 4) ,
            YoutubeVideo(14 , "https://www.youtube.com/embed/G_KMybIvv4c" , 4) ,
            YoutubeVideo(15 , "https://www.youtube.com/embed/jo6-0q90DZs" , 4) ,
            YoutubeVideo(16 , "https://www.youtube.com/embed/FlRwssZYRM0" , 4) ,

            YoutubeVideo(17 , "https://www.youtube.com/embed/rsv4drcgHys" , 5) ,
            YoutubeVideo(18 , "https://www.youtube.com/embed/Dtw2vfKihXA" , 5) ,
            YoutubeVideo(19 , "https://www.youtube.com/embed/xt25yBU4Q-w" , 5) ,
            YoutubeVideo(20 , "https://www.youtube.com/embed/YMNnQnqPLa4" , 5)
        )
        repository.initYoutubeVideos(videos)
    }

    private fun initResource(){
        val listResource = listOf<Resource>(
            Resource(1,1,"Mining","Mining in Brazil focuses on a number of minerals including gold, bauxite, diamonds, platinum, iron, tin, coal, and several other minerals"),
            Resource(2,1,"Petroleum","Petroleum is a major natural resource from the country as evidenced by the fact that it is the 12th largest producer of oil in the world"),
            Resource(3,1,"Hydropower","In the whole of South America, Brazil has the largest installed capacity of hydroelectric power production"),

            Resource(4,2,"Fishing and Fish Farming","China has a long tradition of fresh and saltwater fishing and aquaculture. China has become the world's leader in both fishing and aquaculture"),
            Resource(5,2,"Forests and Other Plants","Among other resources of China is the rich and diverse ecosystem. Many rare and unique organisms live in China including the giant panda, golden monkey, white-flag dolphin, metasequoia and the dove tree."),
            Resource(6,2,"Water Resources","The high mountains and powerful rivers of China provide many opportunities for hydroelectric power"),

            Resource(7,3,"Coal","Germany has some of the largest coal reserves in the world, and it leads the world in the production of lignite"),
            Resource(8,3,"Natural Gas","Since 2003, the amount of natural gas produced in Germany has steadily decreased"),
            Resource(9,3,"Timber","Timber is one of the most critical natural resources in Germany with the country ranking fifth in wood exports internationally."),

            Resource(10,4,"Iron and coal","Half of Italy’s iron output comes from the island of Elba, one of the oldest geologic areas"),
            Resource(11,4,"Mineral production","During the late 20th century, production of almost all of Italy’s minerals steadily decreased"),
            Resource(12,4,"Energy","Italy’s lack of energy resources undoubtedly hindered the process of industrialization on the peninsul") ,

            Resource(13,5,"Manufacturing","During the 20th century, manufacturing grew to be one of the largest sectors of Egypt’s economy, accounting (along with mining) for roughly one-fourth of the GDP by the 21st century."),
            Resource(14,5,"Finance","Modern banking activities date from the mid-19th century. The Bank of Egypt opened in 1858 and the Anglo-Egyptian Bank in 1864"),
            Resource(15,5,"Trade","The value of imports into Egypt is usually equal to about one-third and exports about one-tenth of the GDP. ")


        )

        repository.initResources(listResource)
    }


    fun getCountries() {
       repository.getAllCountries()
    }

}
