package renting_vehicles;

public interface RealTimePositionService {
    int getX(PEV pev);

    int getY(PEV pev);

    Direction getDirection(PEV pev);
}
