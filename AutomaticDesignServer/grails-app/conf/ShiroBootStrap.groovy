import org.apache.shiro.crypto.hash.Sha256Hash

/**
 * User creation / configuration
 * @author rpb rich@k-int.com
 * @version 1.0 16.01.12
 */
class ShiroBootStrap {
    
    def init = { servletContext ->        
        def adminUser = ShiroUser.findByUsername("admin");
        if ( !adminUser ) {
            adminUser = new ShiroUser(username: "admin", passwordHash: new Sha256Hash("${com.k_int.made4u.adminPass}").toHex())
            adminUser.addToPermissions("*:*")
            adminUser.save()
        }
        
        def ibvuser = ShiroUser.findByUsername("ibvuser");
        if ( !ibvuser ) {
            ibvuser = new ShiroUser(username: "ibvuser", passwordHash: new Sha256Hash("${com.k_int.made4u.ibvPass}").toHex())
            ibvuser.addToPermissions("*:*");
            ibvuser.save();
        }
        
        def databaseUser = ShiroUser.findByUsername("databaseuser");
        if ( !databaseUser ) {
            databaseUser = new ShiroUser(username: "databaseuser", passwordHash: new Sha256Hash("${com.k_int.made4u.dataPass}").toHex())
            databaseUser.addToPermissions("*:*");
            databaseUser.save();
        }
    }

    def destroy = {
    }
	
}

