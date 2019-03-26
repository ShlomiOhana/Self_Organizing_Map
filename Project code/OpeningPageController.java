import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OpeningPageController {

    @FXML
    private AnchorPane a1;

    @FXML
    private TextField dimX;

    @FXML
    private TextField dimY;

    @FXML
    private TextField path;

    @FXML
    private CheckBox numN4;

    @FXML
    private CheckBox numN6;

    @FXML
    private Button okBtn;

    @FXML
    void onClickOk(ActionEvent event) {
    	String linkPath = path.getText();
    	System.out.println(linkPath);
    	

    }

}
