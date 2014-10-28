package org.telepatch.messenger;

/**
 * this is our Notifications Manager, that will replace NotificationsController.
 */

public interface NotificationsManagerSoul {

    /**
     * creates or updates a notification with ID = notify_id
     * @param notify_id
     */
    public void createOrUpdateNotification(int notify_id);

    /**
     * prepares text to be shown in notification.
     * @return
     */
    public String prepareNotificationText();

    /**
     * to be evaluated
     */
    public void retrieveAvatar();

    /**
     * getter for last_notify_id
     * @return last_notify_id the last notification's ID
     */
    public int getLastNotificationID();

    /**
     * getter to retrieve latest message received and that should be displayed in the stack of a notification
     * @param notify_id
     * @return String last message
     */
    public String getLastMessageForNotifyID(int notify_id);

    /**
     * setter to add a message in a stack of messages for a notification
     * @param notify_id
     */
    public void addMessageToStackForNotifyID(int notify_id);

    //public *decidi tu cosa preferisci che gestisca la stack dei messaggi* getMessagesStackList() {}

    /**
     * removes a messages stack (list in notification) for notify_id
     * @param notify_id
     */
    public void removeMessagesStackForNotifyID(int notify_id);





}