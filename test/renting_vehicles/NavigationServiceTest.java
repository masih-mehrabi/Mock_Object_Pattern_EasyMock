package renting_vehicles;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
class NavigationServiceTest {
    
    @TestSubject
    private NavigationService navigationService = new NavigationService();
    
    
    
    @Mock(MockType.STRICT)
    private RealTimePositionService realTimePositionServiceMock;
    

    @Test
    void testDestinationReached() {
        PEV pev = new EBike(100, "21c2");
        
        String expectedOutput = "destination reached";
        expect(realTimePositionServiceMock.getX(pev)).andReturn(200);
        expect(realTimePositionServiceMock.getY(pev)).andReturn(20);
        expect(realTimePositionServiceMock.getDirection(pev)).andReturn(Direction.NORTH);
        replay(realTimePositionServiceMock);
    
    
        assertEquals(expectedOutput, navigationService.getInstructions(pev, new Destination(200, 20, "here")));
    
        verify(realTimePositionServiceMock);
    }
    
    @Test
    void testDirectionDistance() {
        PEV pev = new EBike(100, "21c2");
    
        String expectedOutput = "drive south for 10 more kilometers";
        expect(realTimePositionServiceMock.getDirection(pev)).andReturn(Direction.SOUTH);
        expect(realTimePositionServiceMock.getX(pev)).andReturn(200);
        expect(realTimePositionServiceMock.getY(pev)).andReturn(20);
        replay(realTimePositionServiceMock);
    
    
        assertEquals(expectedOutput, navigationService.getDirectionDistance(pev, new Destination(200, 10, "here")));
    
        verify(realTimePositionServiceMock);
    }
    
}
