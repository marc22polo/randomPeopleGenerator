import java.util.Comparator;

public class OsebaComparator implements Comparator<Oseba>{

	@Override
	public int compare(Oseba o1, Oseba o2) {
		// TODO Auto-generated method stub
		return o1.getUsername().compareTo(o2.getUsername());
	}
	
}
