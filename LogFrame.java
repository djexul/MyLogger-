import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogFrame extends JFrame
{

    public final JPanel mainPanel = new JPanel();
    /** Buttons Panel. */
    public final JPanel buttons = new JPanel();
    /** Text area for existing log content. */
    public final JTextArea content = new JTextArea(10, 50);
    /** Scrollable panel for existing content. */
    public final JScrollPane existingContentScrollPane = new JScrollPane(content);
    /** Text area for input text content. */
    public final JTextArea text = new JTextArea(5,50);
    /** The message area where notices appear. */
    public final JTextField notice = new JTextField("Notices and messages will appear here.",50);
    /** Type of the LogEntry. */
    public final JComboBox<LogType> typeBox= new JComboBox(LogType.values());
    /** The button to add a new Entry. */
    public final Button logEntryButton;





    /** Check to see if the frame is running. */


    public boolean isRunning;

    /** Default frame sizes. */
    private int dWidth = 840;
    private int dHeight = 620;
    /**
     * Create a new LogFrame.
     */
    public LogFrame ()
    {
        //FRAME SETUP
        super("Logbook Keeper");
        isRunning = true;
        this.setBackground(Color.DARK_GRAY);
        this.setSize(new Dimension(dWidth, dHeight));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //LAYOUT SETUP
        LayoutManager layoutManager = new BorderLayout();
        this.setLayout(layoutManager);

        //TEXTAREA SETUP
        content.setForeground(Color.BLACK);
        content.setLineWrap(true);
        content.setBackground(Color.LIGHT_GRAY);
        content.setToolTipText("Log content.");
        content.setEditable(false);
        //content.setPreferredSize(new Dimension((int)(this.getWidth() * 0.88),(int)(this.getHeight() * 0.6)));
        existingContentScrollPane.setPreferredSize(new Dimension((int)(this.getWidth() * 0.9),(int)(this.getHeight() * 0.61)));
        text.setForeground(Color.BLACK);
        text.setLineWrap(true);
        text.setBackground(Color.WHITE);
        text.setSelectedTextColor(Color.LIGHT_GRAY);
        text.setToolTipText("Enter the logbook entry here.");
        text.setPreferredSize(new Dimension((int)(this.getWidth() * 0.75),(int)(this.getHeight() * 0.2)));

        //SCROLLPANEL SETUP
        existingContentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        existingContentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //TEXTFIELD SETUP
        notice.setToolTipText("System messages are displayed here.");
        notice.setEditable(false);

        //COMBOBOX SETUP
        typeBox.setToolTipText("Choose the type of the logbook entry.");

        //BUTTONS SETUP
        logEntryButton = new Button("ADD ENTRY");
        Button exitButton = new Button("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                System.exit(0);
            }
        });

        //PANEL SETUP
        mainPanel.setLayout(new BorderLayout(10, 5));
        mainPanel.add(existingContentScrollPane, BorderLayout.NORTH);
        mainPanel.add(typeBox, BorderLayout.EAST);
        mainPanel.add(text, BorderLayout.WEST);
        mainPanel.add(notice, BorderLayout.SOUTH);
        buttons.setLayout(new FlowLayout());
        buttons.add(logEntryButton);
        buttons.add(exitButton);

        //ADD TO FRAME
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setFocusable(true);

    }

    /** Creates a new LogEntry via the input in the boxes and returns it. */
    public LogEntry getLogEntry(){return new LogEntry(text.getText(),(LogType)typeBox.getSelectedItem());}
    /** Adds a new LogEntry line to the content text area.*/
    public void addLogEntryToContent( String line){content.setText(content.getText() + "\n" + line);}
    /**
     * Sets the whole content of the content text area.
     * Clears any existing text.
     */
    public void setContentTextTo( java.util.List<String> tblob)
    {
        content.setText("");
        for(String line: tblob)
            addLogEntryToContent(line);
    }

}

