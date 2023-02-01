package com.id3.notebookscheduler.constants;


public class KeycloakJobConstants {
    public static String jobIdentity = "collectScheduledNotebooks";
    public static String groupName = "group 2";
    public static String jobDescription = "Fetch scheduled notebooks and send to kafka";
    public static String triggerIdentity = "crudTrigger";
    public static String triggerDescription = "Fetch scheduled notebooks and send to kafka";

    public static String cronExpression = "0 0/1 * 1/1 * ? *";


}
