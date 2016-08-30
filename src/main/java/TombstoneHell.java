import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import java.util.Random;

/**
 * Created by sequoyha on 8/26/16.
 */
public class TombstoneHell implements Runnable {

    private Session session;
    private int step;

    TombstoneHell (Session thisSession)
    {
        session = thisSession;
        step=1;
    }
    public void run()
    {
        while(step <=1000)
        {
            TombStoneBatch tombStoneBatch = new TombStoneBatch(session, step);
            tombStoneBatch.insert();
            DeathByTombstones dbt = new DeathByTombstones(session,step);
            dbt.delete();
            step++;
        }

    }
}
