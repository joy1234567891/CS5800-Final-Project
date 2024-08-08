package MatchAlgo;

import Resident.Resident;
import Hospital.Hospital;
import java.util.List;
import java.util.ArrayList;

public class HospitalResident {
  private Resident resident;
  private Hospital hospital;

  public HospitalResident(Resident resident, Hospital hospital) {
      this.resident = resident;
      this.hospital = hospital;
  }

  public static List<Hospital> residentsOptimal(List<Resident> residentsToMatch, List<Hospital> hospitalsToMatch) {
    List<Resident> residents = new ArrayList<>(residentsToMatch);
    List<Hospital> hospitals = new ArrayList<>(hospitalsToMatch);
    while (!residents.isEmpty()) {
      Resident res = residents.remove(0);
      if(res.getPreferredHospitals().isEmpty()) {
        continue;
      }
      Hospital favHospital = res.getPreferredHospitals().remove(0);
      if (favHospital.atCapacity()) {
        Resident worstRes = favHospital.getWorstResident();
        favHospital.unmatchResident(worstRes);
        residents.add(0, worstRes);
      }
      favHospital.acceptResident(res);

      if (favHospital.atCapacity()) {
        ArrayList<Resident> successors = favHospital.get_successors(res);
        if (!successors.isEmpty()) {
          for (Resident successor : successors) {
            favHospital.deleteResident(successor);
            successor.deleteHospital(favHospital);
          }
        }
      }
    }
    return hospitals;
  }
}

