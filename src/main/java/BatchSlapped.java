import com.datastax.driver.core.*;

import java.util.Random;

/**
 * Created by sequoyha on 8/26/16.
 *
 *
 * Excessive batch sizes written to overload the nodes
 * with GC activity. This can only be fixed properly via
 * a config change to the write behavior. Adjusting the HEAP
 * may provide temp solution but is not a long term fix
 */
public class BatchSlapped implements Runnable{

    private Session session;
    private int endKey;
    private int currentKey;

    BatchSlapped (Session thisSession,int thisKey, int thisEndKey)
    {
        session = thisSession;
        currentKey = thisKey;
        endKey = thisEndKey;
    }
    public void run()
    {
        PreparedStatement ps = session.prepare("Insert into garbage.collection "
                + "(id , step , val1 , val2 , val3 , val4 , val5 , " +
                "val6 , val7 , val8 , val9 , val10 , val11 , val12 , val13 , val14 ,val15, val16 , val17 , " +
                "val18 , val19 , val20 , val21 , val22 , val23 , val24 , val25 , val26 , val27 , " +
                "val28 , val29 , val30) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try
        {
            Random r = new Random();
            while (currentKey <= endKey)
            {
                BatchStatement batchStatement = new BatchStatement();
                for (int steps = 1; steps < 1000; steps++)
                {
                    BoundStatement bound = ps.bind(currentKey, steps, r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
                    batchStatement.add(bound);
                }
                currentKey++;
                session.execute(batchStatement);
            }
        } catch (Exception e) {
            System.out.println("\nError while inserting records:\n\t" + e.getMessage() +
                    "\n" + e.getStackTrace());
        }
    }
}
