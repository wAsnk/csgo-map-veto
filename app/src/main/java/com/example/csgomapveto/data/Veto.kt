package com.example.csgomapveto.data

data class Veto (
    var Number: Int,
    var Team: Team,
    var Map: Map = com.example.csgomapveto.data.Map.TBD,
    var Type: VetoType = VetoType.TBD
)