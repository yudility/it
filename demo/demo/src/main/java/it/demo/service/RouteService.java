package it.demo.service;

import it.demo.point.Point;
import it.demo.repository.PointRepository;
import it.demo.route.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final PointRepository pointRepository;

    public Route find(Point start, Point destination) {
        //여기서 경로 계산하는 로직 필요.
        Route route = new Route(start, destination); //이경우에는 무조건 있어야함
        return route;
    }
}
