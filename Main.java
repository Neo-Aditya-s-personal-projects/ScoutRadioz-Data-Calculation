import gui.DataInputManual;
import gui.GUI;
import data.DataRequest;
public class Main {
    public static void main(String Args[]) {
        DataRequest.setAPIKey("");
        GUI gui = new GUI();
        new DataInputManual(gui);
    }
}