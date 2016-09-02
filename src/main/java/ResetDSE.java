/**
 * Created by sequoyha on 9/2/16.
 */
public class ResetDSE
{
    public void performReset()
    {
        try
        {
            String[] cmd = {"/bin/bash","-c","echo password| sudo -S /home/support/gc-labs/bash/resetDse"};
            Process pb = Runtime.getRuntime().exec(cmd);
        }catch (Exception e)
        {
            System.out.print("Failure in execution\n" + e.getMessage() +"\n" + e.getStackTrace().toString());
        }
    }

}
