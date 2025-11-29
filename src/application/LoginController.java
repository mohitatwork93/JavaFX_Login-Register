package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class LoginController implements Initializable
{
	
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label loginMessageLabel;
	
	@FXML
	private ImageView brandingImageView;
	
	@FXML
	private ImageView lockImageView;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private PasswordField enterPasswordField;
	
	@FXML
	private Hyperlink registerForm;
	
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{
		File brandingFile = new File("images/login_page.jpg");
		Image brandingImage = new Image(brandingFile.toURI().toString());
		brandingImageView.setImage(brandingImage);
		
		File lockFile = new File("images/lock.png");
		Image lockImage = new Image(lockFile.toURI().toString());
		lockImageView.setImage(lockImage);
		
	}
	
	
	public void loginButtonAction(ActionEvent event) throws SQLException
	{
		
		if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) 
		{
			String username = usernameTextField.getText().trim();
			String pass = enterPasswordField.getText().trim();
			validateLogin(username, pass);
		}
		else 
		{
			loginMessageLabel.setText("Please Enter Username and password");
		}
		
	}
	
	public void cancelButtonAction(ActionEvent event)
	{
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	
	public void signUpAction(ActionEvent event)
	{
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
	    Parent root = null;
	    try {
	        root = loader.load();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    Stage stage = new Stage();
	    stage.setTitle("Registration page");
	    stage.setScene(new Scene(root));
	    stage.show();

	    ((Stage) registerForm.getScene().getWindow()).close();  // CLOSE LOGIN WINDOW
	}

	
	public void validateLogin(String username, String pass) throws SQLException
	{
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.getConnection();
			String sql="SELECT * FROM user_account WHERE username = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				try {
				    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
				    Parent root = loader.load();
				    
				    Stage stage = new Stage();
				    stage.setTitle("Dashboard");
				    stage.setScene(new Scene(root));
				    stage.show();
				    
				    ((Stage) loginButton.getScene().getWindow()).close();

				}
				catch(Exception e)
				{
					e.printStackTrace();
					e.getCause();
				}
			    
			    
			} else {
			    loginMessageLabel.setText("Invalid username or password");
			}
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			e.getCause();
		}
		
		
	}
	
}
 