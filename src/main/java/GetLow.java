

/**
 * Created by sequoyha on 9/1/16.
 */
public class GetLow
{
    public void fuckUpGarbageCollector()
    {
        try
        {
            String[] cmd = {"/bin/bash","-c","echo password| sudo -S /home/support/.gc-labs/bash/gc_config_changes"};
            Process pb = Runtime.getRuntime().exec(cmd);
        }catch (Exception e)
        {
            System.out.print("Failure in execution\n" + e.getMessage() +"\n" + e.getStackTrace().toString());
        }

    }
}
