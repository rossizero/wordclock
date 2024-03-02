package com.rossi.myclockfull

class WorldDays {
    companion object {
        fun getInformation(day: Int, month: Int): Pair<Int, String>? {
            when (month) {
                1 -> { // January
                    when (day) {
                        1 -> return Pair(R.raw.braille, "World Braille Day")
                        17 -> return Pair(R.raw.italia, " International Italian Food Day")
                        24 -> return Pair(R.raw.education, "World Day of Education")
                        26 -> return Pair(R.raw.clean_energy, "World Day of Clean Energy")
                    }
                }

                2 -> { // February
                    when (day) {
                        4 -> return Pair(R.raw.cancer, "World Cancer Day")
                        11 -> return Pair(R.raw.women_scientists, "International Day of Women and Girls in Science")
                        12 -> return Pair(R.raw.darwin, "Darwin Day")
                        13 -> return Pair(R.raw.radio, "World Radio Day")
                    }
                }

                3 -> { // March
                    when (day) {
                        1 -> return Pair(R.raw.zero_discrimination, "Zero Discrimination Day")
                        3 -> return Pair(R.raw.wildlife_day, "World Wildlife Day")
                        8 -> return Pair(R.raw.womens_day, "International Women's Day")
                        20 -> return Pair(R.raw.happiness, "International Day of Happiness")
                        21 -> return Pair(R.raw.tree, "International Day of Forests ")
                        22 -> return Pair(R.raw.water, "World Water Day")
                        30 -> return Pair(R.raw.zero_waste, "International Day of Zero Waste")
                    }
                }

                4 -> { // April
                    when (day) {
                        2 -> return Pair(R.raw.autism, "World Autism Awareness Day")
                        5 -> return Pair(R.raw.conscience, "International Day of Conscience ")
                        6 -> return Pair(R.raw.ace, "International Asexuality Day (IAD)")
                        7 -> return Pair(R.raw.health, "World Health Day")
                        12 -> return Pair(R.raw.human_space_flight, "International Day of Human Space Flight")
                        20 -> return Pair(R.raw.four_twenty, "International day of smoking pot!")
                        22 -> return Pair(R.raw.earth_day, "International Mother Earth Day ")
                        25 -> return Pair(R.raw.malaria, "World Malaria Day")
                        30 -> return Pair(R.raw.jazz, "International Jazz Day")
                    }
                }

                5 -> { // May
                    when (day) {
                        3 -> return Pair(R.raw.press, "World Press Freedom Day")
                        8 -> return Pair(R.raw.red_cross, "World Red Cross and Red Crescent Day ")
                        17 -> return Pair(R.raw.telecommunication, "World Telecommunication and Information Society Day")
                        20 -> return Pair(R.raw.bee, "World Bee Day")
                        31 -> return Pair(R.raw.no_smoke, "World No-Tobacco Day")
                    }
                }

                6 -> { // June
                    when (day) {
                        3 -> return Pair(R.raw.bicycle, "World Bicycle Day")
                        8 -> return Pair(R.raw.oceans, "World Oceans Day")
                        14 -> return Pair(R.raw.blood, "World Blood Donor Day")
                        21 -> return Pair(R.raw.yoga, "International Day of Yoga")
                        28 -> return Pair(R.raw.pride, "Pride Day")
                        29 -> return Pair(R.raw.papst_nico, "Papst Nico 420. hat die Welt erkannt! Der Rest schwebt zwischen Pluto und der Weltformel..")
                        30 -> return Pair(R.raw.asteriod, "International Asteroid Day")
                    }
                }

                7 -> { // July
                    when (day) {
                        11 -> return Pair(R.raw.population, "World Population Day")
                        20 -> return Pair(R.raw.chess, "World Chess Day")
                        30 -> return Pair(R.raw.friendship, "International Day of Friendship")
                    }
                }

                8 -> { // August
                    when (day) {
                        8 -> return Pair(R.raw.cat, "International Cat Day")
                        19 -> return Pair(R.raw.humanitarian, "World Humanitarian Day")
                        26 -> return Pair(R.raw.dog, "International Dog Day")
                        29 -> return Pair(R.raw.nuke_ban, "International Day against Nuclear Tests")
                    }
                }

                9 -> { // September
                    when (day) {
                        15 -> return Pair(R.raw.democracy, "International Day of Democracy")
                        21 -> return Pair(R.raw.peace, "International Day of Peace")
                        27 -> return Pair(R.raw.tourism, "World Tourism Day")
                    }
                }

                10 -> { // October
                    when (day) {
                        1 -> return Pair(R.raw.older_persons, "International Day of Older Persons")
                        5 -> return Pair(R.raw.teacher, "World Teachers’ Day")
                        13 -> return Pair(R.raw.universalgenie, "Some random birthday")
                        16 -> return Pair(R.raw.food, "World Food Day")
                        31 -> return Pair(R.raw.halloween, "Halloween")
                    }
                }

                11 -> { // November
                    when (day) {
                        1 -> return Pair(R.raw.vegan, "World Vegan Day")
                        9 -> return Pair(R.raw.science, "International Week of Science and Peace,")
                        19 -> return Pair(R.raw.toilet, "World Toilet Day")
                        21 -> return Pair(R.raw.television, "World Television Day")
                    }
                }
                12 -> { // December
                    when (day) {
                        1 -> return Pair(R.raw.aids, "World AIDS Day")
                        3 -> return Pair(R.raw.disabled, "International Day of Persons with Disabilities")
                        5 -> return Pair(R.raw.soil, "World Soil Day")
                        10 -> return Pair(R.raw.human_rights, "Human Rights Day")
                        11 -> return Pair(R.raw.mountain, "International Mountain Day")
                        24 -> return Pair(R.raw.christmas, "Christmas (at least in Germany). Merry Christmas if you celebrate it! If not I hope you have a good day :)")
                        27 -> return Pair(R.raw.epidemic, "International Day of Epidemic Preparedness")
                        31 -> return Pair(R.raw.new_years_eve, "New years eve! Happy new year!")
                    }
                }
            }
            return null
        }
    }
}