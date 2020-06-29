package com.example.csgomapveto.data

fun getTeams(): List<Team> {
    var teamList = mutableListOf<Team>()

    var TeamA: Team = Team(Name = "NiP")
    var TeamB: Team = Team(Name = "HellRaisers")
    var TeamC: Team = Team(Name = "Astralis")
    var TeamD: Team = Team(Name = "G2 Esports")
    var TeamE: Team = Team(Name = "BIG")
    var TeamF: Team = Team(Name = "GODSENT")
    var Decider: Team = Team(Name = "decider")

    teamList.add(TeamA)
    teamList.add(TeamB)
    teamList.add(TeamC)
    teamList.add(TeamD)
    teamList.add(TeamE)
    teamList.add(TeamF)
    teamList.add(Decider)

    return teamList
}