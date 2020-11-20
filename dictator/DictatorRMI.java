package dictator;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictatorRMI extends Remote {
    void receiveFinal(Request request) throws RemoteException;
    void receiveTentative(Request request) throws RemoteException;
    void receiveProposal(Request request) throws RemoteException;
    void receiveDeliver(Request req) throws RemoteException;
}
