
public enum LogType
{
    /**
     * Was doing research, including reading information,
     * watching tutorial videos or looking for
     * references;
     */
    RESEARCH,
    /**
     * Anything that has to do with actual coding work.
     * This includs writing tests or adding content, but not
     * creating content.
     */
    CODING,
    /**
     * Anything to to with game design or creating content
     * (story, characters, voicelines, dialogue, enemies, etc.)
     * for the game.
     */
    DESIGNandCONTENT,
    /**
     * Used to keep track of any debugging work.
     * This includes any code changes or any changes made to try and
     * locate the errors and problems that appear in the game.
     */
    DEBUGGING,
    /**
     * Any information I deem useful to note down in the log.
     * This is not to be confused with OTHER, which is non-important junk.
     */
    NOTE,
    /**
     * Anything related to testing the project.
     * Includes running tests and fixing bugs, but not writing tests.
     * Testplaying also counts.
     */
    TESTING,
    /**
     * This is for the Planning stage.
     * Use this entry to write down your iteration planning in the logbook.
     */
    PLANNING,
    /**
     * Any non-important/ non-formal notes.
     * These may not be related to the project at all, or anything that does not
     * fit any of the other categories.
     */
    OTHER;

}
