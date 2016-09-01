

/**
 * Created by sequoyha on 9/1/16.
 */
public class GetLow
{
    public void fuckUpGarbageCollector()
    {
        try
        {
            String[] cmd = {"/bin/bash","-c","echo password| sudo -S /home/support/tester"};
            Process pb = Runtime.getRuntime().exec(cmd);
        }catch (Exception e)
        {
            System.out.print("Failed...you fucking suck\n" + e.getMessage());
        }

    }
}
