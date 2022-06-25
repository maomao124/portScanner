package mao;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Project name(项目名称)：端口扫描器
 * Package(包名): mao
 * Class(类名): TCPThread
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 20:43
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class TCPThread extends Thread
{
    public static InetAddress hostAddress;

    //最小的端口号
    public static int MIN_port;
    //最大的端口号
    public static int MAX_port;

    //线程总数
    private final int threadNum;

    //查询方式：0为ip；1为主机名
    public static int type;

    //ip地址前3位
    public static int ip1;
    //ip地址4~6位
    public static int ip2;
    //ip地址7~9位
    public static int ip3;
    //起始ip地址的最后4位
    public static int ipStart;
    //结束ip地址的最后4位
    public static int ipEnd;
    //完整的ip地址
    public static String ipAll;

    //扫描的主机名称或ip
    String hostname = "";
    //端口的类别
    String portType = "0";

    /*
     *构造函数
     */
    public TCPThread(String name, int threadNum)
    {
        super(name);
        this.threadNum = threadNum;
    }

    /*
     *运行函数
     */
    public void run()
    {

        //ip地址
        int host = 0;
        //端口号
        int port = 0;
        //套接字
        Socket theTCPSocket;

        //根据ip地址进行扫描
        if (type == 0)
        {

            //ip地址循环扫描
            for (host = ipStart; host <= ipEnd; host++)
            {

                //组成完整的ip地址
                ipAll = "" + ip1 + "." + ip2 + "." + ip3 + "." + host;
                hostname = ipAll;

                try
                {
                    //在给定主机名的情况下确定主机的 IP 地址
                    hostAddress = InetAddress.getByName(ipAll);
                }
                catch (UnknownHostException ignored)
                {
                }

                //不同的端口循环扫描
                for (port = MIN_port + threadNum; port < MAX_port + Integer.parseInt(Scan.maxThread.getText()); port += Integer.parseInt(Scan.maxThread.getText()))
                {

                    try
                    {
                        if (port > 65535)
                        {
                            continue;
                        }
                        //没有会抛异常
                        theTCPSocket = new Socket(hostAddress, port);
                        theTCPSocket.close();
                        Scan.Result.append(hostname + ":" + port);

                        //判断端口的类别
                        switch (port)
                        {
                            case 21:
                                portType = "(FTP)";
                                break;
                            case 23:
                                portType = "(TELNET)";
                                break;
                            case 25:
                                portType = "(SMTP)";
                                break;
                            case 80:
                                portType = "(HTTP)";
                                break;
                            case 110:
                                portType = "(POP)";
                                break;
                            case 139:
                                portType = "(netBIOS)";
                                break;
                            case 1433:
                                portType = "(SQL Server)";
                                break;
                            case 3306:
                                portType = "(MYSQL)";
                                break;
                            case 3389:
                                portType = "(Terminal Service)";
                                break;
                            case 6379:
                                portType = "(Redis)";
                                break;
                            case 443:
                                portType = "(HTTPS)";
                                break;
                            case 8080:
                                portType = "(Tomcat)";
                                break;
                            case 5601:
                                portType = "(kibana)";
                                break;
                            case 9200:
                                portType = "(elasticSearch)";
                                break;
                            case 1521:
                                portType = "(Oracle)";
                                break;
                            case 5672:
                                portType = "(RabbitMQ)";
                                break;
                            case 8066:
                                portType = "(MyCat)";
                                break;
                            case 15672:
                                portType = "(RabbitMQ admin)";
                                break;

                        }

                        //端口没有特定类别
                        if (portType.equals("0"))
                        {
                            Scan.Result.append("\n");
                        }
                        else
                        {
                            Scan.Result.append(":" + portType + "\n");
                        }
                    }
                    catch (IOException ignored)
                    {
                    }
                }
            }

            //扫描完成后，显示扫描完成，并将“确定”按钮设置为可用
            if (port == MAX_port + Integer.parseInt(Scan.maxThread.getText()))
            {
                Scan.Result.append("\n" + "扫描完成...");

                //将"确定"按钮设置成为可用
                if (!Scan.Submit.isEnabled())
                {
                    Scan.Submit.setEnabled(true);
                }
            }
        }

        //按照主机名进行端口扫描
        if (type == 1)
        {

            for (port = MIN_port + threadNum; port < MAX_port + Integer.parseInt(Scan.maxThread.getText()); port += Integer.parseInt(Scan.maxThread.getText()))
            {

                try
                {
                    if (port > 65535)
                    {
                        continue;
                    }
                    theTCPSocket = new Socket(hostAddress, port);
                    theTCPSocket.close();
                    Scan.Result.append(" " + port);
                    switch (port)
                    {
                        case 21:
                            portType = "(FTP)";
                            break;
                        case 23:
                            portType = "(TELNET)";
                            break;
                        case 25:
                            portType = "(SMTP)";
                            break;
                        case 80:
                            portType = "(HTTP)";
                            break;
                        case 110:
                            portType = "(POP)";
                            break;
                        case 139:
                            portType = "(netBIOS)";
                            break;
                        case 1433:
                            portType = "(SQL Server)";
                            break;
                        case 3306:
                            portType = "(MYSQL)";
                            break;
                        case 3389:
                            portType = "(Terminal Service)";
                            break;
                        case 6379:
                            portType = "(Redis)";
                            break;
                        case 443:
                            portType = "(HTTPS)";
                            break;
                        case 8080:
                            portType = "(Tomcat)";
                            break;
                        case 5601:
                            portType = "(kibana)";
                            break;
                        case 9200:
                            portType = "(elasticSearch)";
                            break;
                        case 1521:
                            portType = "(Oracle)";
                            break;
                        case 5672:
                            portType = "(RabbitMQ)";
                            break;
                        case 8066:
                            portType = "(MyCat)";
                            break;
                        case 15672:
                            portType = "(RabbitMQ admin)";
                            break;
                    }

                    //端口没有特定类别
                    if (portType.equals("0"))
                    {
                        Scan.Result.append("\n");
                    }
                    else
                    {
                        Scan.Result.append(":" + portType + "\n");
                    }
                }
                catch (IOException ignored)
                {
                }
            }

            //扫描完成后，显示扫描完成，并将【确定】按钮设置为可用
            if (port == MAX_port + Integer.parseInt(Scan.maxThread.getText()))
            {
                Scan.Result.append("\n---------\n扫描完成...");

                //将【确定】按钮设置成为可用
                if (!Scan.Submit.isEnabled())
                {
                    Scan.Submit.setEnabled(true);
                }
            }
        }
    }
}
