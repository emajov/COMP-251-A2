import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// if assignments weight the same
		if (a1.weight == a2.weight) {
			return 0;
		}
		// if a1 weighs less, it goes after a2 in list
		else if (a1.weight < a2.weight) {
			return 1;
		}
		// else a1 weighs more and goes before
		else {
			return -1;
		}
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
		
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {	
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
				
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
				
		for (Assignment asgn : Assignments) {
			//int start = 0;
			int end = asgn.deadline;
			int slot = end-1;
			if (homeworkPlan[slot] == -1) {
				homeworkPlan[slot] = asgn.number;
			}
			if (homeworkPlan[slot] != -1) {
				for (int i=homeworkPlan[slot]; i>0; i--) {
					if (i == -1) {
						homeworkPlan[i] = asgn.number;
					}
				}					
				if (slot >= 0 && slot == -1) {
					homeworkPlan[slot] = asgn.number;
				}
				else {
					for (int i=homeworkPlan.length-1; i>end; i--) {
						if (i == -1) {
							homeworkPlan[i] = asgn.number;
						}
					}
				}
			}

		}
		return homeworkPlan;
	}
}
	



