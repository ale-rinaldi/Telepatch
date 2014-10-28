package org.telepatch.messenger;

/**
 * this is our Notifications Manager, that will replace NotificationsController.
 */

public class NotificationsManager implements NotificationsManagerSoul{
    private static NotificationsManager instance;
    private int last_notify_id;
    //TODO
    /* l'idea e' questa: crei un qualche cosa tipo una mappa notify_id + pila messaggi (in array)
       poi quando vai a mostrare la notifica semplicemente carichi l'array. Easy.
     */

    /**
     * constructor
     */
    public NotificationsManager() {

    }

    /**
     * singleton
     * @return NotificationsManager the instance
     */
    public static NotificationsManager getInstance() {
        if (instance == null) {
                instance = new NotificationsManager();
        }
        return instance;
    }

    /**
     * creates or updates a notification with ID = notify_id
     * @param notify_id
     */
    public void createOrUpdateNotification(int notify_id) {
        last_notify_id = notify_id;

    }

    /**
     * prepares text to be shown in notification.
     * @return
     */
    public String prepareNotificationText() {
        return "";
    }

    /**
     * to be evaluated
     */
    public void retrieveAvatar() {

    }

    /**
     * getter for last_notify_id
     * @return last_notify_id the last notification's ID
     */
    public int getLastNotificationID() {
        return last_notify_id;
    }

    /**
     * getter to retrieve latest message received and that should be displayed in the stack of a notification
     * @param notify_id
     * @return String last message
     */
    public String getLastMessageForNotifyID(int notify_id) {
        return "";
    }

    /**
     * setter to add a message in a stack of messages for a notification
     * @param notify_id
     */
    public void addMessageToStackForNotifyID(int notify_id) {


    }

    //public *decidi tu cosa preferisci che gestisca la stack dei messaggi* getMessagesStackList() {}

    /**
     * removes a messages stack (list in notification) for notify_id
     * @param notify_id
     */
    public void removeMessagesStackForNotifyID(int notify_id) {
    }






}