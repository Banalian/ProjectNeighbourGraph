package main.projectneighbourgraph.math;

import main.projectneighbourgraph.graphdata.Node;

public class MathGraph {

    /**
     * Check if a given point is in a circle
     * Note : please give the unit coordinate, not the drawing ones
     * @param pointToCheck the point to check
     * @param circleX the X center position of the circle
     * @param circleY the Y center position of the circle
     * @param circleRadius the radius of the circle
     * @return true if the point is in/on the circle, false otherwise
     */
    public static boolean isPointInCircle(Node pointToCheck, double circleX, double circleY, double circleRadius){

        double xCube = (pointToCheck.getUnitxPos()-circleX)*(pointToCheck.getUnitxPos()-circleX);
        double yCube = (pointToCheck.getUnityPos()-circleY)*(pointToCheck.getUnityPos()-circleY);
        double radiusCube = circleRadius*circleRadius;

        return xCube + yCube <= radiusCube;

    }

    /**
     * Get the absolute length between two points using Pythagoras formula
     * @param point1 the first point to check
     * @param point2 the second one to check
     * @return the length that separates the two points
     */
    public static double lengthBetweenTwoPoints(Node point1, Node point2){

        double xDifference = Math.abs(point1.getUnitxPos() - point2.getUnitxPos());
        double yDifference = Math.abs(point1.getUnityPos() - point2.getUnityPos());

        //Sqrt(a²+b²)=c
        return Math.sqrt((xDifference*xDifference)+(yDifference*yDifference));
    }


    /**
     * Get the absolute length between a point and coordinates using Pythagoras formula
     * Note : please give the unit coords, not the drawing ones
     * @param point the point to check
     * @param xCoord the x coordinate of the coordinates to check
     * @param yCoord the y coordinate of the coordinates to check
     * @return the length that separates the point and the coordinate
     */
    public static double lengthBetweenPointAndCoord(Node point, double xCoord, double yCoord){
        double xDifference = Math.abs(point.getUnitxPos() - xCoord);
        double yDifference = Math.abs(point.getUnityPos() - yCoord);

        //Sqrt(a²+b²)=c
        return Math.sqrt((xDifference*xDifference)+(yDifference*yDifference));
    }


}
