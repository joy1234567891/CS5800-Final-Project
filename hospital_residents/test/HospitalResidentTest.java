import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import MatchAlgo.HospitalResident;
import Resident.Resident;
import Hospital.Hospital;

public class HospitalResidentTest {

  @Test
  public void testBasicMatching() {

    // Create hospitals
    Hospital hospitalA = new Hospital("Hospital A", 1, new ArrayList<>());
    Hospital hospitalB = new Hospital("Hospital B", 1, new ArrayList<>());

    // Create residents
    Resident resident1 = new Resident("Resident 1", new ArrayList<>(List.of(hospitalA, hospitalB)));
    Resident resident2 = new Resident("Resident 2", new ArrayList<>(List.of(hospitalB, hospitalA)));

    // Add residents to hospital preferences
    hospitalA.setPreferredResidents(new ArrayList<>(List.of(resident1)));
    hospitalB.setPreferredResidents(new ArrayList<>(List.of(resident2)));

    // Run the matching algorithm
    List<Hospital> hospitals = HospitalResident.residentsOptimal(
        List.of(resident1, resident2),
        List.of(hospitalA, hospitalB)
    );

    // Assertions
    assertEquals(1, hospitalA.getAcceptedResidents().size());
    assertEquals("Resident 1", hospitalA.getAcceptedResidents().get(0).getName());
    assertEquals(1, hospitalB.getAcceptedResidents().size());
    assertEquals("Resident 2", hospitalB.getAcceptedResidents().get(0).getName());
  }

  @Test
  public void testCapacityHandling() {
    // Create hospitals
    Hospital hospitalA = new Hospital("Hospital A", 1, new ArrayList<>());
    Hospital hospitalB = new Hospital("Hospital B", 1, new ArrayList<>());

    // Create residents
    Resident resident1 = new Resident("Resident 1", new ArrayList<>(List.of(hospitalA, hospitalB)));
    Resident resident2 = new Resident("Resident 2", new ArrayList<>(List.of(hospitalA, hospitalB)));
    Resident resident3 = new Resident("Resident 3", new ArrayList<>(List.of(hospitalA, hospitalB)));

    // Add residents to hospital preferences
    hospitalA.setPreferredResidents(new ArrayList<>(List.of(resident1, resident2, resident3)));
    hospitalB.setPreferredResidents(new ArrayList<>(List.of(resident1, resident2, resident3)));

    // Run the matching algorithm
    List<Hospital> hospitals = HospitalResident.residentsOptimal(
        List.of(resident1, resident2, resident3),
        List.of(hospitalA, hospitalB)
    );

    // Assertions
    assertEquals(1, hospitalA.getAcceptedResidents().size());
    assertEquals(1, hospitalB.getAcceptedResidents().size());
  }

  @Test
  public void testPreferredHospitals() {
    // Create hospitals
    Hospital hospitalA = new Hospital("Hospital A", 2, new ArrayList<>());
    Hospital hospitalB = new Hospital("Hospital B", 1, new ArrayList<>());

    // Create residents
    Resident resident1 = new Resident("Resident 1", new ArrayList<>(List.of(hospitalA, hospitalB)));
    Resident resident2 = new Resident("Resident 2", new ArrayList<>(List.of(hospitalB, hospitalA)));

    // Add residents to hospital preferences
    hospitalA.setPreferredResidents(new ArrayList<>(List.of(resident1)));
    hospitalB.setPreferredResidents(new ArrayList<>(List.of(resident2)));

    // Run the matching algorithm
    List<Hospital> hospitals = HospitalResident.residentsOptimal(
        List.of(resident1, resident2),
        List.of(hospitalA, hospitalB)
    );

    // Assertions
    assertEquals(1, hospitalA.getAcceptedResidents().size());
    assertEquals("Resident 1", hospitalA.getAcceptedResidents().get(0).getName());
    assertEquals(1, hospitalB.getAcceptedResidents().size());
    assertEquals("Resident 2", hospitalB.getAcceptedResidents().get(0).getName());
  }

  @Test
  public void testComplexSettings() {
    // Create hospitals
    Hospital hospitalA = new Hospital("Hospital A", 2, new ArrayList<>());
    Hospital hospitalB = new Hospital("Hospital B", 2, new ArrayList<>());
    Hospital hospitalC = new Hospital("Hospital C", 2, new ArrayList<>());

    // Create residents
    Resident resident1 = new Resident("Resident 1", new ArrayList<>(List.of(hospitalB)));
    Resident resident2 = new Resident("Resident 2", new ArrayList<>(List.of(hospitalB, hospitalA)));
    Resident resident3 = new Resident("Resident 3", new ArrayList<>(List.of(hospitalB, hospitalA, hospitalC)));
    Resident resident4 = new Resident("Resident 4", new ArrayList<>(List.of(hospitalA, hospitalB, hospitalC)));
    Resident resident5 = new Resident("Resident 5", new ArrayList<>(List.of(hospitalB, hospitalC, hospitalA)));

    // Add residents to hospital preferences
    hospitalA.setPreferredResidents(new ArrayList<>(List.of(resident3, resident4, resident2, resident5)));
    hospitalB.setPreferredResidents(new ArrayList<>(List.of(resident3, resident1, resident2, resident4, resident5)));
    hospitalC.setPreferredResidents(new ArrayList<>(List.of(resident3, resident5, resident4)));

    // Run the matching algorithm
    List<Hospital> hospitals = HospitalResident.residentsOptimal(
        List.of(resident1, resident2, resident3, resident4, resident5),
        List.of(hospitalA, hospitalB, hospitalC)
    );

    // Assertions
    assertEquals(2, hospitalA.getAcceptedResidents().size());
    assertEquals("Resident 2 Resident 4 ", hospitalA.allAcceptedResidents());
    assertEquals(2, hospitalB.getAcceptedResidents().size());
    assertEquals("Resident 1 Resident 3 ", hospitalB.allAcceptedResidents());
    assertEquals(1, hospitalC.getAcceptedResidents().size());
    assertEquals("Resident 5 ", hospitalC.allAcceptedResidents());
  }
}

