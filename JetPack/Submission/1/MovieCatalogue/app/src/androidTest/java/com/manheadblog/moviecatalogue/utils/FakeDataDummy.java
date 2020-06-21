package com.manheadblog.moviecatalogue.utils;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.ArrayList;

public class FakeDataDummy {
    private static ArrayList<Movie> arrayListMovies = new ArrayList<>();
    private static ArrayList<TVShow> arrayListTVShow = new ArrayList<>();

    public static ArrayList<Movie> generateMovies(){
        if (arrayListMovies.size()<1) {
            // Empty data generate new data
            arrayListMovies.add(new Movie(
                    1,
                    "Aquaman",
                    "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\'s half-human, half-Atlantean brother and true heir to the throne.\n",
                    68,
                    "English",
                    "7 December 2018",
                    R.drawable.movie_poster_aquaman
            ));
            arrayListMovies.add(new Movie(
                    2,
                    "Avengers: Infinity War",
                    "spot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                    83,
                    "English",
                    "25 April 2018",
                    R.drawable.movie_poster_infinity_war
            ));
            arrayListMovies.add(new Movie(
                    3,
                    "Bohemian Rhapsody",
                    "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \'n\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                    81,
                    "English",
                    "24 October 2013",
                    R.drawable.movie_poster_bohemian
            ));
            arrayListMovies.add(new Movie(
                    4,
                    "Cold Pursuit",
                    "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son\'s murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking\'s associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                    54,
                    "English",
                    "7 February 2019",
                    R.drawable.movie_poster_cold_persuit
            ));
            arrayListMovies.add(new Movie(
                    5,
                    "Creed II",
                    "Between personal obligations and training for his next big fight against an opponent with ties to his family\'s past, Adonis Creed is up against the challenge of his life.",
                    67,
                    "English",
                    "21 Movember 2018",
                    R.drawable.movie_poster_creed
            ));
            arrayListMovies.add(new Movie(
                    6,
                    "Glass",
                    "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four ",
                    65,
                    "English",
                    "16 January 2019",
                    R.drawable.movie_poster_glass
            ));
            arrayListMovies.add(new Movie(
                    7,
                    "How to Train Your Dragon: The Hidden World",
                    "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home ",
                    76,
                    "English",
                    "22 Februty 2019",
                    R.drawable.movie_poster_how_to_train
            ));
            arrayListMovies.add(new Movie(
                    8,
                    "Mary Queen of Scots",
                    "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because ",
                    66,
                    "English",
                    "15 November 2018",
                    R.drawable.movie_poster_marry_queen
            ));
            arrayListMovies.add(new Movie(
                    9,
                    "Master Z: IP Man Legacy",
                    "Following his defeat by Master Ip, Cheung Tin Chi (Zhang) tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it s not long ",
                    52,
                    "Cantonese",
                    "12 April 2019",
                    R.drawable.movie_poster_master_z
            ));
            arrayListMovies.add(new Movie(
                    10,
                    "Mortal Engines",
                    "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these ",
                    60,
                    "English",
                    "29 November 2018",
                    R.drawable.movie_poster_mortal_engines
            ));
            arrayListMovies.add(new Movie(
                    11,
                    "Overlord",
                    "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small ",
                    66,
                    "English",
                    "22 September 2018",
                    R.drawable.movie_poster_overlord
            ));
            arrayListMovies.add(new Movie(
                    12,
                    "Ralph Breaks The Internet",
                    "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope\'s ",
                    72,
                    "English",
                    "05 November 2018",
                    R.drawable.movie_poster_ralph
            ));
        }
        return arrayListMovies;
    }
    public static ArrayList<TVShow> generateTVShows(){
        if (arrayListTVShow.size()<1){
            arrayListTVShow.add(new TVShow(
                    1,
                    "Arrow",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    58,
                    "English",
                    R.drawable.tvshow_poster_arrow
            ));
            arrayListTVShow.add(new TVShow(
                    2,
                    "Doom Patrol",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the ",
                    62,
                    "English",
                    R.drawable.tvshow_poster_doom_patrol
            ));
            arrayListTVShow.add(new TVShow(
                    3,
                    "DragonBall",
                    "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.",
                    70,
                    "Japanese",
                    R.drawable.tvshow_poster_dragon_ball
            ));
            arrayListTVShow.add(new TVShow(
                    4,
                    "Fairy Tail",
                    "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                    64,
                    "Japanese",
                    R.drawable.tvshow_poster_fairytail
            ));
            arrayListTVShow.add(new TVShow(
                    5,
                    "Family Guy",
                    "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                    65,
                    "English",
                    R.drawable.tvshow_poster_family_guy
            ));
            arrayListTVShow.add(new TVShow(
                    6,
                    "Flash",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    67,
                    "English",
                    R.drawable.tvshow_poster_flash
            ));
            arrayListTVShow.add(new TVShow(
                    7,
                    "Game of Thrones",
                    "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                    81,
                    "English",
                    R.drawable.tvshow_poster_got
            ));
            arrayListTVShow.add(new TVShow(
                    8,
                    "Gotham",
                    "Before there was Batman, there was GOTHAM. Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                    69,
                    "English",
                    R.drawable.tvshow_poster_gotham
            ));
            arrayListTVShow.add(new TVShow(
                    9,
                    "Grey\'s Anatomy",
                    "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                    63,
                    "English",
                    R.drawable.tvshow_poster_grey_anatomy
            ));
            arrayListTVShow.add(new TVShow(
                    10,
                    "Hanna",
                    "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                    65,
                    "English",
                    R.drawable.tvshow_poster_hanna
            ));
            arrayListTVShow.add(new TVShow(
                    11,
                    "Marvel\'s Iron Fist",
                    "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                    61,
                    "English",
                    R.drawable.tvshow_poster_iron_fist
            ));
            arrayListTVShow.add(new TVShow(
                    12,
                    "Naruto Shippuden",
                    "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                    75,
                    "Japanese",
                    R.drawable.tvshow_poster_naruto_shipudden
            ));
        }
        return arrayListTVShow;
    }

    public static Movie getMovieByPosition(int position){
        return generateMovies().get(position);
    }
}
