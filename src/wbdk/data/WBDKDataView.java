package wbdk.data;

/**
 * This type represents an abstraction of what our data manager thinks of in
 * regards to our gui. The point is that we can easily decouple these two by
 * using such a narrow interface.
 *
 * @author Sheryar
 */
public interface WBDKDataView {

    public void reloadDraft(Draft draftToReload);
    public void resetComboBox();
}
