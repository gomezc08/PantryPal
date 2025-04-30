DROP TABLE IF EXISTS RecipeIngredient;
DROP TABLE IF EXISTS RecipeInstruction;
DROP TABLE IF EXISTS RecipeCalendar;
DROP TABLE IF EXISTS NutritionLog;
DROP TABLE IF EXISTS PantryCategory;
DROP TABLE IF EXISTS Pantry;
DROP TABLE IF EXISTS UserActivity;
DROP TABLE IF EXISTS UserDailyNutritionGoal;
DROP TABLE IF EXISTS UnitTable;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS FoodItem;

CREATE TABLE IF NOT EXISTS FoodItem (
    fName VARCHAR(30) NOT NULL,
    fBrand VARCHAR(30) NOT NULL,
    fCalories INT,
    fFat INT,
    fCarbs INT,
    fProtein INT,
    fSodium INT,
    fSugar INT,
    fPotassium INT,
    fServingQty INT,
    fServingUnit VARCHAR(30),
    PRIMARY KEY (fName, fBrand)
);

CREATE TABLE IF NOT EXISTS User (
    uFirstName VARCHAR(30) NOT NULL,
    uLastName VARCHAR(30) NOT NULL,
    uEmail VARCHAR(30) NOT NULL,
    uPassword VARCHAR(30) NOT NULL,
    uPhone VARCHAR(30) NOT NULL,
    uHeight INT,
    uWeight INT,
    uAge INT,
    uGender VARCHAR(30),
    PRIMARY KEY (uEmail)
);

CREATE TABLE IF NOT EXISTS UserActivity (
    uEmail VARCHAR(30) NOT NULL,
    activity VARCHAR(30) NOT NULL,
    PRIMARY KEY (uEmail, activity),
    FOREIGN KEY (uEmail) REFERENCES User(uEmail)
);

CREATE TABLE IF NOT EXISTS UserDailyNutritionGoal (
    uEmail VARCHAR(30) NOT NULL,
    calories INT,
    fat INT,
    carbs INT,
    protein INT,
    sodium INT,
    sugar INT,
    potassium INT,
    PRIMARY KEY (uEmail),
    FOREIGN KEY (uEmail) REFERENCES User(uEmail)
);

CREATE TABLE IF NOT EXISTS Pantry (
    pName VARCHAR(30) NOT NULL,
    uEmail VARCHAR(30) NOT NULL,
    pQuantity INT,
    PRIMARY KEY (pName, uEmail),
    FOREIGN KEY (uEmail) REFERENCES User(uEmail)
);

CREATE TABLE IF NOT EXISTS PantryCategory (
    pName VARCHAR(30) NOT NULL,
    category VARCHAR(30) NOT NULL,
    fName VARCHAR(30) NOT NULL,
    PRIMARY KEY (pName, category),
    FOREIGN KEY (pName) REFERENCES Pantry(pName),
    FOREIGN KEY (fName) REFERENCES FoodItem(fName)
);

CREATE TABLE IF NOT EXISTS Recipe (
    rName VARCHAR(30) NOT NULL,
    mealType VARCHAR(30),
    totalCalories INT,
    totalFat INT,
    totalCarbs INT,
    totalProtein INT,
    totalSodium INT,
    totalSugar INT,
    totalPotassium INT,
    PRIMARY KEY (rName)
);

CREATE TABLE IF NOT EXISTS RecipeIngredient (
    rName VARCHAR(30) NOT NULL,
    fName VARCHAR(30) NOT NULL,
    fServingQty INT,
    fServingUnit VARCHAR(30),
    PRIMARY KEY (rName, fName),
    FOREIGN KEY (rName) REFERENCES Recipe(rName),
    FOREIGN KEY (fName) REFERENCES FoodItem(fName)
);

CREATE TABLE IF NOT EXISTS RecipeInstruction (
    rName VARCHAR(30) NOT NULL,
    step INT NOT NULL,
    stepDescription VARCHAR(30),
    PRIMARY KEY (rName, step),
    FOREIGN KEY (rName) REFERENCES Recipe(rName)
);

CREATE TABLE IF NOT EXISTS UnitTable (
    uName VARCHAR(30) NOT NULL,
    uAbbreviation VARCHAR(30),
    unitType VARCHAR(30),
    PRIMARY KEY (uName)
);

CREATE TABLE IF NOT EXISTS RecipeCalendar(
    uEmail VARCHAR(30) NOT NULL,
    date DATE NOT NULL,
    mealType VARCHAR(30),
    rName VARCHAR(30),
    PRIMARY KEY (uEmail, date),
    FOREIGN KEY (uEmail) REFERENCES User(uEmail),
    FOREIGN KEY (rName) REFERENCES Recipe(rName)
);

CREATE TABLE IF NOT EXISTS NutritionLog(
    uEmail VARCHAR(30) NOT NULL,
    date DATE NOT NULL,
    totalCalories INT,
    totalFat INT,
    totalCarbs INT,
    totalProtein INT,
    totalSodium INT,
    totalSugar INT,
    totalPotassium INT,
    PRIMARY KEY (uEmail, date),
    FOREIGN KEY (uEmail) REFERENCES User(uEmail)
);