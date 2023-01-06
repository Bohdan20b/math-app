# 🎥 Math application
### 📗 *Project description*
```
A simple math operation application, that accepts a string and checks if it is a mathematical expression. If it is valid, app will save it to DB.
```

###  *Features*
*Expression manipulations*
* User can check the expression for valid brackets order and if the result is valid, expression will be saved into the DB.
* User can see all the equations in the DB, update them and if the updated version is valid, it will be automatically counted and saved into the DB.
* User can also find an expression by its result in DB. App supports such query: find all expressions by result between values, find all expressions by result more than value, find all expressions by result less than value.

### 💾 *Project's structure*
Based on 3-layer architecture:

* Presentation layer - controllers.
* Application layer - services.
* Data access layer - DAO.

### 🔨 *Project launch:*
1. Clone this project to your IDE as Maven project. Hint: If you use IntelliJ IDEA, use only Ultimate version
2. Open [pom.xml](pom.xml) and reload all maven projects.
3. Open [application.properties](src/main/resources/application.properties) and set your credentials.
4. Build project.
