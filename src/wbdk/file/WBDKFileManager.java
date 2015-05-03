package wbdk.file;

import java.io.IOException;
import wbdk.data.Draft;

/**
 * This interface provides an abstraction of what a file manager should do. Note
 * that file managers know how to read pitchers and hitters json file,
 *
 * @author Sheryar
 */
public interface WBDKFileManager {

    public void loadHitters(Draft playerToLoad, String hittersPath) throws IOException;
    public void loadPitchers(Draft playerToLoad, String hittersPath) throws IOException;
    public void saveDraft(Draft draftToSave) throws IOException;
    public void loadDraft(Draft draftToLoad, String draftPath) throws IOException;
}
