package fulton.util.net.register;

import java.util.HashMap;

import fulton.util.net.HttpRequest;
/**
 * A host has its corresponding login manager
 * 
 * Login is our bussiness, register is not.
 * 
 * The routines that handles is
 * 			if success,go to the ask page
 * 				the previously success may failed bacause of 
 * time issue.So it is its duty to identity the two status.
 * 			if not,stay 
 * 
 * In practice of the design,a mechanism must be found to
 * do the later post.Suppose that is the cookie mechanism
 * 
 * But whatever flavor of mechanism it is , it will always 
 * be included in the next Request Headers.So use HashMap to 
 * Keep it.
 * 
 * Besides,
 * 		This request(url...), url can be partial.If url doesn't starts
 * 	 with a valid domain,then just add it.Or not.
 * 
 * Still,
 * 		Lot of work to do.
 * 
 * @author Douglas
 *
 */

public interface LoginRequester extends HttpRequest{
	
	/**
	 * This sends a message to the host and wait for its response.
	 * the by judging the status,return true if login,else false.
	 * @return
	 */
	public boolean isLogin();
	
	/**
	 * After post login request,call isLogin to get the status
	 * May contain : Cookie
	 * Use of any well designed http requester will help 
	 * you do most sutff.
	 * @return
	 */
	public boolean login();
	
	
	
	/**
	 * send logout request
	 */
	public void logout();
	
	public String getUserName();
	
	public String getPassword();
	
	public String getHost();
	
	
}
