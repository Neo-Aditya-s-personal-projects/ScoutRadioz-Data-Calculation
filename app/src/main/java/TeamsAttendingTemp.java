public class TeamsAttendingTemp {
    static Team[] teams = {
        new Team(69, "Hyper"),
        new Team(151, "Tough Techs"),
        new Team(157, "Aztechs"),
        new Team(190, "Gompei"),
        new Team(237, "Black Magic"),
        new Team(348, "Norwell"),
        new Team(1027, "Mechatronic Maniacs"),
        new Team(1100, "Inverse Polarity"),
        new Team(1757, "Wolverines"),
        new Team(1768, "Nashoba Robotics"),
        new Team(1768, "Nashoba Robotics"),
        new Team(2079, "4H ALARM Robotics"),
        new Team(2168, "Aluminum Falcons"),
        new Team(2262, "Robo-Panthers"),
        new Team(2370, "IBOTS"),
        new Team(2712, "Power Surge 4-H Robotics"),
        new Team(3205, "Patriots"),
        new Team(3323, "Potential Energy"),
        new Team(3623, "The Robomingos"),
        new Team(4048, "Redshift"),
        new Team(4987, "MegaRams"),
        new Team(5000, "Hammerheads"),
        new Team(5347, "Gryphons"),
        new Team(541, "Hard Reset"),
        new Team(5735, "Control Freaks"),
        new Team(5962, "Persevere"),
        new Team(6333, "Scavenger Robotics"),
        new Team(6620, "The Northmengineers"),
        new Team(6731, "Record Robotics"),
        new Team(6933, "Archytas"),
        new Team(7760, "Griffin Innovations"),
        new Team(8013, "Boston Lions"),
        new Team(8544, "Reinorcement"),
        new Team(8604, "Alpha Centuri"),
        new Team(9056, "CyberHuskies 9056"),
        new Team(9101, "Green Mountain Robotics"),
        new Team(9644, "NEIA Robotics")
    };

    public static boolean isValidNumber(int teamNumber) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getTeamNumber() == teamNumber) return true;
        }
        return false;
    }

    public static void appendData(int teamNumber, String role, int speakerNotes, int ampNotes, int autoNotes, int notesPassed, int trapNotes, boolean climbed) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getTeamNumber() == teamNumber) {
                teams[i].appendData(role, speakerNotes, ampNotes, autoNotes, notesPassed, trapNotes, climbed);
            }
        }
    }

    public static Team[] getTeams() {
        int lengthOfArray = 0;

        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getTotalMatches() > 0) {
                lengthOfArray++;
            }
        }

        Team[] validTeams = new Team[lengthOfArray];
        int index = 0;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getTotalMatches() > 0) {
                validTeams[index] = teams[i];
            }
        }
        return validTeams;
    }
}
