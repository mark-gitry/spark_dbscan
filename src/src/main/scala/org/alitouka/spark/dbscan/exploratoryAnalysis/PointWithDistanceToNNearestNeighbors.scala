package org.alitouka.spark.dbscan.exploratoryAnalysis

import org.alitouka.spark.dbscan.spatial.Point

/**
 * Point that tracks the distance to its nth nearest neighbors.
 */
private [dbscan] class PointWithDistanceToNNearestNeighbors (pt: Point, val n: Int = 12) extends  Point (pt) {
  var distanceToNthNearestNeighbor = Double.MaxValue 
  var nShortestDistances : List[Double] = List.fill(n)(Double.MaxValue)
  
  def considerDistance(d: Double) = {
    if (d < distanceToNthNearestNeighbor) {
      nShortestDistances = d :: nShortestDistances
      
      nShortestDistances = nShortestDistances.sorted
      
      if (nShortestDistances.size > n) {
        nShortestDistances = nShortestDistances.slice(0, n)
      }
      
      distanceToNthNearestNeighbor = nShortestDistances(n-1)
    }
  }
}