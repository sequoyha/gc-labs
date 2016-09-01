import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import java.util.Random;

/**
 * Created by sequoyha on 8/26/16.
 */
public class KevinSmithed {

            /* Writes excessively wide rows that are then retrieved
            via queries. Continues to write data in order to trigger compactions
            and further create long GC pauses
             */
    private Session session;
    KevinSmithed (Session thisSession)
    {
        session = thisSession;
    }

    public void buyExtraSeat()
    {
        session.execute("CREATE TABLE IF NOT EXISTS garbage.collect_more (id int, step int, cc1 int, cc2 int, val1 int, val2 int, val3 int, val4 int, val5 int, " +
                "val6 int, val7 int, val8 int, val9 int, val10 int, val11 int, val12 int, val13 int, val14 int,val15 int, val16 int, val17 int, " +
                "val18 int, val19 int, val20 int, val21 int, val22 int, val23 int, val24 int, val25 int, val26 int, val27 int, " +
                "val28 int, val29 int, val30 int, primary key (id, step, cc1, cc2));");
        PreparedStatement ps = session.prepare("Insert into garbage.collect_more "
                + "(id , step , cc1, cc2, val1 , val2 , val3 , val4 , val5 , " +
                "val6 , val7 , val8 , val9 , val10 , val11 , val12 , val13 , val14 ,val15, val16 , val17 , " +
                "val18 , val19 , val20 , val21 , val22 , val23 , val24 , val25 , val26 , val27 , " +
                "val28 , val29 , val30) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        try
        {
            Random r = new Random();
            for (int steps = 1; steps < 1000; steps++)
            {
                for(int id = 1; id <=1000; id++)
                {
                    for (int cluster1 = 1; cluster1 <1000; cluster1++)
                    {
                        BatchStatement batchStatement = new BatchStatement();
                        for (int cluster2 = 1; cluster2 <100; cluster2++)
                        {
                            BoundStatement bound = ps.bind(id, steps, cluster1, cluster2,r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
                            batchStatement.add(bound);
                        }
                        session.execute(batchStatement);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\nError while inserting records:\n\t" + e.getMessage() +
                    "\n" + e.getStackTrace());
        }
    }
}
