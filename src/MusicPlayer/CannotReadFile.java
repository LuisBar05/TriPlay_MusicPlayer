package MusicPlayer;

import java.io.IOException;

/**
 *
 * @author Luis
 */

/** Minor changes (meh) 
 * //You don't really need that "Not supported yet" messages, don't you...
 * @author Renata (@BalbyReny)
 */

class CannotReadFile extends Exception {

    CannotReadFile(String localizedMessage) {}

    CannotReadFile(IOException ex) {}

}
