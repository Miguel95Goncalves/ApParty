package services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Party;
import model.Post;
import model.Privilege;
import model.Service;
import model.Status;
import model.Table;
import model.UserClient;
import model.UserProfessional;
import model.UserType;

public interface Logic {
	static ArrayList<UserClient> arUserClient = new ArrayList<>();
	static ArrayList<UserProfessional> arUserProfessional = new ArrayList<>();
	static ArrayList<Party> arParty = new ArrayList<>();
	static ArrayList<Post> arPost = new ArrayList<>();
	static ArrayList<Privilege> arPrivilege = new ArrayList<>();
	static ArrayList<Service> arService = new ArrayList<>();
	static ArrayList<Status> arStatus = new ArrayList<>();
	static ArrayList<Table> arTable = new ArrayList<>();
	static ArrayList<UserType> arUserType = new ArrayList<>();

	String execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
