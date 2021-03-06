package Smtp.StateMachine;

import Smtp.SmtpContext;

public class ReceivingDataState implements SmtpStateInf {
    SmtpContext context;
    boolean crReceived;
    boolean dotReceived;

    public ReceivingDataState(SmtpContext context) {
        this.context = context;
    }

    @Override
    public void Handle(String data) {
        //handle the receiving of the mailbody
        // "\r\n.\r\n" should return to the Smtp.StateMachine.WaitForMailFromState
    }
}
