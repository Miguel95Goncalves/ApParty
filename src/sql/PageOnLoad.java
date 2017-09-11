package sql;

public interface PageOnLoad {
	
	static void PageOnLoad() {
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
