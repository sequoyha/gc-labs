import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.dse.DseSession;

/**
 * Created by sequoyha on 8/29/16.
 */
public class DeathByTombstones {

    private Session session;
    private int currentStep;
    private int id;
    private int currentId;

    DeathByTombstones(Session thisSession, int thisStep)
    {
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
    public void delete()
    {
        while (this.getCurrentId() <=1000000)
        {
            this.setCurrentId(this.executeBatch(this.getCurrentId()));
        }
    }
    public int executeBatch(int startingId)
    {
        PreparedStatement deleteStatement = session.prepare("Delete val2, val3, val4, val5, val6, val7, val8, val9, val10" +
                ",val11,val12 , val13 , val14 ,val15, val16 , val17 , " +
                "val18 , val19 , val20 , val21 , val22 , val23 , val24 , val25 , val26 , val27 ," +
                "val28 , val29 from garbage.collection where id = ? and step = ?");

        int endId = startingId + 99;
        BatchStatement batchStatement = new BatchStatement();
        while(startingId < endId)
        {
            BoundStatement boundDelete = deleteStatement.bind(startingId, currentStep);
            batchStatement.add(boundDelete);
            startingId++;
        }
        session.execute(batchStatement);
        return startingId;
    }
}
