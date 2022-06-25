package mao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Project name(项目名称)：端口扫描器
 * Package(包名): mao
 * Class(类名): Scan
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 20:39
 * Version(版本): 1.0
 * Description(描述)： 无
 */
public class Scan
{
    /**
     * The constant frame.
     */
    //顶层窗体
    public static JFrame frame = new JFrame("端口扫描器");

    /**
     * The constant Result.
     */
    //显示扫描结果
    public static JTextArea Result = new JTextArea("", 11, 55);
    /**
     * The constant resultPane.
     */
    //滚动条面板
    public static JScrollPane resultPane = new JScrollPane(Result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    /**
     * The constant hostname.
     */
    //输入主机名文本框
    public static JTextField hostname = new JTextField("localhost", 10);

    /**
     * The constant fromIp1.
     */
    //输入ip地址前3位的输入框
    public static JTextField fromIp1 = new JTextField("127", 6);
    /**
     * The constant fromIp2.
     */
    //输入ip地址4~6位的输入框
    public static JTextField fromIp2 = new JTextField("0", 6);
    /**
     * The constant fromIp3.
     */
    //输入ip地址7~9位的输入框
    public static JTextField fromIp3 = new JTextField("0", 6);
    /**
     * The constant fromIp4.
     */
    //输入起始ip地址最后4位的输入框
    public static JTextField fromIp4 = new JTextField("1", 6);
    /**
     * The constant toIp.
     */
    //输入目标ip地址最后4位的输入框
    public static JTextField toIp = new JTextField("1", 6);

    /**
     * The constant minPort.
     */
    //输入最小端口的输入框
    public static JTextField minPort = new JTextField("0", 8);
    /**
     * The constant maxPort.
     */
    //输入最大端口的输入框
    public static JTextField maxPort = new JTextField("65535", 6);
    /**
     * The constant maxThread.
     */
    //输入最大线程数量的输入框
    public static JTextField maxThread = new JTextField("200", 6);

    /**
     * The constant DLGError.
     */
    //错误提示框
    public static JDialog DLGError = new JDialog(frame, "错误!");
    /**
     * The constant DLGINFO.
     */
    public static JLabel DLGINFO = new JLabel("");

    /**
     * The constant type.
     */
    public static JLabel type = new JLabel("请选择：");

    /**
     * The constant radioIp.
     */
    //扫描类型
    public static JRadioButton radioIp = new JRadioButton("IP地址：");
    /**
     * The constant radioHost.
     */
    public static JRadioButton radioHost = new JRadioButton("主机名：", true);
    /**
     * The constant group.
     */
    //单选框组
    public static ButtonGroup group = new ButtonGroup();

    /**
     * The constant P1.
     */
    public static JLabel P1 = new JLabel("端口范围:");
    /**
     * The constant P2.
     */
    public static JLabel P2 = new JLabel("~");
    /**
     * The constant P3.
     */
    public static JLabel P3 = new JLabel("~");
    /**
     * The constant Pdot1.
     */
    public static JLabel Pdot1 = new JLabel(".");
    /**
     * The constant Pdot2.
     */
    public static JLabel Pdot2 = new JLabel(".");
    /**
     * The constant Pdot3.
     */
    public static JLabel Pdot3 = new JLabel(".");
    public static JLabel TNUM = new JLabel("线程数:");
    public static JLabel RST = new JLabel("扫描结果:");

    //定义按钮
    public static JButton OK = new JButton("确定");
    public static JButton Submit = new JButton("开始扫描");
    public static JButton Cancel = new JButton("退出");
    public static JButton saveButton = new JButton("保存扫描结果");

    //菜单栏
    public static JMenuBar menuBar = new JMenuBar();
    public static JMenu menu = new JMenu("文件(F)");
    public static JMenuItem saveItem = new JMenuItem("保存扫描结果(S)");
    public static JMenuItem exitItem = new JMenuItem("退出(Q)");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {

        frame.setSize(1270, 720);
        //获取屏幕宽度
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        //获取屏幕高度
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        //位于屏幕中央
        frame.setLocation(screenWidth / 2 - frame.getWidth() / 2, screenHeight / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //用户调整大小
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DLGError.setSize(600, 200);
        DLGError.setLocation(screenWidth / 2 - 600 / 2, screenHeight / 2 - 200 / 2);


        //设置字体、颜色等个性化设置
        Font font = new Font("宋体", Font.BOLD, 24);
        Result.setForeground(Color.MAGENTA);
        Result.setFont(font);
        Result.setSelectionColor(Color.green);
        hostname.setFont(font);
        fromIp1.setFont(font);
        fromIp2.setFont(font);
        fromIp3.setFont(font);
        fromIp4.setFont(font);
        toIp.setFont(font);
        minPort.setFont(font);
        maxPort.setFont(font);
        maxThread.setFont(font);
        radioIp.setFont(font);
        radioHost.setFont(font);
        P1.setFont(font);
        P2.setFont(font);
        P3.setFont(font);
        Pdot1.setFont(font);
        Pdot2.setFont(font);
        Pdot3.setFont(font);
        TNUM.setFont(font);
        RST.setFont(font);
        OK.setFont(font);
        OK.setBackground(Color.green);
        Submit.setFont(font);
        Submit.setBackground(Color.GREEN);
        Cancel.setFont(font);
        Cancel.setBackground(Color.red);
        saveButton.setFont(font);
        saveButton.setBackground(Color.green);
        DLGINFO.setFont(font);


        saveItem.setBackground(Color.cyan);
        exitItem.setBackground(Color.red);
        //添加“菜单栏”
        menu.add(saveItem);
        menu.add(exitItem);


        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //设置热键
        menu.setMnemonic('F');
        saveItem.setMnemonic('S');
        //为“另存为”组件设置快捷键为ctrl+s
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        exitItem.setMnemonic('Q');
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        //采用表格包型布局
        Container mPanel = frame.getContentPane();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 0, 0, 10);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(type, gridBagConstraints);

        group.add(radioIp);
        group.add(radioHost);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(radioIp, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromIp1, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(Pdot1, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromIp2, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(Pdot2, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromIp3, gridBagConstraints);

        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(Pdot3, gridBagConstraints);

        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromIp4, gridBagConstraints);

        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(P2, gridBagConstraints);

        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(toIp, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(radioHost, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(hostname, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(P1, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(minPort, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(P3, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(maxPort, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(TNUM, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(maxThread, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(Submit, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(saveButton, gridBagConstraints);

        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(Cancel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(RST, gridBagConstraints);

        //设置文本区域可以换行
        Result.setLineWrap(true);
        //设置文本区域不可编辑
        Result.setEditable(false);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mPanel.add(resultPane, gridBagConstraints);

        Container dPanel = DLGError.getContentPane();
        dPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dPanel.add(DLGINFO);
        dPanel.add(OK);

        Submit.addActionListener(new SubmitAction());
        Cancel.addActionListener(new CancleAction());
        OK.addActionListener(new OKAction());

        //实现保存功能
        saveItem.addActionListener(e ->
        {
            JFileChooser jFileChooser = new JFileChooser();
            int returnVal = jFileChooser.showSaveDialog(null);

            //点击“保存”
            if (returnVal == 0)
            {
                //获取文件对象
                File saveFile = jFileChooser.getSelectedFile();
                FileWriter writeOut = null;
                try
                {
                    //写
                    writeOut = new FileWriter(saveFile);
                    writeOut.write(Scan.Result.getText());

                }
                catch (IOException ex)
                {
                    //System.out.println("保存失败");
                    JOptionPane.showMessageDialog(null, "保存失败！", "提示", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    try
                    {
                        if (writeOut != null)
                        {
                            writeOut.close();
                        }
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            //点击“取消”
        });

        //实现退出功能
        exitItem.addActionListener(e -> System.exit(0));


        frame.setVisible(true);
    }
}


/**
 * 实现“取消”功能
 * 退出程序
 */
class CancleAction implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}

/**
 * 实现“确定”功能
 * 完成扫描
 */
class SubmitAction implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent a)
    {

        int minPort;
        int maxPort;
        int maxThread;

        int ip1 = 0;
        int ip2 = 0;
        int ip3 = 0;
        int ipstart = 0;
        int ipend = 0;

        String ipaddress = "";
        String hostname = "";

        Scan.Result.setText("");
        //将"确定"按钮设置成为不可用
        if (Scan.Submit.isEnabled())
        {
            Scan.Submit.setEnabled(false);
        }

        /*
         *判断搜索的类型
         *按照ip地址扫描：type = 0
         *按照主机名称扫描：type = 1
         */
        if (Scan.radioIp.isSelected())
        {

            TCPThread.type = 0;

            //判断ip的前3位是否为int型
            try
            {
                ip1 = Integer.parseInt(Scan.fromIp1.getText());
            }
            catch (NumberFormatException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的ip!\n位于第一个框", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("错误的ip!");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }

            //判断ip的4~6位是否为int型
            try
            {
                ip2 = Integer.parseInt(Scan.fromIp2.getText());
            }
            catch (NumberFormatException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的ip!\n位于第二个框", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("错误的ip!");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }

            //判断ip的7~9位是否为int型
            try
            {
                ip3 = Integer.parseInt(Scan.fromIp3.getText());
            }
            catch (NumberFormatException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的ip!\n位于第三个框", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("错误的ip!");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }

            //判断起始ip的最后4位是否为int型
            try
            {
                ipstart = Integer.parseInt(Scan.fromIp4.getText());
            }
            catch (NumberFormatException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的ip!\n位于第四个框", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("错误的ip!");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }

            //判断目标ip的最后4位是否为int型
            try
            {
                ipend = Integer.parseInt(Scan.toIp.getText());
            }
            catch (NumberFormatException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的ip!\n位于第五个框", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("错误的目标ip!");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }

            //判断起始ip是否正确
            //判断条件：大于0且小于等于255
            if (ip1 < 0 || ip1 > 255 || ip2 < 0 || ip2 > 255 || ip3 < 0 || ip3 > 255 || ipstart < 0 || ipstart > 255)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "ip地址应为0-255的整数!", "错误!", JOptionPane.ERROR_MESSAGE);
                //Scan.DLGINFO.setText("                    ip地址为0-255的整数!                    ");
                //Scan.DLGError.setVisible(true);
                Scan.Submit.setEnabled(true);
                return;
            }
            else
            {
                TCPThread.ip1 = ip1;
                TCPThread.ip2 = ip2;
                TCPThread.ip3 = ip3;
                TCPThread.ipStart = ipstart;
            }

            //判断目标ip是否正确
            //判断条件：大于0且小于等于255
            if (ipend < 0 || ipend > 255)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "目标ip地址为0-255的整数!", "错误!", JOptionPane.ERROR_MESSAGE);
                Scan.Submit.setEnabled(true);
                //Scan.DLGINFO.setText("                    目标ip地址为0-255的整数!                    ");
                //Scan.DLGError.setVisible(true);
                return;
            }
            else
            {
                TCPThread.ipEnd = ipend;
            }

            ipaddress = "" + ip1 + ip2 + ip3 + ipstart;

            /*
             *判断ip地址的有效性
             */
            try
            {
                TCPThread.hostAddress = InetAddress.getByName(ipaddress);
            }
            catch (UnknownHostException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的IP或地址不可达!", "错误!", JOptionPane.ERROR_MESSAGE);
                Scan.Submit.setEnabled(true);
                //Scan.DLGINFO.setText("            错误的IP或地址不可达!            ");
                //Scan.DLGError.setVisible(true);
                return;
            }
        }

        //根据主机名进行端口扫描
        if (Scan.radioHost.isSelected())
        {

            TCPThread.type = 1;

            /*
             *判断主机名称的有效性
             */
            try
            {
                TCPThread.hostAddress = InetAddress.getByName(Scan.hostname.getText());
            }
            catch (UnknownHostException e)
            {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "错误的域名或地址不可达!", "错误!", JOptionPane.ERROR_MESSAGE);
                Scan.Submit.setEnabled(true);
                //Scan.DLGINFO.setText("            错误的域名或地址不可达!            ");
                //Scan.DLGError.setVisible(true);
                return;
            }
        }

        /*
         *判断端口号的有效性
         */
        try
        {
            minPort = Integer.parseInt(Scan.minPort.getText());
            maxPort = Integer.parseInt(Scan.maxPort.getText());
            maxThread = Integer.parseInt(Scan.maxThread.getText());
        }
        catch (NumberFormatException e)
        {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "错误的端口号或线程数!端口号和线程数必须为整数!", "错误!", JOptionPane.ERROR_MESSAGE);
            Scan.Submit.setEnabled(true);
            //Scan.DLGINFO.setText("错误的端口号或线程数!端口号和线程数必须为整数!");
            //Scan.DLGError.setVisible(true);
            return;
        }

        /*
         *判断最小端口号的有效范围
         *判断条件：大于0且小于65535，最大端口应大于最小端口
         */
        if (minPort < 0 || minPort > 65535 || minPort > maxPort)
        {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "最小端口必须是0-65535并且小于最大端口的整数!", "错误!", JOptionPane.ERROR_MESSAGE);
            Scan.Submit.setEnabled(true);
            //Scan.DLGINFO.setText("最小端口必须是0-65535并且小于最大端口的整数!");
            //Scan.DLGError.setVisible(true);
            return;
        }
        else
        {
            TCPThread.MIN_port = minPort;
        }

        /*
         *判断最大端口号的有效范围
         *判断条件：大于0且小于65535，最大端口应大于最小端口
         */
        if (maxPort > 65535)
        {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "最大端口必须是0-65535并且大于最小端口的整数!", "错误!", JOptionPane.ERROR_MESSAGE);
            Scan.Submit.setEnabled(true);
            //Scan.DLGINFO.setText("最大端口必须是0-65535并且大于最小端口的整数!");
            //Scan.DLGError.setVisible(true);
            return;
        }
        else
        {
            TCPThread.MAX_port = maxPort;
        }

        /*
         *判断线程数量的有效范围
         *判断条件：大于1且小于200
         */
        if (maxThread < 1 || maxThread > 200)
        {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "线程数应为1-200的整数!!", "错误!", JOptionPane.ERROR_MESSAGE);
            Scan.Submit.setEnabled(true);
            //Scan.DLGINFO.setText("                    线程数为1-200的整数!                    ");
            //Scan.DLGError.setVisible(true);
            return;
        }

        Scan.Result.append("线程数 " + Scan.maxThread.getText() + "\n-------------\n");

        //启动线程
        for (int i = 0; i < maxThread; i++)
        {
            new TCPThread("T" + i, i).start();
        }
    }
}

/**
 * 实现错误提示框中的“确定”按钮功能
 */
class OKAction implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Scan.DLGError.dispose();
    }
}
