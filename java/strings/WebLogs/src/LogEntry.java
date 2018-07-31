package src;
/**
 * Write a description of class LogRecord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import  java.util.*;
public class LogEntry
{
    private final String ipAddress;
    private final Date accessTime;
    private final String request;
    private final int statusCode;
    private final int bytesReturned;

    public String getIPAddress() { return ipAddress; }
    public Date getAccessTime() { return accessTime; }
    public String getRequest() { return request; }
    public int getStatusCode() { return statusCode; }
    public int getBytesReturned() { return bytesReturned; }
    
    private LogEntry(LogEntryBuilder builder) {
        this.ipAddress = builder.ipAddress;
        this.accessTime = builder.accessTime;
        this.request = builder.request;
        this.statusCode = builder.statusCode;
        this. bytesReturned = builder.bytesReturned;
    }
    
    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }
    
    @Override
    public String toString() {
        return ipAddress + " " + accessTime + " " + request 
        + " " + statusCode + " " + bytesReturned;
    }
    
    public static class LogEntryBuilder {
        private String ipAddress;
        private Date accessTime;
        private String request;
        private int statusCode;
        private int bytesReturned;
        
        public LogEntryBuilder ip(String ip) {
            this.ipAddress = ip;
            return this;
        }
        
        public LogEntryBuilder accessDate(Date date) {
            this.accessTime = date;
            return this;
        }
        
        public LogEntryBuilder request(String req) {
            this.request = req;
            return this;
        }
        
        public LogEntryBuilder status(int status) {
            this.statusCode = status;
            return this;
        }
        
        public LogEntryBuilder bytes(int bytes) {
            this.bytesReturned = bytes;
            return this;
        }
        
        public LogEntry build() {
            return new LogEntry(this);
        }
    }
}
