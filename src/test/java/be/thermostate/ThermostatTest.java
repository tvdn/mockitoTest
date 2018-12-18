//package be.thermostate;
//
//import be.ThermostatStubs.Heating;
//import be.ThermostatStubs.Sensor;
//import be.ThermostatStubs.Thermostat;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.function.Executable;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.Duration;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ThermostatTest {
//    private final static int INTERVAL = 10;
//
//    @Mock
//    private Heating heatingMock;
//    @Mock
//    private Sensor sensorMock;
//    @InjectMocks
//    private Thermostat thermostat;
//
//    @Test
//    public void testThermostat1() {
//
//    }
//
//
//
//    @Test
//    public void testThermostat()  throws InterruptedException {
//        thermostat.setTargetTemperature(new Temperature(20));
//        sensorMock.setTemp(15);
//        sensorMock.setcalled(false);
//        heatingMock.setHeating(false);
//        Thread.sleep(INTERVAL * 3);
//        assertTrue(thermostat.isHeating());
//        assertTrue(sensorMock.isCalled());
//        assertTrue(heatingMock.isHeating());
//    }
//
//    @Test
//    public void testChangeCurrent() throws InterruptedException {
//        sensorMock.setTemp(0);
//        Temperature finalTemp = new Temperature(30);
//        thermostat.setTargetTemperature(finalTemp);
//        thermostat.start();
//        Thread.sleep(INTERVAL * 3);
//        assertTrue(thermostat.isHeating());
//        sensorMock.setTemp(sensorMock.getTemperature().getValue() + 10);
//        assertTrue(thermostat.isHeating());
//    }
//
//   @Test
//    public void testChangeTarget() throws InterruptedException {
//        sensorMock.setTemp(30);
//        float temp = 10;
//        thermostat.setTargetTemperature(new Temperature(temp));
//        thermostat.start();
//        Thread.sleep(INTERVAL *3);
//
//        assertTrue(!thermostat.isHeating());
//        thermostat.setTargetTemperature(new Temperature(temp += 20));
//        assertTrue(!thermostat.isHeating());
//
//   }
//
//    @RepeatedTest(value=10)
//    public void testThermostatInterval(){
//        thermostat.setTargetTemperature(new Temperature(20));
//        thermostat.setInterval(10);
//        heatingMock.setHeating(false);
//        sensorMock.setTemp(15);
//        assertTimeoutPreemptively(Duration.ofMillis(20), new Executable() {
//            @Override
//            public void execute() throws InterruptedException {
//                while (!heatingMock.isHeating()) Thread.sleep(1);
//            }
//        });
//    }
//
//
//    @ParameterizedTest
//    @CsvFileSource(resources="/data.csv")
//    @Tag("parameterized")
//    @DisplayName("parameterized")
//    public void paramTest(float target, float current, boolean status) throws InterruptedException {
//        thermostat.setTargetTemperature(new Temperature(target));
//        sensorMock.setTemp(current);
//        Thread.sleep(INTERVAL * 3);
//        assertEquals(status, thermostat.isHeating());
//    }
//
//}
