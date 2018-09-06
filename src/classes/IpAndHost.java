/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author abuyog
 */
public class IpAndHost {
    public static String getIpAddress() throws UnknownHostException{
        InetAddress thisIp =  InetAddress.getLocalHost();
                String ip = InetAddress.getLocalHost().toString();
                return ip;
    }
    public static String getHostName() throws UnknownHostException{
        InetAddress thisIp =  InetAddress.getLocalHost();
                String host = thisIp.getHostName();
                return host;
    }
}
