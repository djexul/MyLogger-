
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class LogEntry
{
    /** Timestamp of the logentry. */
    private final Date timestamp;
    /** Content of the entry. */
    private final String text;
    /** The type of the entry. */
    private final LogType type;
    /** Formatter for the Date obj. */
    private static DateFormat format;
    //static block for format assignment.
    static
    {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK);
    }

    /**
     * Create a new LogbookKeeper.LogEntry with the given params.
     *
     * @param text - content of the entry.
     * @param type - the type of the entry.
     */
    public LogEntry(String text, LogType type)
    {
        this.text = text;
        this.type = type;
        timestamp = new Date();
    }

    /**
     * {@inheritDoc}
     * @return -string representation of the Entry.
     */
    @Override
    public String toString()
    {
        String entry = "[ {0} ]".replace("{0}",format.format(timestamp) )+ "[ {0} ]".replace("{0}", type.toString()) + text;

        return entry;
    }


}
