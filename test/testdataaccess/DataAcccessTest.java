/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdataaccess;

import java.sql.Connection;
import java.sql.SQLException;

import dataaccess.Conection;

import org.junit.Test;
import org.junit.Assert;
        
/**
 *
 * @author Antonio de Jesús Dominguez García
 *
 */
public class DataAcccessTest {
    
    @Test
    public void DataBaseConnectionTest() throws SQLException{
        Connection currentConnection=new Conection().getConnection();
        Assert.assertNotNull(currentConnection);
    }
    
    @Test
    public void setConnectionTest() throws SQLException {
        Connection currentConnection = new Conection().getConnection();
        Conection connection = new Conection();
        connection.setConnection(currentConnection);
        Assert.assertNotNull(connection);
    }

    @Test
    public void connect() throws SQLException{
        Conection connection = new Conection();
        connection.connect();
        Assert.assertNotNull(connection);
    }

    @Test
    public void disconnect() throws SQLException {
        Conection connection = new Conection();
        connection.disconnect();
        Assert.assertFalse(false);
    }
    
}
