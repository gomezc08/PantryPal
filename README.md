# PantryPal

This project is a Spring Boot application designed to manage pantry activities. It allows users to add, update, and remove food item entries. The application uses Maven as its build system and H2 as an in-memory database for ease of setup and teardown.

## Features

- **Add Food**: Users can add new food items specifying details such as food brand, name, protein, carbs, etc.
- **Update Food Quanity**: Users can update existing quantities of food item.
- **Delete Food**: Users can remove food entries that are no longer needed.
- **View Food**: Users can view a list of food items in their pantry.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 11 or newer
- Maven 3.6 or newer

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installation

1. **Clone the repository:**
   ```bash
   git clone git@github.com:gomezc08/PantryPal.git
   cd pantrypal
   mvnw spring-boot:run
   ```
