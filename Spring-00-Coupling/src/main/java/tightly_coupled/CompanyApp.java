package tightly_coupled;
public class CompanyApp {
    public static void main(String[] args) {

        FullTimeMentor fullTimeMentor = new FullTimeMentor();
        PartTimeMentor partTimeMentor = new PartTimeMentor();

        Mentor mentor = new Mentor(fullTimeMentor, partTimeMentor); // MI
        mentor.manageAccount();

        // MI


    }
}
