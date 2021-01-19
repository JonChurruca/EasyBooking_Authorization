package AuthorizationSystemServices;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class AuthorizationService extends UnicastRemoteObject implements IAuthorizationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private IAuthorizationService; 
	protected HashMap <String,String> users = new HashMap<>(); 
	
	public AuthorizationService()throws RemoteException {
		// we create some default values 
		users.put("haizearodriguez@opendeusto.es", "1234"); 
		users.put("unai.mendiondo@opendeusto.es", "23456"); 

	}

	
	public boolean sendValidation(String email, String password) {
		
		boolean result; 
		
        if (users.containsKey(email) && users.containsValue(password)) {
        	result = true; 
        	
        } else {
        	result = false;
           
        }
        	
        return result; 
	}
	
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		
		//Create the name of the server 
		// EX: name = "//120.0.1:1099/Hello"
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {	
			IAuthorizationService authServer = new AuthorizationService();
			Naming.rebind(name, authServer);
			System.out.println("* Server '" + name + "' active and waiting...");
			
			
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
