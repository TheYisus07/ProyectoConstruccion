/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbussinesslogic;

import bussinesslogic.ConstancyDAO;
import bussinesslogic.EventDAO;
import domain.Constancy;
import domain.Event;
import java.sql.Date;
import java.util.ArrayList;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @Antonio de Jesús Dominguez García
 */
public class ConstancyTest {
    
    public ConstancyTest() {
        
    }
    
    @Test
    public void testConstancyByGenerateConstancy(){

        ConstancyDAO constancy = new ConstancyDAO();
        Constancy constancyResult;
        
        Constancy constancyObject = new Constancy("constancy", "nueva constacia", "juan@gmail.com", "luis@gmail.com", "pedro@outlook.com", "nota2", "evento1");
        String recognitionTypeExpected = "constancy";
        constancyResult = constancy.generateConstancy(constancyObject);
        String constancyNew = constancyResult.getRecognitionType();
        
        assertEquals("prueba ingresar una constancia", recognitionTypeExpected, constancyNew);
        
    }
    
    @Test
    public void testConstancyBYGetAllConstancys(){
        
        ConstancyDAO constancy = new ConstancyDAO();
        ArrayList<Constancy> arrayListConstancys;
        arrayListConstancys = constancy.consultConstancyList();
        
        Assert.assertFalse("Prueba regresa lista de constancias", arrayListConstancys.isEmpty());
        
    }
    
    
    @Test
    public void testConstancyByCheckConstancy(){
        
        ConstancyDAO constancy = new ConstancyDAO();
        Constancy constancyResult;
        String constancyExpected = "constancy";
        constancyResult = constancy.checkConstancy(constancyExpected);
        String constancyActual = constancyResult.getRecognitionType();
        assertEquals("Prueba obtener constancia", constancyExpected, constancyActual);
    }
}
