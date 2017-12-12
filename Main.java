import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Main
{
    /** The frame */
    private static LogFrame frame;
    /** The menubar */
    private static JMenuBar menuBar =  new JMenuBar();
    /** The menus */
    private static JMenu fileMenu = new JMenu();
    /** The Menu Items */
    private static JMenuItem setPathMenu = new JMenuItem();
    /** The File Printer */
    private static PrintWriter out;
    /** Path to file */
    private static String path;

    public static void main(String[] args)
    {
        setPathMenu.setText("Load Log File");
        setPathMenu.setToolTipText("Set the log file to write in.");
        setPathMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                    List<String> text = readLogContent();
                    if(text!=null)
                        frame.setContentTextTo(text);

                    frame.repaint();
                }
            }
        });
        fileMenu.setText("File");
        fileMenu.add(setPathMenu);
        menuBar.add(fileMenu);
        frame = new LogFrame();
        frame.setJMenuBar(menuBar);
        frame.notice.setText("Welcome {0}, setting up your logbook now.".replace("{0}", LoggerProperties.prop.getProperty("user")));
        frame.repaint();
        //try to get path from properties.
        path = LoggerProperties.prop.getProperty("path");
        if (path != null && !path.equals(""))
        {
            frame.notice.setText("Found logbook path in: "+ path);
            frame.repaint();
        }
        else
        {
            frame.notice.setText("No path set. Searching for logbook in main directory.");
            frame.repaint();
            path = "logbook.log";
        }
        //add content
        if(readLogContent()!= null)
            frame.setContentTextTo(readLogContent());
        //add listener
        ActionListener logEntryListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = frame.getLogEntry().toString();
                writeToFile(line);
                frame.notice.setText("Log entry added to file.");
                frame.text.setText("");
                frame.addLogEntryToContent(line);
                frame.repaint();
            }
        };
        frame.logEntryButton.addActionListener(logEntryListener);
    }

    /**
     * Write LogLine to the .log file.
     * @param logLine -  the LogLine to be written.
     */
    public static void writeToFile(String logLine)
    {
        try
        {
            FileWriter fw = new FileWriter(path, true);
            out = new PrintWriter(fw);
            out.println(logLine);
        }catch (IOException io)
        {
            frame.notice.setText("Caught an error opening/creating your logbook file. Searched for: " + path);
        }
        finally
        {
            if(out != null) out.close();
        }
    }
    /**
     * Reads the file and returns the content.
     */
    public static List<String> readLogContent()
    {
        List<String> lines = null;
        try
        {
            lines = Files.readAllLines(Paths.get(path.replace( " ", "_")), Charset.defaultCharset());
        }catch (IOException io)
        {
            frame.notice.setText("Caught an error opening/creating your logbook file. Searched for: " + path);
        }
        return lines;
    }


}
