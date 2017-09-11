package services;

import sql.CategorySQL;
import sql.FriendSQL;
import sql.PartySQL;
import sql.PrivilegeSQL;
import sql.ServiceSQL;
import sql.StatusSQL;
import sql.TableSQL;
import sql.UserClientSQL;
import sql.UserProfessionalSQL;
import sql.UserTypeSQL;

public class PageOnLoad {
	
	public static void loadArrayLists() {
		TableSQL.loadTables();
		StatusSQL.loadStatus();
		CategorySQL.loadCategory();
		PrivilegeSQL.loadPrivilege();
		UserTypeSQL.loadUserType();
		UserClientSQL.loadUserClient();
		UserProfessionalSQL.loadUserProfessional();
		FriendSQL.loadUsersFriends();
		ServiceSQL.loadService();
		PartySQL.loadParty();
		PartySQL.loadPartyStage();
		PrivilegeSQL.loadPrivilegeToServices();
		UserClientSQL.loadFriendInvite();
		PartySQL.loadPartyInvite();
		UserClientSQL.loadUsersToParty();
	}

}
