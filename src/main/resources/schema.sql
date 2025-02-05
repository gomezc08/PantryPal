DROP TABLE IF EXISTS pantrypal;

CREATE TABLE IF NOT EXISTS pantrypal (
    fName VARCHAR(30) NOT NULL,
    fBrand VARCHAR(30) NOT NULL,
    fCalories INT,
    fFat INT,
    fCarbs INT,
    fProtein INT,
    fSodium INT,
    fSugar INT,
    fPotassium INT,
    fQuantity INT,
    PRIMARY KEY (fName, fBrand)
);