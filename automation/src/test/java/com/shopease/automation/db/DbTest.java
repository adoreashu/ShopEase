package com.shopease.automation.db;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.*;

public class DbTest {
    @Test
    public void testDatabaseConnectionAndUsers() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:../backend/data/shopease;MODE=MySQL;AUTO_SERVER=TRUE", "sa", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM users");
        rs.next();
        int count = rs.getInt(1);
        Assert.assertTrue(count > 0, "Users should exist in DB");
        conn.close();
    }
}
