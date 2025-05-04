INSERT INTO FoodItem(fName, fBrand, fCalories, fFat, fCarbs, fProtein, fSodium, fSugar, fPotassium, fServingQty, fServingUnit)
VALUES 
('Whole Wheat Bread', 'Wheaty Goodness', 110, 2, 22, 4, 180, 2, 1, 1, 'pieces'),
('Almond Milk', 'Nutty Delight', 60, 3, 8, 1, 150, 7, 2, 1, 'cups'),
('Granola Bar', 'Crunchy Bites', 150, 5, 25, 3, 200, 9, 12, 1, 'bars');

INSERT INTO User(uFirstName, uLastName, uEmail, uPassword, uPhone, uHeight, uWeight, uAge, uGender)
VALUES ('Chris', 'Gomez', 'chris@gmail.com', 'chris', '3603910011', 100, 150, 22, 'Male');

INSERT INTO FoodItem (fName, fBrand, fCalories, fFat, fCarbs, fProtein, fSodium, fSugar, fPotassium, fServingQty, fServingUnit)
VALUES 
('Cheese', 'Tillamook', 113, 9, 1, 7, 180, 0, 20, 1, 'slice'),
('Bars', 'KIND', 200, 12, 17, 6, 30, 5, 100, 1, 'bar'),
('Milk', 'Horizon', 150, 8, 12, 8, 120, 11, 350, 1, 'cup');

INSERT INTO Pantry(uEmail, fName, pQuantity)
VALUES 
('chris@gmail.com', 'Cheese', 2),
('chris@gmail.com', 'Bars', 26),
('chris@gmail.com', 'Milk', 1);