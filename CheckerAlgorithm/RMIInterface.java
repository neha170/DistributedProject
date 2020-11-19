package CheckerAlgorithm;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{
    Response getPref() throws RemoteException;
    Response getAllocation(Request request) throws RemoteException;	
}