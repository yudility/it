package it.demo.route;

import it.demo.point.Point;

public class Route {
    public Point start;
    public Point destination;

    public int times;

    public String vectors; // 배열로 받아야 됨 (HOW?)
    public int priority;

    public Route(Point start, Point destination) {
        this.start = start;
        this.destination = destination;
    }


}
