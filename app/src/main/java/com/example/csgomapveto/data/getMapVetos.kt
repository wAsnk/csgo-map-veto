package com.example.csgomapveto.data

fun getMapVetos(teamList: List<Team>): List<MapVeto> {

    var Decider = teamList[6]

    var mapVetoList = mutableListOf<MapVeto>()
    //de_vertigo, de_inferno, de_nuke, de_overpass, de_train, de_mirage, de_dust2, TBD
    mapVetoList.add(
    MapVeto(Name = teamList[0].Name + " vs. " + teamList[1].Name, Team1 = teamList[0].Name, Team2 = teamList[1].Name, VetoList = mutableListOf<Veto>(
        Veto(Number = 1, Team = teamList[0], Map = Map.DE_DUST2, Type = VetoType.BAN),
        Veto(Number = 2, Team = teamList[1], Map = Map.DE_INFERNO, Type = VetoType.BAN),
        Veto(Number = 3, Team = teamList[0], Map = Map.DE_MIRAGE, Type = VetoType.BAN),
        Veto(Number = 4, Team = teamList[1], Map = Map.DE_NUKE, Type = VetoType.BAN),
        Veto(Number = 5, Team = teamList[0], Map = Map.DE_TRAIN, Type = VetoType.PICK),
        Veto(Number = 6, Team = teamList[1], Map = Map.DE_VERTIGO, Type = VetoType.PICK),
        Veto(Number = 7, Team = Decider, Map = Map.DE_OVERPASS, Type = VetoType.PICK)
    )
    ))


    mapVetoList.add(
    MapVeto(Name = teamList[2].Name + " vs. " + teamList[3].Name, Team1 = teamList[2].Name, Team2 = teamList[3].Name, VetoList = mutableListOf<Veto>(
        Veto(Number = 1, Team = teamList[2], Map = Map.DE_DUST2, Type = VetoType.BAN),
        Veto(Number = 2, Team = teamList[3], Map = Map.DE_INFERNO, Type = VetoType.BAN),
        Veto(Number = 3, Team = teamList[2], Map = Map.DE_MIRAGE, Type = VetoType.BAN),
        Veto(Number = 4, Team = teamList[3], Map = Map.DE_NUKE, Type = VetoType.BAN),
        Veto(Number = 5, Team = teamList[2], Map = Map.DE_TRAIN, Type = VetoType.PICK),
        Veto(Number = 6, Team = teamList[3], Map = Map.DE_VERTIGO, Type = VetoType.PICK),
        Veto(Number = 7, Team = Decider, Map = Map.DE_OVERPASS, Type = VetoType.PICK)
    )))

    mapVetoList.add(
    MapVeto(Name = teamList[4].Name + " vs. " + teamList[5].Name, Team1 = teamList[4].Name, Team2 = teamList[5].Name, VetoList = mutableListOf<Veto>(
        Veto(Number = 1, Team = teamList[4], Map = Map.DE_DUST2, Type = VetoType.BAN),
        Veto(Number = 2, Team = teamList[5], Map = Map.DE_INFERNO, Type = VetoType.BAN),
        Veto(Number = 3, Team = teamList[4], Map = Map.DE_MIRAGE, Type = VetoType.BAN),
        Veto(Number = 4, Team = teamList[5], Map = Map.DE_NUKE, Type = VetoType.BAN),
        Veto(Number = 5, Team = teamList[4], Map = Map.DE_TRAIN, Type = VetoType.PICK),
        Veto(Number = 6, Team = teamList[5], Map = Map.DE_VERTIGO, Type = VetoType.PICK),
        Veto(Number = 7, Team = Decider, Map = Map.DE_OVERPASS, Type = VetoType.PICK)
    )))

    mapVetoList.add(
    MapVeto(Name = teamList[1].Name + " vs. " + teamList[4].Name,Team1 = teamList[1].Name, Team2 = teamList[4].Name, VetoList = mutableListOf<Veto>(
        Veto(Number = 1, Team = teamList[1], Map = Map.DE_DUST2, Type = VetoType.BAN),
        Veto(Number = 2, Team = teamList[4], Map = Map.DE_INFERNO, Type = VetoType.BAN),
        Veto(Number = 3, Team = teamList[1], Map = Map.DE_MIRAGE, Type = VetoType.BAN),
        Veto(Number = 4, Team = teamList[4], Map = Map.DE_NUKE, Type = VetoType.BAN),
        Veto(Number = 5, Team = teamList[1], Map = Map.DE_TRAIN, Type = VetoType.PICK),
        Veto(Number = 6, Team = teamList[4], Map = Map.DE_VERTIGO, Type = VetoType.PICK),
        Veto(Number = 7, Team = Decider, Map = Map.DE_OVERPASS, Type = VetoType.PICK)
    )
    ))

    return mapVetoList
}