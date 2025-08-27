package project.data.extract.util;

import lombok.Getter;

public enum Error {
    LOCKING_ACCOUNT_WARNING("NOTE: We have noticed that you have 2 attempts already on entering your account. " +
            "\nPlease be informed that the 3rd attempt with the same username might lock the account automatically if incorrectly entered." +
            "\nPlease make sure to enter your correct username and password. Thank you!"),
    CONNECTION_ERROR("Unable to establish the connection! Please make sure you are connected to the Allianz Network (VPN\\Citrix) and try again."),   //VPN/INTERNET/CITRIX
    SERVER_ERROR("Server Error Occured"),       //CODE ERROR
    DATABASE_AUTH_FAIL("Authentication Failed: Invalid Credential! Please try again."),     //DATABASE CREDENTIALS AND OTHERS
    DATABASE_LOCKED("Your account is locked. Please reach out to ITHD team to assist you with your credential. Thank you."),
    INVALID_REQUEST("Invalid Input ");     //INVALID REQUEST

    @Getter private String user_msg;
    Error(String user_msg) {
        this.user_msg = user_msg;
    }
}
