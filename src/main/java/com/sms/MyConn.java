package com.sms;

import oracle.jdbc.OracleConnectionBuilder;
import oracle.jdbc.datasource.OracleDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyConn {
    public void MyConnection() {
        OracleDataSource dataSource = null;
        try {
            dataSource = new OracleDataSource() {
                @Override
                public OracleConnectionBuilder createConnectionBuilder() throws SQLException {
                    return null;
                }

                @Override
                public Connection getConnection() throws SQLException {
                    return null;
                }

                @Override
                public Connection getConnection(String username, String password) throws SQLException {
                    return null;
                }

                @Override
                public <T> T unwrap(Class<T> iface) throws SQLException {
                    return null;
                }

                @Override
                public boolean isWrapperFor(Class<?> iface) throws SQLException {
                    return false;
                }

                @Override
                public void setDataSourceName(String s) throws SQLException {

                }

                @Override
                public String getDataSourceName() {
                    return null;
                }

                @Override
                public String getDatabaseName() {
                    return null;
                }

                @Override
                public void setDatabaseName(String s) throws SQLException {

                }

                @Override
                public void setServerName(String s) throws SQLException {

                }

                @Override
                public String getServerName() {
                    return null;
                }

                @Override
                public void setURL(String s) throws SQLException {

                }

                @Override
                public String getURL() throws SQLException {
                    return null;
                }

                @Override
                public void setUser(String s) throws SQLException {

                }

                @Override
                public String getUser() {
                    return null;
                }

                @Override
                public void setPassword(String s) throws SQLException {

                }

                @Override
                public String getDescription() {
                    return null;
                }

                @Override
                public void setDescription(String s) throws SQLException {

                }

                @Override
                public String getNetworkProtocol() {
                    return null;
                }

                @Override
                public void setNetworkProtocol(String s) throws SQLException {

                }

                @Override
                public void setPortNumber(int i) throws SQLException {

                }

                @Override
                public int getPortNumber() {
                    return 0;
                }

                @Override
                public void setConnectionProperties(Properties properties) throws SQLException {

                }

                @Override
                public Properties getConnectionProperties() throws SQLException {
                    return null;
                }

                @Override
                public void setMaxStatements(int i) throws SQLException {

                }

                @Override
                public int getMaxStatements() throws SQLException {
                    return 0;
                }

                @Override
                public void setImplicitCachingEnabled(boolean b) throws SQLException {

                }

                @Override
                public boolean getImplicitCachingEnabled() throws SQLException {
                    return false;
                }

                @Override
                public void setExplicitCachingEnabled(boolean b) throws SQLException {

                }

                @Override
                public boolean getExplicitCachingEnabled() throws SQLException {
                    return false;
                }

                @Override
                public void setRoleName(String s) throws SQLException {

                }

                @Override
                public String getRoleName() {
                    return null;
                }

                @Override
                public PrintWriter getLogWriter() throws SQLException {
                    return null;
                }

                @Override
                public void setLogWriter(PrintWriter out) throws SQLException {

                }

                @Override
                public void setLoginTimeout(int seconds) throws SQLException {

                }

                @Override
                public int getLoginTimeout() throws SQLException {
                    return 0;
                }

                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            };
            dataSource.setURL("jdbc:oracle:thin:@localhost:1521/eis");  // Database URL (replace with your actual DB URL)
            dataSource.setUser("dbuser");  // Set your DB username
            dataSource.setPassword("dbuser");  // Set your DB password

            OracleConnectionBuilder connectionBuilder = dataSource.createConnectionBuilder();
            Connection connection = connectionBuilder.build();
            System.out.println("Connected to the database!");
            connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
