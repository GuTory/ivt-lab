package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

public class GT4500Test {

    private final TorpedoStore primary = Mockito.mock(TorpedoStore.class);
    private final TorpedoStore secondary = Mockito.mock(TorpedoStore.class);
    private GT4500 ship;

    @BeforeEach
    public void init() {

        when(primary.fire(1)).thenReturn(true);
        when(secondary.fire(1)).thenReturn(true);

        this.ship = new GT4500(primary, secondary);
    }

    @Test
    public void fireTorpedo_Single_Success() {
        // Arrange
        when(primary.isEmpty()).thenReturn(false);
        when(secondary.isEmpty()).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_All_Success() {
        // Arrange
        when(primary.isEmpty()).thenReturn(false);
        when(secondary.isEmpty()).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_Empty_Failure() {
        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertEquals(false, result);
        verify(primary, times(1)).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, times(0)).fire(1);
        verify(secondary, times(0)).fire(1);
        verify(secondary, times(0)).fire(5);
    }

    @Test
    public void fireTorpedo_First_Empty_Success() {
        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertEquals(true, result);

        verify(primary, times(1)).isEmpty();
        verify(secondary, times(1)).isEmpty();
        verify(primary, times(0)).fire(1);
        verify(secondary, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_Second_Empty_Success() {
        when(primary.isEmpty()).thenReturn(false);
        when(secondary.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertEquals(true, result);
    }

    @Test
    public void fireTorpedo_Fire_Fails() {
        when(primary.fire(1)).thenReturn(false);
        when(secondary.fire(1)).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertEquals(false, result);
    }

    @Test
    public void fireTorpedo_PrimaryWasFiredLast_Secondary_Fails_First_Success() {
        ship.setWasPrimaryFiredLast(true);
        when(secondary.isEmpty()).thenReturn(true);
        when(primary.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertEquals(true, ship.isWasPrimaryFiredLast());
    }

    @Test
    public void fireTorpedo_PrimaryWasFiredLast_Secondary_Success() {
        ship.setWasPrimaryFiredLast(true);
        when(secondary.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertEquals(true, result);
        assertEquals(false, ship.isWasPrimaryFiredLast());
    }

    @Test
    public void fireTorpedo_PrimaryWasFiredLast_Secondary_Fails_First_Fails(){
        ship.setWasPrimaryFiredLast(true);
        when(secondary.isEmpty()).thenReturn(true);
        when(primary.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertEquals(false, result);
    }

    @Test
    public void fireTorpedo_PrimaryFiredLast(){
        when(primary.isEmpty()).thenReturn(false);

        ship.fireTorpedo(FiringMode.SINGLE);

        assertEquals(true, ship.isWasPrimaryFiredLast());
    }

    @Test
    public void fireLaser(){
        assertEquals(false, ship.fireLaser(FiringMode.ALL));
    }


    @Test
    public void fireTorpedo_Single(){
        ship.setWasPrimaryFiredLast(false);
        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);


        assertEquals(true, result);
        assertEquals(false, ship.isWasPrimaryFiredLast());
    }

    @Test
    public void fireTorpedo_Single_All_Fail(){
        ship.setWasPrimaryFiredLast(false);
        when(primary.isEmpty()).thenReturn(true);
        when(secondary.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);


        assertEquals(false, result);
    }

    @Test
    public void FireTorpedo_Bad_Mode(){
        boolean result = ship.fireTorpedo(FiringMode.MOCK);

        assertEquals(false, result);
    }

}
