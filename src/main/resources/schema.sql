DROP TABLE IF EXISTS Food;

CREATE TABLE IF NOT EXISTS Food (
    fName VARCHAR(30) NOT NULL,
    fBrand VARCHAR(30) NOT NULL,
    fCalories INT,
    fFat INT,
    fCarbs INT,
    fProtein INT,
    fSodium INT,
    fSugar INT,
    fPotassium INT,
    PRIMARY KEY (fName, fBrand)
);