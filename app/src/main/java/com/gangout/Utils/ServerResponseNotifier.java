package com.gangout.Utils;

public class ServerResponseNotifier {

    private ServerResponse ie;
    private boolean somethingHappened;

    public ServerResponseNotifier (ServerResponse event)
    {
        // Save the event object for later use.
        ie = event;
        // Nothing to report yet.
        somethingHappened = false;
    }
    //...
    public void doWork ()
    {
        // Check the predicate, which is set elsewhere.
        if (somethingHappened)
        {
            // Signal the even by invoking the interface's method.
            ie.Ser ();
        }
        //...
    }
    // ...
}
