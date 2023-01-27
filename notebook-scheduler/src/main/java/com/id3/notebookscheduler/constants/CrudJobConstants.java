package com.id3.notebookscheduler.constants;

public class CrudJobConstants {
    public static String jobIdentity = "collectKeycloakUsers";
    public static String groupName = "group 1";
    public static String jobDescription = "Fetch keycloak users and send to kafka";
    public static String triggerIdentity = "keycloakTrigger";
    public static String triggerDescription = "Fetch keycloak users and send to kafka";

    public static String cronExpression = "0 0/1 * 1/1 * ? *";
}
