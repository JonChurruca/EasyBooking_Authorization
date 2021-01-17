package AuthorizationSystemServices;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthorizationService extends Remote {
	boolean sendValidation(String email, String password) throws RemoteException;


}
