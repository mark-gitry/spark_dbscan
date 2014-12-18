package org.alitouka.spark.dbscan.exploratoryAnalysis

import org.alitouka.spark.dbscan.SuiteBase
import org.alitouka.spark.dbscan.spatial.Point

/**
 * @author mpgeraty
 *
 */
class PointWithDistanceToNNearestNeighborsSuite extends SuiteBase {

  test ("PointWithDistanceToNNearestNeighbors should work properly") {
    val pt = new PointWithDistanceToNNearestNeighbors(new Point(), 3)
    
    pt.n should be (3)
    
    val distances : List[Double] = List(0.9, 0.2, 0.1, 0.8, 0.6, 0.3, 0.5, 0.4, 0.7)
    
    for (i <- 0 to distances.length-1) {
      pt.considerDistance(distances(i))
    }
    
    pt.distanceToNthNearestNeighbor should be (0.3)
    
    pt.nShortestDistances.length should be (3)
    
    pt.nShortestDistances should equal (List(0.1, 0.2, 0.3))
  }
}