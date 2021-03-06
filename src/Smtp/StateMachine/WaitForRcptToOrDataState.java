package Smtp.StateMachine;

import Smtp.SmtpContext;

public class WaitForRcptToOrDataState implements SmtpStateInf {

    SmtpContext context;

    public WaitForRcptToOrDataState(SmtpContext context) {
        this.context = context;
    }

    @Override
    public void Handle(String data) {

        //Handle "MAIL FROM: <user@domain.nl>" Command & TRANSITION TO NEXT STATE
        if (data.startsWith("RCPT TO:")) {
            context.AddRecipient(data.substring(9));
            context.SendData("250 OK");
            context.SetNewState(new WaitForRcptToOrDataState(context));
            return;
        }

        //Handle "DATA" Command & TRANSITION TO NEXT STATE
        if (data.startsWith("DATA")) {

        }

        //Handle "QUIT" Command
        // If date start with "QUIT"
        if(data.toUpperCase().startsWith("QUIT")) {
            // Send "221 Bye to sockets output via context helper method"
            context.SendData("221 Bye");
            // Call the disconnect helper method from context
            context.DisconnectSocket();
            return;
        }

        //Generate "503 Error on invalid input"
        // Otherwise send an error message to context
        context.SendData("503 Error...");

    }
}
