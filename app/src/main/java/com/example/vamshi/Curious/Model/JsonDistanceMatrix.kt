package com.example.vamshi.Curious.Model

import java.util.ArrayList

internal data class JsonDistanceMatrix(var destinationAddresses: ArrayList<String>,
                                       var originAddresses: ArrayList<String>,
                                       var rows: ArrayList<Row>,
                                       var status: String)

internal data class Row(var elements: ArrayList<Element>)

internal data class Element(var distance: Distance,
                            var duration: Duration,
                            var status: String)

internal data class Duration(var text: String,
                             var value: Int)

internal data class Distance(var text: String,
                             var value: Int)
