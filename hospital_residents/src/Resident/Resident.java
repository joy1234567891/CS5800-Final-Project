package Resident;

import Hospital.Hospital;
import java.util.ArrayList;

public class Resident {
  private String name;
  private ArrayList<Hospital> preferredHospitals;

  public Resident(String name, ArrayList preferredHospitals) {
    this.name = name;
    this.preferredHospitals = preferredHospitals;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Hospital> getPreferredHospitals() {
    return preferredHospitals;
  }

  public void deleteHospital(Hospital hos) {
    preferredHospitals.remove(hos);
  }

}
