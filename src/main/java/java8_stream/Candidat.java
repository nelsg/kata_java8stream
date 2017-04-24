package java8_stream;

public class Candidat {
	private String prenom;
	private int votes = 0;

	public Candidat(String prenom) {
		super();
		this.prenom = prenom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int addVote() {
		return (votes += 1);
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	@Override
	public String toString() {
		return "[" + prenom + ", votes=" + votes + "]";
	}
}
