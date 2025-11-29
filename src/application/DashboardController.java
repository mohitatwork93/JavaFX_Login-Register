package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController 
{
	
	
	@FXML private Button management1NavBtn;
	@FXML private Button management2NavBtn;
	@FXML private Button management3NavBtn;
	@FXML private Button profileNavBtn;
	@FXML private Button logoutButton;
	
	@FXML
	private void logoutButtonAction(ActionEvent event)
	{
		Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void manage1ButtonAction()
	{
		
	}
	
	@FXML
	private void manage2ButtonAction()
	{
		
	}
	
	@FXML
	private void manage3ButtonAction()
	{
		
	}
	
	@FXML
	private void profileButtonAction()
	{
		
	}

}
