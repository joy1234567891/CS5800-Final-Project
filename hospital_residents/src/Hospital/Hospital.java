package Hospital;

import java.util.ArrayList;
import Resident.Resident;

public class Hospital {
    private String name;
    private int capacity;
    private ArrayList<Resident> acceptedResidents;
    private ArrayList<Resident> preferredResidents;
    private ArrayList<Resident> fullPreferredResidents;


    public Hospital(String name, int capacity, ArrayList preferredResidents) {
        this.name = name;
        this.capacity = capacity;
        this.preferredResidents = preferredResidents;
        this.fullPreferredResidents = preferredResidents;
        this.acceptedResidents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Resident> getPreferredResidents() {
        return preferredResidents;
    }

    public ArrayList<Resident> getAcceptedResidents() {
        return acceptedResidents;
    }

    public void setPreferredResidents(ArrayList<Resident> preferredResidents) {
        this.preferredResidents = preferredResidents;
    }

    public void acceptResident(Resident res) {
        acceptedResidents.add(res);
    }

    public boolean atCapacity() {
        return (acceptedResidents.size() >= capacity);
    }

    public Resident getWorstResident() {
        Resident worst = acceptedResidents.get(0);
        for (Resident res : acceptedResidents) {
            if (preferredResidents.indexOf(res) > preferredResidents.indexOf(worst)) {
                worst = res;
            }
        }
        return worst;
    }

    public void unmatchResident(Resident res) {
        acceptedResidents.remove(res);
    }

    public ArrayList get_successors(Resident res) {
        ArrayList<Resident> successors = new ArrayList<>();
        for (Resident r : preferredResidents) {
            if (preferredResidents.indexOf(r) > preferredResidents.indexOf(res)) {
                successors.add(r);
            }
        }
        return successors;
    }

    public void deleteResident(Resident successor) {
        preferredResidents.remove(successor);
    }

    public String allAcceptedResidents() {
        String result = "";
        for (Resident res : acceptedResidents) {
            result += res.getName() + " ";
        }
        return result;
    }
}
