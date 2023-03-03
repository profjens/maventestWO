package js_fb5.w_hs.testmaven.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import js_fb5.w_hs.model.MeinUser;

public class ZweiteSeite extends ERXComponent {
	
	private static final long serialVersionUID = 1L;
	private EOEditingContext _editingContext;
	private NSArray<MeinUser> uListe;
    public ZweiteSeite(WOContext context) {
        super(context);
        _editingContext = ERXEC.newEditingContext(); 
    }
    public NSArray<MeinUser>getUListe(){
    	uListe = MeinUser.fetchAllMeinUsers(_editingContext);
    	return uListe;
    }
    public MeinUser aUser, aU;
    public WOActionResults addUser() {
    	aUser = MeinUser.createMeinUser(_editingContext, "test");
    	return null;
    }
    public WOActionResults deleteUser() {
    	try {
        	aU.delete();
        	aU.editingContext().saveChanges();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }
    public WOActionResults save() {
    	aUser.editingContext().saveChanges();
    	return null;
    }
}