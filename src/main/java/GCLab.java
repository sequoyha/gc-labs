/*
 * Created by sequoyha on 8/26/16.
 */

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;

public class GCLab {
    public static void main(String[] args) {
        GCLab gc = new GCLab();
        DseCluster cluster = gc.CreateCluster("localhost");
        DseSession session = gc.ConnectToCluster(cluster);
        gc.ChooseYourWeapon(session,args[0]);
        gc.CloseCluster(cluster);
    }

    public DseCluster CreateCluster(String nodeId)
    {
        DseCluster dseCluster;
            dseCluster = DseCluster.builder()
                    .addContactPoint(nodeId)
                    .build();
            DseSession dseSession = dseCluster.connect();

            dseSession.execute("CREATE KEYSPACE IF NOT EXISTS garbage WITH replication = {'class': 'SimpleStrategy', '" +
                    "replication_factor': 3};");

            dseSession.execute("CREATE TABLE IF NOT EXISTS garbage.collection (id int, step int, val1 int, val2 int, val3 int, val4 int, val5 int, " +
                    "val6 int, val7 int, val8 int, val9 int, val10 int, val11 int, val12 int, val13 int, val14 int,val15 int, val16 int, val17 int, " +
                    "val18 int, val19 int, val20 int, val21 int, val22 int, val23 int, val24 int, val25 int, val26 int, val27 int, " +
                    "val28 int, val29 int, val30 int, primary key (id, step));");
            return dseCluster;
    }
    public DseSession ConnectToCluster(DseCluster dseCluster)
    {
            DseSession dseSession = dseCluster.connect();
            return dseSession;
    }

    public void CloseCluster(DseCluster dseCluster)
    {
        dseCluster.close();
    }
    public void ChooseYourWeapon(Session session, String choice)
    {
        switch (Integer.parseInt(choice.substring(choice.length()-1)))
        {
            case 1:
                TombstoneHell t1 = new TombstoneHell(session);
                t1.run();
                break;
            case 2:
                BatchSlapped bs = new BatchSlapped(session, 0, 1000000);
                bs.run();
                break;
            case 3:
                KevinSmithed ks = new KevinSmithed(session);
                ks.buyExtraSeat();
                break;
            default:
                System.out.println("Invalid argument for Lab #");

        }
    }

}
