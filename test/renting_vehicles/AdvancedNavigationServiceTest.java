package renting_vehicles;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.easymock.MockType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
class AdvancedNavigationServiceTest {


    @TestSubject
    private NavigationService navigationService = new NavigationService();
    
    @Mock(MockType.NICE)
    private RealTimePositionService realTimePositionServiceMock;


    @Test
    void testConnectionLoss() {
        PEV pev = new EBike(100, "21c2");
    
        String expectedOutput = "connection lost";
        replay(realTimePositionServiceMock);
    
    
        assertEquals(expectedOutput, navigationService.getInstructions(pev, new Destination(200, 20, "here")));
    
        verify(realTimePositionServiceMock);
    }
    
    //      Remark: make sure to use the verify() functionality
    //              & that the test fails due to a verify error
    @Test
    void testCorrectConnection() {
        PEV pev = new EBike(100, "21c2");
    
        String expectedOutput = "correctly connected";
        expect(realTimePositionServiceMock.getDirection(pev)).andReturn(Direction.SOUTH);
        expect(realTimePositionServiceMock.getX(pev)).andReturn(200);
        expect(realTimePositionServiceMock.getY(pev)).andReturn(20);
        replay(realTimePositionServiceMock);
    
    
        assertEquals(expectedOutput, navigationService.isCorrectlyConnected(pev));
    
        verify(realTimePositionServiceMock);
    }


  
}
