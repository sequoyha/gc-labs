import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import java.util.Random;

/**
 * Created by sequoyha on 8/30/16.
 */
public class TombStoneBatch {

    private Session session;
    private int currentStep;
    private int id;
    private int currentId;

    TombStoneBatch(Session thisSession, int thisStep) {
        session = thisSession;
        currentStep = thisStep;
        id = 1;
        currentId = id;
    }
    public int getCurrentId()
    {
        return currentId;
    }
    public void setCurrentId(int thisId)
    {
        currentId = thisId;
    }
    public void insert() {
        System.out.println("Inserting data for all keys and step " + currentStep);
        while (this.getCurrentId() <=1000000)
        {
            this.setCurrentId(this.executeBatch(this.getCurrentId()));
        }
    }

    public int executeBatch(int startingId) {
        PreparedStatement ps = session.prepare("Insert into garbage.collection "
                + "(id , step , val1 , val2 , val3 , val4 , val5 , " +
                "val6 , val7 , val8 , val9 , val10 , val11 , val12 , val13 , val14 ,val15, val16 , val17 , " +
                "val18 , val19 , val20 , val21 , val22 , val23 , val24 , val25 , val26 , val27 , " +
                "val28 , val29 , val30) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        Random r = new Random();
        int endId = startingId + 99;
        BatchStatement batchStatement = new BatchStatement();
        while (startingId < endId)
        {
            BoundStatement bound = ps.bind(startingId, currentStep, r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
            batchStatement.add(bound);
            startingId++;
        }
        session.execute(batchStatement);
        return startingId;

    }
}
