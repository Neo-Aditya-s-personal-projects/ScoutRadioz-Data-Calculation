import data.Request;
import gui.DataInputManual;
import gui.GUI;
public class Main {
    public static void main(String Args[]) {
        Request.setAPIKey("");
        GUI gui = new GUI();
        new DataInputManual(gui);
    }
}