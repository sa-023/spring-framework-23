package tightly_coupled;
public class Mentor {

    // we cant do multiple inheritance with the classes in Java. So we do has a relationship by adding references of needed classes
    FullTimeMentor fullTimeMentor;
    PartTimeMentor partTimeMentor;

    // MI
    public Mentor(FullTimeMentor fullTimeMentor, PartTimeMentor partTimeMentor) { // MI
        this.fullTimeMentor = fullTimeMentor;
        this.partTimeMentor = partTimeMentor;
    }

    public void manageAccount(){
        fullTimeMentor.createAccount();
        partTimeMentor.createAccount();
        // MI
    }
}
