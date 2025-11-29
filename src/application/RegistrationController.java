package application;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RegistrationController 
{
	
	@FXML private ImageView logoImageView;
	@FXML private Label registerMessageLabel;
	
	@FXML private PasswordField passwordField;
	@FXML private TextField visiblePasswordField;
	
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField usernameField;
	@FXML private TextField emailAddressField;

	@FXML private Button registrationCancelButton; 
	@FXML private Button registrationSubmitButton; 


	@FXML
	private CheckBox showPasswordCheckBox;

	@FXML
	public void initialize() {
		
		File logoFile = new File("images/registerlogo.png");
		Image logoImage = new Image(logoFile.toURI().toString());
		logoImageView.setImage(logoImage);
		
		registerMessageLabel.setText("Enter Details and Click on Submit Button ");
		
		visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        // Toggle password visibility
        showPasswordCheckBox.selectedProperty().addListener((obs, oldV, selected) -> {
            
        	passwordField.setVisible(!selected);
            passwordField.setManaged(!selected);
        	visiblePasswordField.setVisible(selected);
            visiblePasswordField.setManaged(selected);
            
        });
	}
	
	
	@FXML
	private void SubmitButtonAction()
	{
		String firstName = firstNameField.getText().trim();
		String lastName = lastNameField.getText().trim();
		String username = usernameField.getText().trim();
		String emailaddress = emailAddressField.getText().trim();
		String password = passwordField.getText().trim();
		
		
		if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || emailaddress.isEmpty() || password.isEmpty()) 
		{
            showAlert(Alert.AlertType.ERROR, "All fields are required!");
            return;
        }
		
		addUser(firstName, lastName, username, emailaddress, password);
		
	}
	
	
	private void addUser(String firstName, String lastName, String username, String emailaddress, String password)
	{
		String sql = "INSERT INTO user_account (firstname, lastname, username, password, email) VALUES (?,?,?,?,?);";
		
		try
		{
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.getConnection();
		
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, username);
				ps.setString(4, password);
				ps.setString(5, emailaddress);
				
				int updated = ps.executeUpdate();

	            if (updated > 0) {
	                showAlert(Alert.AlertType.INFORMATION,
	                        "Your registration has been submitted.\nOnce approved by the Admin, your account will be activated.");
	                closeWindow();
	            } else {
	                showAlert(Alert.AlertType.ERROR, "Registration failed.");
	            }
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
		}

	}
	
    private void closeWindow() {
        Stage stage = (Stage) registrationSubmitButton.getScene().getWindow();
        stage.close();
    }
	
	private void showAlert(Alert.AlertType type, String msg) {
	        Alert a = new Alert(type);
	        a.setHeaderText(null);
	        a.setContentText(msg);
	        a.showAndWait();
	}
	
	
	@FXML
	private void cancelButtonAction()
	{
		  Stage stage = (Stage) registrationSubmitButton.getScene().getWindow();
	        stage.close();
	}
	
	@FXML
	private void toggleShowPassword() {
	    if (showPasswordCheckBox.isSelected()) {
	        visiblePasswordField.setText(passwordField.getText());
	        visiblePasswordField.setVisible(true);
	        visiblePasswordField.setManaged(true);

	        passwordField.setVisible(false);
	        passwordField.setManaged(false);
	    } else {
	        passwordField.setText(visiblePasswordField.getText());
	        passwordField.setVisible(true);
	        passwordField.setManaged(true);

	        visiblePasswordField.setVisible(false);
	        visiblePasswordField.setManaged(false);
	    }
	}
	
}
