/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbussinesslogic;

import bussinesslogic.EventDAO;
import domain.Event;
import java.sql.Date;
import java.util.ArrayList;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Antonio de Jesús Dominguez García
 *
 */
public class EventTest {
    
    public EventTest(){
        
    }
    
    @Test
    public void testEventByScheduleEvent(){

        EventDAO event = new EventDAO();
        Event eventResult;
        
        Date registrationDate = new Date((2021-1900), (3-1), 27);
        Date eventDate = new Date((2021-1900), (4-1), 03);
        Event eventObject = new Event("evento2", "tipo", registrationDate, "xalapa", eventDate, "si", "Jaime");
        String tittleEventExpected = "evento2";
        eventResult = event.scheduleEvent(eventObject);
        String eventNew = eventResult.getTittle();
        
        assertEquals("prueba ingresar un cuerpo academico", tittleEventExpected, eventNew);
        
    }
    
    @Test
    public void testEventByCheckEventHistory(){
        
        EventDAO event = new EventDAO();
        ArrayList<Event> arrayListEvents;
        arrayListEvents = event.checkEventHistory();
        
        Assert.assertFalse("Prueba regresa lista de eventos", arrayListEvents.isEmpty());
        
    }
    
    @Test
    public void testEventByConsultEvent(){
        
        EventDAO event = new EventDAO();
        Event eventResult;
        String eventExpected = "evento1";
        eventResult = event.consultEvent(eventExpected);
        String eventActual = eventResult.getTittle();
        assertEquals("Prueba obtener evento", eventExpected, eventActual);
    }
    
    @Test
    public void testEventByModifyEvent(){
        
        EventDAO event = new EventDAO();
        Event eventResult;
        
        Date registrationDate = new Date((2021-1900), (3-1), 27);
        Date eventDate = new Date((2021-1900), (4-1), 03);
        Event eventObjectNew = new Event("evento2", "tipo", registrationDate, "xalapa", eventDate, "si", "Jaime");
        Event eventObjectOld = new Event("evento3", "evento", registrationDate, "coatepec", eventDate, "No", "Jaime");
        String tittleEventExpected = "evento2";
        eventResult = event.modifyEvent(eventObjectNew,eventObjectOld);
        String eventNew = eventResult.getTittle();
        
        assertEquals("prueba ingresar un cuerpo academico", tittleEventExpected, eventNew);
        
    }
    
}
