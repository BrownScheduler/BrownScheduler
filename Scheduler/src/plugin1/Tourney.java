package plugin1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import middleend.MiddleEnd;
import backbone.Grouping;


public class Tourney implements backbone.Tournament{
	
	private ArrayList<plugin1.MyRound> rounds;
	private TeamGrouping competitors;
	private JudgeGrouping judges;
	private int totalRounds;
	
	public Tourney(){
		rounds = new ArrayList<MyRound>();
		competitors = new TeamGrouping(this, "Competitors");
		judges = new JudgeGrouping(this, "Judges");
		totalRounds = 2;
	}
	
	@Override
	public MyRound createNextRound(){
		ConstraintHandler handle = new ConstraintHandler(this, this.competitors.getMembers(), 
				this.judges.getMembers());
		MyRound r = handle.createNewRound();
		rounds.add(r);
		System.out.println(r);
		return r;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Grouping> getCategories() {
		ArrayList<Grouping> cats = new ArrayList<Grouping>();
		cats.add(this.competitors);
		cats.add(this.judges);
		return cats;
	}
	
	@Override
	public List<backbone.Round> getRounds() {
		LinkedList<backbone.Round> rs = new LinkedList<backbone.Round>();
		rs.addAll(rounds);
		return rs;
	}
	
	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		Tourney t = new Tourney();
		new MiddleEnd(t);
	}
}
