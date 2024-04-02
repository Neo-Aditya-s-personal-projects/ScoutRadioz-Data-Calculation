package data;

import calcs.Team;

public class AttendingTeamsData {
    static Team[] teams = {
            new Team(125, "NUTRONs"),
            new Team(6329, "The Bucks' Wrath"),
            new Team(176, "Aces High"),
            new Team(5687, "The Outliers"),
            new Team(177, "Bobcat Robotics"),
            new Team(6328, "Mechanical Advantage"),
            new Team(8085, "MOJO"),
            new Team(78, "AIR STRIKE"),
            new Team(1058, "PVC Pirates"),
            new Team(133, "B.E.R.T."),
            new Team(1768, "Nashoba Robotics"),
            new Team(1153, "Timberwolves"),
            new Team(8013, "Boston Lions"),
            new Team(3467, "Windham Windup"),
            new Team(4048, "Redshift"),
            new Team(2067, "Apple Pi"),
            new Team(175, "Buzz Robotics"),
            new Team(9443, "Aluminum Panthers"),
            new Team(88, "TJ^2"),
            new Team(5422, "Stormgears FRC"),
            new Team(166, "Chop Shop"),
            new Team(1699, "Robocats"),
            new Team(195, "CyberKnights"),
            new Team(58, "The Riot Crew"),
            new Team(5735, "Control Freaks"),
            new Team(172, "Northern Force"),
            new Team(2877, "LigerBots"),
            new Team(190, "Gompei and the H.E.R.D."),
            new Team(230, "Gaelhawks"),
            new Team(3464, "Sim-City"),
            new Team(1922, "Oz-Ram"),
            new Team(131, "CHAOS"),
            new Team(999, "MechaRAMS"),
            new Team(7407, "Wired Boars"),
            new Team(558, "Elm City Robo Squad"),
            new Team(5813, "Morpheus"),
            new Team(2713, "Red Hawk Robotics"),
            new Team(2370, "IBOTS"),
            new Team(6731, "Record Robotics"),
            new Team(4909, "Bionics"),
            new Team(4564, "Orange Chaos"),
            new Team(1729, "Team Inconceivable!"),
            new Team(4905, "Andromeda One"),
            new Team(5112, "The Gongoliers"),
            new Team(246, "Lobstah Bots"),
            new Team(8410, "Oyster River Overdrive 8410"),
            new Team(2079, "4H ALARM Robotics"),
            new Team(6933, "Archytas"),
            new Team(5142, "Robo Dominators"),
            new Team(319, "Big Bad Bob"),
            new Team(2648, "Infinite Loop"),
            new Team(1350, "The Rambots"),
            new Team(9732, "The Rainbow Junkyard"),
            new Team(509, "Red Storm"),
            new Team(1100, "Inverse Polarity"),
            new Team(8709, "Pathfinder Tech"),
            new Team(716, "Who'sCTEKS"),
            new Team(3461, "Operation PEACCE Robotics"),
            new Team(9644, "NEIA Robotics"),
            new Team(1474, "Titans"),
            new Team(4546, "Shockwave"),
            new Team(2168, "Aluminum Falcons"),
            new Team(95, "Grasshoppers"),
            new Team(1071, "Team MAX"),
            new Team(2423, "The KwarQs"),
            new Team(663, "Power Sharks"),
            new Team(3182, "Athena's Warriors"),
            new Team(238, "Crusaders"),
            new Team(839, "Rosie Robotics"),
            new Team(4041, "Iron Tigers"),
            new Team(4473, "Delta Prime Robotics 4-H"),
            new Team(138, "Entropy"),
            new Team(5962, "perSEVERE"),
            new Team(2876, "DevilBotz"),
            new Team(5459, "Ipswich TIGERS"),
            new Team(4176, "Iron Tigers"),
            new Team(6346, "Cybears Robotics Team"),
            new Team(7913, "'Bear'ly Functioning"),
            new Team(9729, "Knights"),
            new Team(173, "RAGE Robotics"),
            new Team(8626, "Cyber Sailors"),
            new Team(236, "Techno-Ticks"),
            new Team(9101, "Green Mountain Robotics"),
            new Team(1991, "The Dragons"),
            new Team(126, "Gael Force"),
            new Team(467, "Colonials"),
            new Team(3146, "GRANBY GRUNTS"),
            new Team(155, "The TechnoNuts"),
            new Team(9710, "Discovery PolyBotz"),
            new Team(1740, "Cyber Colonels"),
            new Team(8046, "LakerBots"),
            new Team(8544, "Reinforcement"),
            new Team(1119, "Pup Cups"),
            new Team(8708, "Ov3R1y K0Mp13X"),
            new Team(1757, "Wolverines"),
            new Team(3654, "TechTigers")
    };

    public static boolean isValidNumber(int teamNumber) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getTeamNumber() == teamNumber)
                return true;
        }
        return false;
    }

    public static void appendData(int teamNumber, String role, int speakerNotes, int ampNotes, int autoNotes,
            int notesPassed, int trapNotes, boolean climbed) {
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
                index++;
            }
        }
        return validTeams;
    }
}
