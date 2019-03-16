package com.gangout.Utils;

public class UserExistanceResponse implements ServerResponse {

    private ServerResponseNotifier en;
    public UserExistanceResponse()
    {
        // Create the event notifier and pass ourself to it.
        en = new ServerResponseNotifier (this);
    }
    // Define the actual handler for the event.
    public void interestingEvent ()
    {
        // Wow!  Something really interesting must have occurred!
        // Do something...
    }

    @Override
    public void GetServerResponse() {

    }
}
