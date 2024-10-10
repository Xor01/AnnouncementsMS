<p align="center">
  <img src="https://img.icons8.com/?size=512&id=55494&format=png" width="20%" alt="ANNOUNCEMENTSMS-logo">
</p>
<p align="center">
    <h1 align="center">ANNOUNCEMENTSMS</h1>
</p>
<p align="center">
    <em>Empowering user experiences, one interaction at a time.</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/Xor01/AnnouncementsMS?style=flat&logo=opensourceinitiative&logoColor=white&color=e2192e" alt="license">
	<img src="https://img.shields.io/github/last-commit/Xor01/AnnouncementsMS?style=flat&logo=git&logoColor=white&color=e2192e" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/Xor01/AnnouncementsMS?style=flat&color=e2192e" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/Xor01/AnnouncementsMS?style=flat&color=e2192e" alt="repo-language-count">
</p>
<p align="center">
		<em>Built with the tools and technologies:</em>
</p>
<p align="center">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" alt="java">
</p>

<br>

#####  Table of Contents

- [ Overview](#-overview)
- [ Features](#-features)
- [ Repository Structure](#-repository-structure)
- [ Modules](#-modules)
- [ Getting Started](#-getting-started)
    - [ Prerequisites](#-prerequisites)
    - [ Installation](#-installation)
    - [ Usage](#-usage)
    - [ Tests](#-tests)
- [ Project Roadmap](#-project-roadmap)
- [ Contributing](#-contributing)
- [ License](#-license)
- [ Acknowledgments](#-acknowledgments)

---

##  Overview

AnnouncementsMS is a comprehensive software project that streamlines user management and interaction within organizations. Its core functionalities include user registration, login authentication, group management, and secure password handling. Leveraging a well-structured architecture, AnnouncementsMS offers a user-friendly interface for adding users to groups, viewing member details, and ensuring database integrity. By integrating essential features like user record management and login validation, the project enhances system security and data management efficiency, making it a valuable tool for effective organization-wide communication.

---

##  Features

|    |   Feature         | Description |
|----|-------------------|---------------------------------------------------------------|
| ⚙️  | **Architecture**  | The project follows a modular architecture with distinct components like Register, Login, AddUserToGroup classes. It uses Java Swing for GUI interfaces and dotenv for secure database connection retrieval. |
| 🔩 | **Code Quality**  | The codebase demonstrates good coding practices and encapsulation. The classes are well-structured and follow Java conventions for maintainability. |
| 📄 | **Documentation** | The repository includes a README.md file providing basic information about the project. More documentation on functionalities, APIs, and setup would enhance its quality. |
| 🔌 | **Integrations**  | Relies on Java Swing for GUI components and dotenv for securely retrieving database connection information. |
| 🧩 | **Modularity**    | The project exhibits modularity with separate classes for user registration, login, database handling, and user interactions, promoting reusability. |
| 🧪 | **Testing**       | Testing frameworks and tools used are not specified in the details provided. Implementing unit and integration tests would enhance code reliability. |
| ⚡️  | **Performance**   | Efficiency and resource usage not explicitly mentioned. Optimizing database queries and GUI rendering could improve performance. |
| 🛡️ | **Security**      | Uses dotenv for securely retrieving database connection information. More details on data encryption, user authentication, and authorization mechanisms would enhance security measures. |
| 📦 | **Dependencies**  | Key dependencies include SQL and Java. More detailed listing of external libraries used could provide better insights. |
| 🚀 | **Scalability**   | Scalability aspects like handling increased traffic are not explicitly discussed. Enhancements in database design and load balancing strategies would aid in scalability. |

---

##  Repository Structure

```sh
└── AnnouncementsMS/
    ├── .github
    │   └── ISSUE_TEMPLATE
    ├── AnnouncementsMS.iml
    ├── LICENSE
    ├── README.md
    ├── SECURITY.md
    ├── assets
    │   ├── icons
    │   ├── login.png
    │   ├── mainScreen.png
    │   ├── membersList.png
    │   └── reg.png
    ├── pom.xml
    ├── src
    │   ├── main
    │   └── test
    └── template.env
```

---

##  Modules

<details closed><summary>src.main.java</summary>

| File | Summary |
| --- | --- |
| [Register.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/Register.java) | Repository Structure```sh└── AnnouncementsMS/ ├──.github │ └── ISSUE_TEMPLATE ├── AnnouncementsMS.iml ├── LICENSE ├── README.md ├── SECURITY.md ├── assets │ ├── icons │ ├── login.png │ ├── mainScreen.png │ ├── membersList.png │ └── reg.png ├── pom.xml ├── src │ ├── main │ └── test └── template.env```---### `src/main/java/Register.java`The `Register` class in this repository provides a user interface for new user registration. It allows users to input their credentials such as username, first name, last name, email, and password. The class handles the registration process involving validation of input fields and interactions with a database. This component contributes to the user registration functionality of the larger AnnouncementsMS application. |
| [Login.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/Login.java) | Login Module**The `Login` Java class in this repositorys architecture is responsible for creating a user login interface. It provides fields for users to input their username and password, along with a button to authenticate. The class utilizes Swing components to design and display the login window in a visually appealing manner. By encapsulating the login functionality within this module, the code promotes a structured and modular approach to user authentication within the project. |
| [DBInfo.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/DBInfo.java) | Retrieves database connection info securely using dotenv for the AnnouncementsMS repository. It abstracts the URL, username, and password for database access. |
| [AddUserToGroup.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/AddUserToGroup.java) | Enables adding users to a group, checking for existing users, and handling constraints via user input. Implements UI interactions for user-friendly experience. |
| [User.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/User.java) | The `User.java` file in the `src/main/java` directory of the `AnnouncementsMS` repository defines a class that manages user interactions within the application. It includes functionalities such as handling database connections, displaying tabs for different user groups, and managing user input areas. The file encapsulates user-specific logic and provides a structured approach to user-related operations, enhancing the overall architecture of the repository. |
| [UserRecord.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/UserRecord.java) | Generates hashed passwords for user records using a secure hashing algorithm. Enhances security by preventing plaintext storage of passwords in the AnnouncementsMS applications user management system. |
| [ConnectionManager.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/ConnectionManager.java) | Facilitates establishing database connections using provided credentials. Centralizes connection logic for easy access across the AnnouncementsMS repository, streamlining database operations. |
| [Main.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/Main.java) | Launches the application with a sleek Mac-like theme, initializing the login window. |
| [db.sql](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/db.sql) | Defines database schema and structures for the Announcements Management System. Describes tables, relationships, and data types, enabling efficient data storage and retrieval. Essential for the systems data management functionality. |
| [ListUsersOfAGroup.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/ListUsersOfAGroup.java) | Retrieves and displays group members details from the database in a structured list format on a GUI. Supports error handling for SQL exceptions and populates the UI with the member information upon invocation. |
| [PasswordHandler.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/main/java/PasswordHandler.java) | Calculates SHA-256 hash of passed passwords for secure storage in the AnnouncementsMS system. The `PasswordHandler` class encapsulates password hashing operations, enhancing system security by transforming plaintext passwords into irreversible hashed values. |

</details>

<details closed><summary>src.test.java</summary>

| File | Summary |
| --- | --- |
| [UserGroupTest.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/test/java/UserGroupTest.java) | Tests user group functionality by adding and removing users in a database group. Handles cases of adding non-member and existing members with error handling. |
| [LoginTest.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/test/java/LoginTest.java) | Verifies Login functionality with correct, incorrect, and missing credentials, enhancing application security. |
| [RegisterTest.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/test/java/RegisterTest.java) | Tests user registration functionality with valid and duplicate data in the AnnouncementsMS system. Verifies error handling and database integrity constraints in the Register class. |
| [GroupTest.java](https://github.com/Xor01/AnnouncementsMS/blob/main/src/test/java/GroupTest.java) | Verifies successful creation and deletion of a user group in the AnnouncementsMS system by leveraging database interactions. |

</details>

---

##  Getting Started

###  Prerequisites

**Java**: `version x.y.z`

###  Installation

Build the project from source:

1. Clone the AnnouncementsMS repository:
```sh
❯ git clone https://github.com/Xor01/AnnouncementsMS
```

2. Navigate to the project directory:
```sh
❯ cd AnnouncementsMS
```

3. Install the required dependencies:
```sh
❯ mvn clean install
```

###  Usage

To run the project, execute the following command:

```sh
❯ java -jar target/myapp.jar
```

###  Tests

Execute the test suite using the following command:

```sh
❯ mvn test
```

---

##  Project Roadmap

- [X] **`Task 1`**: <strike>Implement feature one.</strike>
- [ ] **`Task 2`**: Implement feature two.
- [ ] **`Task 3`**: Implement feature three.

---

##  Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Report Issues](https://github.com/Xor01/AnnouncementsMS/issues)**: Submit bugs found or log feature requests for the `AnnouncementsMS` project.
- **[Submit Pull Requests](https://github.com/Xor01/AnnouncementsMS/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github.com/Xor01/AnnouncementsMS/discussions)**: Share your insights, provide feedback, or ask questions.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/Xor01/AnnouncementsMS
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com{/Xor01/AnnouncementsMS/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=Xor01/AnnouncementsMS">
   </a>
</p>
</details>

---

##  License

This project is protected under the GNU Affero General Public License v3.0 License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/gpl-3.0/) file.

---

### Please copy the <code>template.env</code> template into <code>.env</code> file, and edit the variables appropriately in the <code>.env</code> file.
### Please run <code>db.sql</code> to get the database schema.
# Registration Form
![Registration Form](https://github.com/Xor01/AnnouncementsMS/blob/826616d9952b04ecfb585685b19003c7e8e0bbae/assets/reg.png)

This is the registration form where new users can create an account.

---

# Login Form
![Login Form](https://github.com/Xor01/AnnouncementsMS/blob/826616d9952b04ecfb585685b19003c7e8e0bbae/assets/login.png)

This is the login form for existing users to access their accounts.

---

# Main Screen
![Main Screen](https://github.com/Xor01/AnnouncementsMS/blob/826616d9952b04ecfb585685b19003c7e8e0bbae/assets/mainScreen.png)

This is the main screen of the application after logging in, displaying essential features and navigation options.

---

# Members List Screen
![Members List Screen](https://github.com/Xor01/AnnouncementsMS/blob/826616d9952b04ecfb585685b19003c7e8e0bbae/assets/membersList.png)

This screen shows the list of members, allowing users to manage and view member details.

