/**
 * 
 */
package org.core.client.auhorization;

import org.core.client.authorization.IAuthorizer;


/**
 * @author piotrek
 *
 */
public class AuthorizationImpl implements IAuthorizer {

  /*
   * (non-Javadoc)
   * 
   * @see org.core.client.auhorization.IAuthorization#doLogin(java.lang.String)
   */
  public Boolean doLogin(final String userEmail) {
    System.out.println("userEmail: " + userEmail);
    return true;
  }

}
