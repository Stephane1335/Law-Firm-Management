# 🏛️ Law Firm Management — JavaFX Application  

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptopenjdk.net/)  
[![JavaFX](https://img.shields.io/badge/JavaFX-21-green.svg)](https://openjfx.io/)  
[![Gradle](https://img.shields.io/badge/Gradle-Build%20Tool-orange.svg)](https://gradle.org/)  

A **Java + JavaFX (FXML)** desktop application for managing a **law firm’s operations**.  
The system helps track **clients, agents, cases, tasks, and scheduling** within a legal office.  

---

## 🚀 Features  
✔️ **Client management** (add, edit, delete, display in TableView).  
✔️ **Agents management** for law firm staff.  
✔️ **Cases & files tracking**.  
✔️ **Agenda & appointments scheduling**.  
✔️ Modern **JavaFX + FXML UI**.  

---

## 📂 Project Structure  
```
Cabinetdavocats/
 ├── home/                  # Main package
 │   ├── Db.java             # Centralized JDBC connection handler
 │   ├── Client.java         # Client model
 │   ├── ClientController.java
 │   ├── Agent.java          # Agent model
 │   ├── AgentController.java
 │   ├── Agenda.java
 │   ├── AgendaController.java
 │   └── ...
 ├── resources/              # FXML files (UI layouts)
 │   ├── accueil.fxml
 │   ├── ajoutclient.fxml
 │   └── ...
 ├── config.properties       # JDBC configuration (editable)
 ├── build.gradle            # Gradle build script (optional)
 ├── settings.gradle
 └── README.md
```  

---

## ⚙️ Requirements  
- [Java 17 LTS](https://adoptopenjdk.net/) (recommended)  
- [JavaFX SDK](https://openjfx.io/)  
- A database (e.g., **MySQL** or **PostgreSQL**)  
- [Gradle](https://gradle.org/) (if you want to use the included build system)  

---

## 🛠️ Setup & Run  

### 1. Configure the database  
Edit the `config.properties` file with your database credentials:  
```properties
db.url=jdbc:mysql://localhost:3306/lawfirm
db.user=root
db.password=yourpassword
```

### 2. Run with Gradle  
```bash
gradle run
```

### 3. Run manually (without Gradle)  
```bash
javac -d out --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml home/*.java
java --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml home.Main
```

---

## 🧑‍💻 Contribution  
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

---

## 📜 License  
This project is licensed under the **MIT License**.  
