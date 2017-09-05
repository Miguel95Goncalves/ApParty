package model;

public class PartyStage {

	private int party_stage_id;
	private String party_stage_description;
	
	public PartyStage(int party_stage_id, String party_stage_description) {
		super();
		this.party_stage_id = party_stage_id;
		this.party_stage_description = party_stage_description;
	}
	
	public int getParty_stage_id() {
		return party_stage_id;
	}
	public void setParty_stage_id(int party_stage_id) {
		this.party_stage_id = party_stage_id;
	}
	public String getParty_stage_description() {
		return party_stage_description;
	}
	public void setParty_stage_description(String party_stage_description) {
		this.party_stage_description = party_stage_description;
	}

}
