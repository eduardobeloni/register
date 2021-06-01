# Register
This is an example project of employees time registering. You are going to need Postman to test all the URLs featured in the backend and the Eclipse IDE with Tomcat server to import the project.

# Setting up the database
You should use the provided file register.db (in the project's root directory) and PostgreSQL database as starting points to test this project. Use the command "psql register < register.db" to restore the dump file.

# Running the Java backend
After cloning the repository and importing it in the Eclipse IDE, just right-click the file RegisterApp.java inside "register" package and select the option "Run as", then "Java application".

# Login
For the login, you need to do a POST request to the address http://localhost:8080/login using the Postman application. You also have to inform the user credentials in the body of the message, i.e., their email and password (according to the database "users" table) in Json format:
{
	"email": "admin@example.com",
	"password": "admin"
}
If the user's credentials are not found, the response status will be "403 Forbidden".

# Register the time
In order to register their time, the logged user must do a POST request to http://localhost:8080/register/. There will be presented in the message's body a true/false status of the register action.

# My registers
A properly logged user can see their registered times by doing a GET request to http://localhost:8080/myregisters. If thare is no user logged, the body of the message will return an empty list.

# Dashboard
By doing a GET request to the URL http://localhost:8080/dashboard, the admin (and only the user admin) can see the list of all users and their respective registered times.