import com.datastax.driver.core.Session;

/**
 * Created by sequoyha on 10/5/16.
 */
public class Hell
{
    public void unleashHell(Session session)
    {
        TombstoneHell tombstoneHell = new TombstoneHell(session);
        tombstoneHell.run();
        BatchSlapped batchSlapped = new BatchSlapped(session, 0, 1000000);
        batchSlapped.run();
        RunnableKevinSmith runnableKevinSmith = new RunnableKevinSmith(session);
        runnableKevinSmith.run();

    }

    private class RunnableKevinSmith implements Runnable{

        private Session session;
        RunnableKevinSmith(Session session)
        {
            this.session = session;
        }
        public void run()
        {
            KevinSmithed ks = new KevinSmithed(session);
            ks.buyExtraSeat();
        }
    }


}
